package com.exabeam.configparsertool;

import java.util.Map;

public class EventTypeFailure {

    private Map<String, SanityExceptionParser> exceptions;

    private int parsers_with_missing_fields;
    private int num_parsers;
    private int num_vendors;

    public Object getSummary() {
        return summary;
    }

    public void setSummary(Object summary) {
        this.summary = summary;
    }

    private Object summary;

    public int getParsers_with_missing_fields() {
        return parsers_with_missing_fields;
    }

    public void setParsers_with_missing_fields(int parsers_with_missing_fields) {
        this.parsers_with_missing_fields = parsers_with_missing_fields;
    }



    public int getNum_parsers() {
        return num_parsers;
    }

    public void setNum_parsers(int num_parsers) {
        this.num_parsers = num_parsers;
    }


    public int getNum_vendors() {
        return num_vendors;
    }

    public void setNum_vendors(int num_vendors) {
        this.num_vendors = num_vendors;
    }



    public Map<String, SanityExceptionParser> getExceptions() {
        return exceptions;
    }

    public void setExceptions(Map<String, SanityExceptionParser> exceptions) {
        this.exceptions = exceptions;
    }


}
