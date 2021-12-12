package de.hsba.bi.project.user;

// Ermöglicht Auswahl verschiedener Rollen beim Erstellen/ Ändern von Usern

public enum Role {
    PERSONALABTEILUNG("PERSONALABTEILUNG"), TERMINVERWALTER("TERMINVERWALTER"), MITARBEITER("MITARBEITER");

    private final String displayValue;

    private Role(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
