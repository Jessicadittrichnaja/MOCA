package de.hsba.bi.project.events;

public enum Category {
    Seminar("Seminar"), Workshop("Workshop"), Teamtag("Teamtag"), ELearning("E-Learning");

    private final String displayValue;

    private Category(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
