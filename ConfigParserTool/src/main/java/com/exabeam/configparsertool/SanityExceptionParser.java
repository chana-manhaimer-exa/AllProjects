package com.exabeam.configparsertool;
import java.util.List;
import java.util.Map;


public class SanityExceptionParser{
    public Map<String, List<String>> getMissing_rule_fields() {
        return missing_rule_fields;
    }

    public void setMissing_rule_fields(Map<String, List<String>> missing_rule_fields) {
        this.missing_rule_fields = missing_rule_fields;
    }

    private Map<String,List<String>> missing_rule_fields;

    public List<String> getMissing_event_builder_fields() {
        return missing_event_builder_fields;
    }

    public void setMissing_event_builder_fields(List<String> missing_event_builder_fields) {
        this.missing_event_builder_fields = missing_event_builder_fields;
    }

    private List<String> missing_event_builder_fields;

    public Map<String, List<String>> getMissing_model_fields() {
        return missing_model_fields;
    }

    public void setMissing_model_fields(Map<String, List<String>> missing_model_fields) {
        this.missing_model_fields = missing_model_fields;
    }

    private Map<String, List<String>> missing_model_fields;




}