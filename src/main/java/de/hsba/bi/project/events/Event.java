package de.hsba.bi.project.events;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Basic(optional = false)
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String category;

    @Getter
    @Setter
    private Integer duration;

        public Event(String name, String description, String category, Integer duration){
            this.name = name;
            this.description = description;
            this.category = category;
            this.duration = duration;
        }

    public Event() {

    }

    public boolean hasOverTwoDuration() {
        return this.duration > 2;
    }
}