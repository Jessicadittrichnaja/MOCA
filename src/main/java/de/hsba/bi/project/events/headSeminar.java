package de.hsba.bi.project.events;

// Ermöglicht Auswahl verschiedener Seminarleiter beim Erstellen/ Ändern von Events
public enum headSeminar {
    Klausi("Klausi Peter"), Günther("Günther Müller"), Maxi("Maxi Mustermann"), Anne("Anne Susanne"), Sabine("Sabine Richter");

    private final String displayValue;

    private headSeminar(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
