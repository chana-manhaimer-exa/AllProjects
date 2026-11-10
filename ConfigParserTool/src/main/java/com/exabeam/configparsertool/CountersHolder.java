package com.exabeam.configparsertool;

public class CountersHolder {
    public int getParsersCount() {
        return parsersCount;
    }

    public void setParsersCount(int parsersCount) {
        this.parsersCount = parsersCount;
    }

    public int getEventTypesCount() {
        return eventTypesCount;
    }

    public void setEventTypesCount(int eventTypesCount) {
        this.eventTypesCount = eventTypesCount;
    }

    private int eventTypesCount=0;
    private int parsersCount=0;

    public int getMitreCount() {
        return mitreCount;
    }

    public void setMitreCount(int mitreCount) {
        this.mitreCount = mitreCount;
    }
    public int getMitreSubCount() {
        return mitreSubCount;
    }

    public void setMitreSubCount(int mitreSubCount) {
        this.mitreSubCount = mitreSubCount;
    }


    private int mitreCount=0;
    private int mitreSubCount=0;
}
