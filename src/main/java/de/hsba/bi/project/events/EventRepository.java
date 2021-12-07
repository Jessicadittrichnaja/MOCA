package de.hsba.bi.project.events;

import de.hsba.bi.project.web.EventForm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    Event findByName(String name);

    List<Event> findByNameContaining(String name);

    List<Event> findByCategory(Category category);

    List<Event> findByLocation(Location location);

    List<Event> findByDate(LocalDate date);

    List<Event> findByTime(Time time);

    Optional<Event> findById(Integer id);
}
