package com.exabeam.configparsertool;
import java.util.Set;

public class DataSource {
    private String vendor;
    private String source;
    private String eventType="";
    private Set<String> parsers;

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Set<String> getParsers() {
        return parsers;
    }

    public void setParsers(Set<String> parsers) {
        this.parsers = parsers;
    }
}
