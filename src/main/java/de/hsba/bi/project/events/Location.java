package de.hsba.bi.project.events;

// Ermöglicht Auswahl verschiedener Standorte beim Erstellen/ Ändern von Events
public enum Location {
    Seminarraum1("Alsterblick"), Seminarraum2("Dachterrasse"), Spiegelsaal("Spiegelsaal"), Beamerraum("Beamerraum"), grosseHalle("Atrium");

    private final String displayValue;

    private Location(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
