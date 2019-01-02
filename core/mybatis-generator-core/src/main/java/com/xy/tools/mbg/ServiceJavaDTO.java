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

import java.io.Serializable;

public class ServiceJavaDTO implements Serializable {
    private String rootPackage;
    private String servicePackage;

    private String modelPackage;
    private String modelName;
    /**
     * 小驼峰式
     */
    private String littleCamelModelName;

    private String clientPackage;
    private String clientName;
    private String littleCamelClientName;

    public String getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getClientPackage() {
        return clientPackage;
    }

    public void setClientPackage(String clientPackage) {
        this.clientPackage = clientPackage;
    }

    public String getLittleCamelModelName() {
        return littleCamelModelName;
    }

    public void setLittleCamelModelName(String littleCamelModelName) {
        this.littleCamelModelName = littleCamelModelName;
    }

    public String getLittleCamelClientName() {
        return littleCamelClientName;
    }

    public void setLittleCamelClientName(String littleCamelClientName) {
        this.littleCamelClientName = littleCamelClientName;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }
}
