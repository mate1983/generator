package com.xy.tools.mbg;

import java.io.Serializable;

/**
 * @author limingcai
 * @date 2019/1/2
 **/
public class ServiceJavaDTO implements Serializable {
    private String targetPackage;
    private String modelName;
    private String clientName;

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
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
}
