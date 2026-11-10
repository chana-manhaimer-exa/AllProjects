package com.exabeam.configparsertool;

public class Model {

    private String modelId;
    private String modelTemplate;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId.trim();
    }

    public String getModelTemplate() {
        return modelTemplate;
    }

    public void setModelTemplate(String modelTemplate) {
        this.modelTemplate = modelTemplate;
    }


}
