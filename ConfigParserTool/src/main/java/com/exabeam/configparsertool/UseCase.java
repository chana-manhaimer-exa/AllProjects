package com.exabeam.configparsertool;
import java.util.List;

public class UseCase {
    private String useCaseName;
    private List<String> useCaseEventTypes;
    private String ruleId;
    private String ruleName;
    private String ruleCategory;
    private String modelId;
    private String mitreId;
    private String ruleMitreLabels;
    private String rulePicassoLabels;

    public String getUseCaseName() {
        return useCaseName;
    }

    public void setUseCaseName(String useCaseName) {
        this.useCaseName = useCaseName.trim();
    }

    public List<String> getUseCaseEventTypes() {
        return useCaseEventTypes;
    }

    public void setUseCaseEventTypes(List<String> useCaseEventTypes) {
        this.useCaseEventTypes = useCaseEventTypes;
    }
    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId.trim();
    }

    public String getRuleName() {
        return ruleName;
    }
    public String getRuleCategory() {
        return ruleCategory;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName.trim();
    }
    public void setRuleCategory(String ruleCategory) {
        this.ruleCategory = ruleCategory.trim();
    }
    public String getModelId() {
        return modelId;
    }
    public String getMitreId() {
        return mitreId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId.trim();
    }
    public void setMitreId(String mitreId) {
        this.mitreId = mitreId.trim();
    }

    public void setRuleMitreLabels(String ruleMitreLabels) {
        this.ruleMitreLabels = ruleMitreLabels.trim();
    }
    public void setRulePicassoLabels(String rulePicassoLabels) {
        this.rulePicassoLabels = rulePicassoLabels.trim();
    }
    public String getRuleMitreLabels() {
        return ruleMitreLabels;
    }
    public String getRulePicassoLabels() {
        return rulePicassoLabels;
    }

}
