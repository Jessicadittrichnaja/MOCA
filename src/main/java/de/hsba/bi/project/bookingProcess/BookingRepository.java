package de.hsba.bi.project.bookingProcess;

import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

   // verschiedene Datenbankabfragen, um z.B. zu garantieren, dass ein Event nur einmal von einem User gebucht werden kann und Übersicht über Buchungen zu ermöglichen
   @Query("SELECT e from Booking b INNER JOIN b.event e where b.user= :user")
   List<Event> findByUser(@Param("user")User user);

   @Transactional
   @Modifying
   @Query("UPDATE User u Set u.password = :password where u.id = :id")
   void findAvailableEvents(@Param("password") String password, @Param("id") Integer id);

   @Query("SELECT COUNT(id) from Booking b WHERE b.user= :user AND b.event= :event")
   Integer findBookingByUser(@Param("user")User user, @Param("event") Event event);

   @Query("SELECT b from Booking b WHERE b.user= :user AND b.event= :event")
   Booking findBooking(@Param("user")User user, @Param("event") Event event);

   @Query("SELECT b.user from Booking b WHERE b.event= :event")
   List<User> findBookingsForEvent(@Param("event") Event event);

}

