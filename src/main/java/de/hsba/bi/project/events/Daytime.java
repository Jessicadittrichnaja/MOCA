package de.hsba.bi.project.events;

// Ermöglicht Auswahl verschiedener Kategorien beim Erstellen/ Ändern von Events

public enum Daytime {
    Morgens("Morgens"), Vormittags("Vormittags"), Nachmittags("Nachmittags"), Abends("Abends");

    private final String displayValue;

    private Daytime(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
