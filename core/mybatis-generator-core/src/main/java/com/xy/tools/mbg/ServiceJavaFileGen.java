/**
 *    Copyright 2006-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.xy.tools.mbg;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;

public class ServiceJavaFileGen {
    private Configuration ftlConfig;
    private String configPath;

    public ServiceJavaFileGen(final String filePath) {
        try {
            this.configPath = filePath;
            ftlConfig = new Configuration(Configuration.VERSION_2_3_23);
            ftlConfig.setDefaultEncoding("UTF-8");
            ftlConfig.setDirectoryForTemplateLoading(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean writeJavaFile(org.mybatis.generator.config.Configuration mygConfig, MyBatisGenerator myBatisGenerator) {
        try {
            ServiceJavaDTO serviceJavaDTO = buildDTO(mygConfig, myBatisGenerator);

            Collection<File> fileCollections = FileUtils.listFiles(new File(configPath), FileFilterUtils.suffixFileFilter("ftl"), DirectoryFileFilter.INSTANCE);
            if (null == fileCollections || fileCollections.isEmpty()) {
                return false;
            }
            for (File file : fileCollections) {
                Template template = ftlConfig.getTemplate(file.getName());
                template.setEncoding("UTF-8");


                File dir = new File(configPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String clientProject = mygConfig.getContexts().get(0).getJavaClientGeneratorConfiguration().getTargetProject();
                String servicePath = dir + File.separator + clientProject + File.separator + StringUtils.replaceAll(serviceJavaDTO.getServicePackage(), "\\.", Matcher.quoteReplacement(File.separator));
                dir = new File(servicePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String baseName = FilenameUtils.getBaseName(file.getName());
                String targetFilename = serviceJavaDTO.getModelName() + baseName + ".java";
                OutputStream fos = new FileOutputStream(new File(servicePath, targetFilename));
                Writer out = new OutputStreamWriter(fos);
                template.process(serviceJavaDTO, out);

                out.flush();
                out.close();
                fos.flush();
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        return true;
    }

    public ServiceJavaDTO buildDTO(org.mybatis.generator.config.Configuration config, MyBatisGenerator myBatisGenerator) {
        if (null == myBatisGenerator) {
            return null;
        }
        List<GeneratedJavaFile> javaFiles = myBatisGenerator.getGeneratedJavaFiles();
        if (null == javaFiles || javaFiles.isEmpty()) {
            return null;
        }
        TopLevelClass modelClass = null;
        Interface clientClass = null;
        for (GeneratedJavaFile generatedJavaFile : javaFiles) {
            if (generatedJavaFile.getCompilationUnit() instanceof TopLevelClass) {
                modelClass = (TopLevelClass) generatedJavaFile.getCompilationUnit();
                continue;
            }
            if (generatedJavaFile.getCompilationUnit() instanceof Interface) {
                clientClass = (Interface) generatedJavaFile.getCompilationUnit();
                continue;
            }
        }
        ServiceJavaDTO dto = new ServiceJavaDTO();
        if (null != modelClass) {
            FullyQualifiedJavaType qualifiedJavaType = modelClass.getType();
            dto.setModelPackage(qualifiedJavaType.getPackageName());
            dto.setModelName(qualifiedJavaType.getShortName());
            dto.setLittleCamelModelName(toLittleCameCase(dto.getModelName()));
        }
        if (null != clientClass) {
            FullyQualifiedJavaType qualifiedJavaType = clientClass.getType();
            int index = qualifiedJavaType.getPackageName().lastIndexOf(".");
            dto.setRootPackage(StringUtils.substring(qualifiedJavaType.getPackageName(), 0, index));
            dto.setServicePackage(dto.getRootPackage() + ".service");
            dto.setClientPackage(qualifiedJavaType.getPackageName());
            dto.setClientName(qualifiedJavaType.getShortName());
            dto.setLittleCamelClientName(toLittleCameCase(dto.getClientName()));
        }

        return dto;
    }

    public String toLittleCameCase(final String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

}
