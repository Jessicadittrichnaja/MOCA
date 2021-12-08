package de.hsba.bi.project.bookingProcess;

import de.hsba.bi.project.events.*;
import de.hsba.bi.project.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public List<Booking> findAll() {
        return (List<Booking>) bookingRepository.findAll();
    }
    public Booking addEvent(Booking booking)  {
        booking = bookingRepository.save(booking);
        return booking;
    }

    public Booking findById(Integer id) {

        Booking booking = bookingRepository.findById(id).orElse(null);

        return booking;

    };

 /*   public List<Booking> findbyUser(User user) {
        List<Booking> bookings = bookingRepository.findByUser(user);
        for (Booking booking : bookings) {
        }
        return bookings;
    };
*/
    public void removeBooking(Booking booking)  {
        bookingRepository.delete(booking);
    }

    public void save(Booking booking) { bookingRepository.save(booking);
    }

}