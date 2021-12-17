package de.hsba.bi.project.events;

import de.hsba.bi.project.user.User;
import de.hsba.bi.project.web.EventForm;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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

    // Methoden z.B. zum Runterzählen/ Hochzählen von freien Plätzen für Events bei Buchungen/ Löschen von Buchungen durch Mitarbeiter

    @Transactional
    @Modifying
    @Query("UPDATE Event e Set e.spots = e.spots-1 where e.id = :id")
    void removeSpot(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Event e Set e.spots = e.spots+1 where e.id = :id")
    void addSpot(@Param("id") Integer id);

    @Query("SELECT Count(id) from User u where u.name= :name")
    Integer countNumberUsersWithSameName(@Param("name")String name);

    @Query("SELECT e from Event e WHERE e.id NOT IN (SELECT e.id FROM Event e INNER JOIN Booking b ON e.id = b.event WHERE b.user= :user) AND e.spots > 0")
    List<Event> findAvailableEvents(@Param("user") User user);

    @Transactional
    @Modifying
    @Query("UPDATE Event e Set e.spots = e.spots+1 WHERE e.id IN (SELECT e.id FROM Event e INNER JOIN Booking b ON e.id = b.event WHERE b.user= :user)")
    void addSpotWhenUserDeleted(@Param("user") User user);
    
  @Query("UPDATE Event e SET e.isClosed = true where e.id = :id")
    void closeEvent(@Param("id") Integer id);

    @Query("SELECT e.isClosed FROM Event e WHERE e.id = :id")
    boolean isEventClosed(@Param("id") Integer id);

}
