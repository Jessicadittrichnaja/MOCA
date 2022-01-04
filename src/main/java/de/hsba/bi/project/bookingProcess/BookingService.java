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

    @Autowired
    EventService eventService;

    // Methoden z.B. zum Erstellen und Löschen von Buchungen

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

    public Booking findByEvent(Event event) {
        Booking booking = bookingRepository.findByEvent(event).orElse(null);
        return booking;
    };

    public void removeBooking(Booking booking)  {
        bookingRepository.delete(booking);
    }

    public void save(Booking booking) { bookingRepository.save(booking);
    }

    public void throwErrorIfBookingIsClosed(Integer id) {
        if(eventService.isEventClosed(id)){
            throw new IllegalArgumentException("Event is closed");
        }
    }
    public List<Event> findByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    public Integer findBookingByUser(User user, Event event) {
        return bookingRepository.findBookingByUser(user, event);
    }

    public Booking findBooking(User user, Event event) {
        return bookingRepository.findBooking(user, event);
    }

    public List<User> findBookingsForEvent(Event event) {
        return bookingRepository.findBookingsForEvent(event);
    }
}