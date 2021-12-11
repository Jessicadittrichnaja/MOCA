package de.hsba.bi.project.events;

// Ermöglicht Auswahl verschiedener Kategorien beim Erstellen/ Ändern von Events

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
