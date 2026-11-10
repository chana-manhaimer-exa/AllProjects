package com.exabeam.configparsertool;

public class CorrelationRule {

    public String getOverlap_with_AA() {
        return overlap_with_AA;
    }

    public void setOverlap_with_AA(String overlap_with_AA) {
        this.overlap_with_AA = overlap_with_AA;
    }

    private String overlap_with_AA;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    private String templateId;

    public String getMitreTactic() {
        return mitreTactic;
    }

    public void setMitreTactic(String mitreTactic) {
        this.mitreTactic = mitreTactic;
    }

    private String mitreTactic;

    public CorrelationRule(String crTemplateID, String  crName,String crDescription,String crUsecase, String crSeverity, String crQuery, String overlap_with_AA,  String mitreTactic) {

        this.crName = crName;
        this.crDescription=crDescription;
        this.crUsecase=crUsecase;
        this.crSeverity=crSeverity;
        this.crQuery=crQuery;
        this.templateId=crTemplateID;
        this.overlap_with_AA=overlap_with_AA;
        this.mitreTactic=mitreTactic;
    }
    public String getCrUsecase() {
        return crUsecase;
    }

    public void setCrUsecase(String crUsecase) {
        this.crUsecase = crUsecase;
    }

    private String crUsecase;
    public String getCrName() {
        return crName;
    }

    public void setCrName(String crName) {
        this.crName = crName;
    }

    private String crName;

    public String getCrDescription() {
        return crDescription;
    }

    public void setCrDescription(String crDescription) {
        this.crDescription = crDescription;
    }

    private String crDescription;

    public String getCrSeverity() {
        return this.crSeverity;
    }

    public void setCrSeverity(String crSeverity) {
        this.crSeverity = crSeverity;
    }

    private  String crSeverity;

    public String getCrQuery() {
        return crQuery;
    }

    public void setCrQuery(String crQuery) {
        this.crQuery = crQuery;
    }

    private String crQuery;



}
