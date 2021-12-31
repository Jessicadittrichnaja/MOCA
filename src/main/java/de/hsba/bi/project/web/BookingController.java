package de.hsba.bi.project.web;

import de.hsba.bi.project.bookingProcess.Booking;
import de.hsba.bi.project.bookingProcess.BookingRepository;
import de.hsba.bi.project.bookingProcess.BookingService;
import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.user.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.hsba.bi.project.events.EventService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/booking")
@ComponentScan("de.hsba.bi.project.events")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;

    // zeigt Buchungen zu einem User

    @GetMapping
    public String bookingList(Model model) {
        model.addAttribute("bookings", bookingService.findByUser(userService.findCurrentUser()));
        return "booking";
    }

}