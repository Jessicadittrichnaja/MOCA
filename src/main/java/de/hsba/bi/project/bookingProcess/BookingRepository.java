package de.hsba.bi.project.bookingProcess;

import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {


    Optional<Booking> findById(Long id);

//    List<Booking> findByUser(User user);
}

