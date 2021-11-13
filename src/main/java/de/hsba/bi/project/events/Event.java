package de.hsba.bi.project.events;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;*/

/*@Entity*/
public class Event implements Serializable {

 /*   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
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

/*        public Event(Long id, String name, String description, Integer duration) {
            super();
            this.id = id;
            this.name = name;
            this.description = description;
            this.duration = duration;
        }
*/
}