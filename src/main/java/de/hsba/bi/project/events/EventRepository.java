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
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    Optional<Event> findByName(String name);

    List<Event> findByNameContaining(String name);

    List<Event> findByCategory(Category category);

    List<Event> findByLocation(Location location);

    List<Event> findByDate(LocalDate date);

    List<Event> findByHeadSeminar(headSeminar headSeminar);

    Optional<Event> findById(Integer id);

    // Methoden z.B. zum Runterzählen/ Hochzählen von freien Plätzen für Events bei Buchungen/ Löschen von Buchungen durch Mitarbeiter. Die Queries werden in den Controllern genutzt, die Funktionsweise wird dort jeweils näher erläutert.

    @Transactional
    @Modifying
    @Query("UPDATE Event e Set e.spots = e.spots-1 where e.id = :id")
    void removeSpot(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Event e Set e.spots = e.spots+1 where e.id = :id")
    void addSpot(@Param("id") Integer id);

    @Query("SELECT e from Event e WHERE e.id NOT IN (SELECT e.id FROM Event e INNER JOIN Booking b ON e.id = b.event WHERE b.user= :user) AND e.spots > 0 AND e.isClosed = false ORDER BY e.date")
    List<Event> findAvailableEvents(@Param("user") User user);

    @Transactional
    @Modifying
    @Query("UPDATE Event e Set e.spots = e.spots+1 WHERE e.id IN (SELECT e.id FROM Event e INNER JOIN Booking b ON e.id = b.event WHERE b.user= :user)")
    void addSpotWhenUserDeleted(@Param("user") User user);

    @Transactional
    @Modifying
    @Query("UPDATE Event e SET e.isClosed = true where e.id = :id")
    void closeEvent(@Param("id") Integer id);

    @Query("SELECT e.isClosed FROM Event e WHERE e.id = :id")
    boolean isEventClosed(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Event e SET e.isClosed = false where e.id = :id")
    void openEvent(@Param("id") Integer id);

    @Query("SELECT e.isClosed FROM Event e WHERE e.id = :id")
    boolean isEventOpen(@Param("id") Integer id);

    List<Event> findTop3ByOrderByIdAsc();

    // Für die Filterung der Events nach Tageszeiten

    @Query("SELECT e from Event e WHERE e.startTime < '11:00:00'")
    List<Event> findEventsMorning();

    @Query("SELECT e from Event e WHERE e.startTime >= '11:00:00' AND e.startTime < '14:00:00'")
    List<Event> findEventsNoon();

    @Query("SELECT e from Event e WHERE e.startTime >= '14:00:00' AND e.startTime < '17:00:00'")
    List<Event> findEventsAfternoon();

    @Query("SELECT e from Event e WHERE e.startTime >= '17:00:00' AND e.startTime < '20:00:00'")
    List<Event> findEventsEvening();

    //damit keine Überschneidungen von Terminen zur gleichen Zeit im gleichen Raum möglich sind

    @Query("SELECT Count(id) from Event e where e.location= :location and e.date= :date and (e.startTime < :endTime AND e.endTime > :startTime)")
    Integer countNumberEventsWithSameLocationAtSameTime(@Param("location")Location location, @Param("date")LocalDate date, @Param("startTime")LocalTime startTime,  @Param("endTime")LocalTime endTime);

    @Query("SELECT Count(id) from Event e where e.id!= :id and e.location= :location and e.date= :date and (e.startTime < :endTime AND e.endTime > :startTime)")
    Integer countNumberEventsWithSameLocationAtSameTimeExceptCurrentEvent(@Param("id")Integer id, @Param("location")Location location, @Param("date")LocalDate date, @Param("startTime")LocalTime startTime,  @Param("endTime")LocalTime endTime);

    @Query("SELECT Count(id) from Event e where e.headSeminar= :headSeminar and e.date= :date and (e.startTime < :endTime AND e.endTime > :startTime)")
    Integer countNumberEventsWithSameHeadSeminarAtSameTime(@Param("headSeminar")headSeminar headSeminar, @Param("date")LocalDate date, @Param("startTime")LocalTime startTime,  @Param("endTime")LocalTime endTime);

    @Query("SELECT Count(id) from Event e where e.id!= :id and e.headSeminar= :headSeminar and e.date= :date and (e.startTime < :endTime AND e.endTime > :startTime)")
    Integer countNumberEventsWithSameHeadSeminarAtSameTimeExceptCurrentEvent(@Param("id")Integer id, @Param("headSeminar")headSeminar headSeminar, @Param("date")LocalDate date, @Param("startTime")LocalTime startTime,  @Param("endTime")LocalTime endTime);
}




