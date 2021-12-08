package de.hsba.bi.project.bookingProcess;

import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

   @Query("SELECT e from Booking b INNER JOIN b.event e where b.user= :user")
   List<Event> findByUser(@Param("user")User user);
/*
   @Query("SELECT booking from Booking b where b.event= :event")
   List<Booking> findById(@Param("event")Event event);
*/
}

