package de.hsba.bi.project.events;

// Ermöglicht Auswahl verschiedener Standorte beim Erstellen/ Ändern von Events

public enum Location {
    Hamburg("Hamburg"), Berlin("Berlin"), München("München");

    private final String displayValue;

    private Location(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
