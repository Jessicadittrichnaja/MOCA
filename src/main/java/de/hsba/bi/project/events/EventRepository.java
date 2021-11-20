package de.hsba.bi.project.events;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    Event findByName(String name);

    List<Event> findByNameContaining(String name);

    }
