package de.hsba.bi.project.user;

public enum Role {
    Personalabteilung("Personalabteilung"), Terminverwalter("Terminverwalter"), Mitarbeiter("Mitarbeiter");

    private final String displayValue;

    private Role(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
