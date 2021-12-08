package de.hsba.bi.project.web;

import de.hsba.bi.project.bookingProcess.Booking;
import de.hsba.bi.project.bookingProcess.BookingRepository;
import de.hsba.bi.project.bookingProcess.BookingService;
import de.hsba.bi.project.events.EventRepository;
import de.hsba.bi.project.events.EventService;
import de.hsba.bi.project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class BookingShowController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private FormAssembler formAssembler;

    private final UserService userService;

    private final BookingService bookingService;

    private final BookingRepository bookingRepository;

    @GetMapping("/bookingOverview/{id}")
    public String showBookedEvent(@PathVariable("id") int id, Model model) {
        model.addAttribute("events", eventService.findById(id));
        Booking booking = new Booking(eventService.findEvent(id), userService.findCurrentUser());
        bookingService.save(booking);
        model.addAttribute("bookingid", booking.getId());
        return "bookingOverview";
    }
/*
    @GetMapping("@{/booking/delete/{id}")
    public String deleteBookedEvent(@PathVariable("id") int id, Model model) {

        Booking booking = bookingService.findById(id);
        bookingService.removeBooking(booking);
        model.addAttribute("bookings", bookingService.findAll());
        return "bookingOverview";
    }*/
}

