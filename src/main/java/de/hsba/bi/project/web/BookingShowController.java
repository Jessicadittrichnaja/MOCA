package de.hsba.bi.project.web;

import de.hsba.bi.project.bookingProcess.Booking;
import de.hsba.bi.project.bookingProcess.BookingRepository;
import de.hsba.bi.project.bookingProcess.BookingService;
import de.hsba.bi.project.events.EventRepository;
import de.hsba.bi.project.events.EventService;
import de.hsba.bi.project.filter.Filter;
import de.hsba.bi.project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BookingShowController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventFormConverter eventFormConverter;

    private final UserService userService;

    private final BookingService bookingService;


    // speichert Buchung, wenn Buchung noch nicht von User getätigt wurde. Zieht außerdem einen freien Platz vom Event ab.
    @GetMapping("/bookingOverview/{id}")
    public String showBookedEvent(@PathVariable("id") int id, Model model) {
        bookingService.throwErrorIfBookingIsClosed(id);
        if (bookingService.findBookingByUser(userService.findCurrentUser(), eventService.findById(id)) == 1) {
            model.addAttribute("events", eventService.findAll());
            model.addAttribute("filter", new Filter());
            return "overview";
        }
        model.addAttribute("events", eventService.findById(id));
        Booking booking = new Booking(eventService.findEvent(id), userService.findCurrentUser());
        bookingService.save(booking);
        eventService.removeSpot(id);
        return "bookingOverview";
    }

    // Löscht Buchung und fügt einen freien Platz zum Event hinzu, das vom User gelöscht wurde.
    @GetMapping("/booking/delete/{id}")
    public String deleteBooking(@PathVariable("id") int id, Model model) {
        bookingService.throwErrorIfBookingIsClosed(id);
        Booking booking = bookingService.findBooking(userService.findCurrentUser(), eventService.findById(id));
        bookingService.removeBooking(booking);
        eventService.addSpot(id);
        model.addAttribute("bookings", bookingService.findByUser(userService.findCurrentUser()));
        return "booking";
    }

    // Zeigt, wer alles für das Event angemeldet ist.
    @GetMapping("/booking/users/{id}")
    public String showBookingsForEvent(@PathVariable("id") int id, Model model) {
        model.addAttribute("bookings", bookingService.findBookingsForEvent(eventService.findById(id)));
        return "bookingsForEvent";
    }
}

