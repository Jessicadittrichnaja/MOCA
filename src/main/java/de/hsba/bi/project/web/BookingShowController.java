package de.hsba.bi.project.web;

import de.hsba.bi.project.bookingProcess.Booking;
import de.hsba.bi.project.bookingProcess.BookingService;
import de.hsba.bi.project.events.EventRepository;
import de.hsba.bi.project.events.EventService;
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
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private FormAssembler formAssembler;

    private final UserService userService;

    private final BookingService bookingService;

    @GetMapping("/bookingOverview/{id}")
    public String showBookedEvent(@PathVariable("id") int id, Model model) {
        model.addAttribute("event", eventService.findById(id));
        Booking booking = new Booking(eventService.findEvent(id), userService.findCurrentUser());
        bookingService.save(booking);
        return "bookingOverview";
    }

/*    @PostMapping
    public String change(Model model, @PathVariable("id") Integer id,
                         @ModelAttribute("event") @Valid EventForm eventForm, BindingResult journalBinding) {
        if (journalBinding.hasErrors()) {
            return "overview";
        }

        return "bookingOverview";
    }
*/
}

