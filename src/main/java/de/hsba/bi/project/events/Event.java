package de.hsba.bi.project.events;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Basic(optional = false)
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Category category;

    @Getter
    @Setter
    private Integer duration;

    @Getter
    @Setter
    private Integer spots;


        public Event(String name, String description, Category category, Integer duration, Integer spots) {
            this.name = name;
            this.description = description;
            this.category = category;
            this.duration = duration;
            this.spots = spots;
        }

    public Event() {

    }
}