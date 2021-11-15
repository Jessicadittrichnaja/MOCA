package de.hsba.bi.project.events;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    @Query("SELECT e FROM Event e ORDER BY e.name")
    List<Event> findAllEvents();

    List<Event> findAll();

}
