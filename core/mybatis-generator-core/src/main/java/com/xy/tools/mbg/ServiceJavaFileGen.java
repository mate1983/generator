package com.xy.tools.mbg;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.io.*;
import java.util.List;

/**
 * 服务类的生成
 *
 * @author limingcai
 * @date 2019-01-02
 */
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
            Template template = ftlConfig.getTemplate("service.ftl");
            template.setEncoding("UTF-8");

            File dir = new File(configPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            OutputStream fos = new FileOutputStream(new File(dir, "Person.java"));
            Writer out = new OutputStreamWriter(fos);
            ServiceJavaDTO serviceJavaDTO = buildDTO(mygConfig, myBatisGenerator);
            template.process(serviceJavaDTO, out);

            out.flush();
            out.close();
            fos.flush();
            fos.close();
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
//        dto.setTargetPackage(ftlConfig.get);
//        dto.setClientName();
//        dto.setModelName();

        return dto;
    }
}
