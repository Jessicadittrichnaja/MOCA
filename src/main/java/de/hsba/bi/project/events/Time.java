package de.hsba.bi.project.events;

// Ermöglicht Auswahl verschiedener Zeiten beim Erstellen/ Ändern von Events

public enum Time {
    Morgens("08:00 Uhr"), Vormittags("12:00 Uhr"), Mittags("14:00 Uhr"), Abends("16:00 Uhr");

    private final String displayValue;

    private Time(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
