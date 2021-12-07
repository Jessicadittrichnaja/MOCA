package de.hsba.bi.project.events;

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
