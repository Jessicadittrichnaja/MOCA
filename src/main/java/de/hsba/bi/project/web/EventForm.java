package de.hsba.bi.project.web;

import de.hsba.bi.project.events.Category;
import de.hsba.bi.project.events.Location;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EventForm {

    // ermöglicht Fehlermeldung, wenn nicht alle Felder für neues/ bearbeitetes Event ausgefüllt wurden

    @NotBlank(message = "Bitte einen Namen eingeben")
    @Getter
    @Setter
    private String name;

    @NotNull(message = "Bitte einen Standort wählen")
    @Getter
    @Setter
    private Location location;

    @NotNull(message = "Bitte eine Kategorie wählen")
    @Getter
    @Setter
    private Category category;

    @NotBlank(message = "Bitte eine Beschreibung wählen")
    @Getter
    @Setter
    private String description;

    @NotNull(message = "Bitte eine Dauer wählen")
    @Getter
    @Setter
    private Integer duration;

    @NotNull(message = "Bitte eine Teilnehmerzahl wählen")
    @Getter
    @Setter
    private Integer spots;

    @NotNull(message = "Bitte ein Datum wählen")
    @Getter
    @Setter
    private LocalDate date;

    @NotNull(message = "Bitte eine Startzeit wählen")
    @Getter
    @Setter
    private LocalTime startTime;

    @Getter
    @Setter
    private LocalTime endTime;


}