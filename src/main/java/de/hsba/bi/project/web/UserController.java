package de.hsba.bi.project.web;

import de.hsba.bi.project.bookingProcess.BookingRepository;
import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.events.EventRepository;
import de.hsba.bi.project.user.User;
import de.hsba.bi.project.user.UserRepository;
import de.hsba.bi.project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserFormConverter userFormConverter;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EventRepository eventRepository;

    // Erstellung eines neuen Users

    @GetMapping("/HR/createUser")
    public String Form(Model model) {
        model.addAttribute("userForm", userFormConverter.toForm(userService.findCurrentUser()));
        model.addAttribute("user", new User());
        return "HR/createUser";
    }

    // Speichern des neuen Users, wenn valide und der Name noch nicht in der Datenbank vorhanden ist

    @PostMapping("/HR/createUser")
    public String saveUser(@ModelAttribute("user") User user, @ModelAttribute("userForm") @Valid UserForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "HR/createUser";
        }
        if (userRepository.countNumberUsersWithSameName(user.getName()) == 1){
            model.addAttribute("user", new User());
            return "HR/createUser";
        }
        userService.save(userFormConverter.update(user, form));
        return "HR/userResult";
    }
    @GetMapping("/HR/userlist")
    public String userList(Model model) {
        model.addAttribute("user", userService.findAll());
        return "HR/userlist";
    }
    // Löschen eines Users


    @GetMapping("/HR/userlist/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model)
    {
        User user = userService.findById(id);
        eventRepository.addSpotWhenUserDeleted(user);
        userService.removeUser(user);
        model.addAttribute("user",userService.findAll());
        return "HR/userlist";
    }

    // Bearbeiten eines Users

//    @GetMapping("/HR/userlist/edit/{id}")
//    public String editUserPage(@PathVariable("id") int id, Model model) {
//        model.addAttribute("userForm", userFormConverter.toForm(userService.findUser(id)));
//        model.addAttribute("user", userRepository.findAll());
//
//        return "HR/editUser";
//    }

    // Speichert Änderungen, wenn valide

//    @PostMapping("/HR/userlist/edit/{id}")
//    public String editUser(@PathVariable("id") int id, @ModelAttribute("userForm") @Valid UserForm form, BindingResult binding, Model model) {
//        if (binding.hasErrors()) {
//            return "HR/editUser";
//        }
//
//        User user = userService.findUser(id);
//        userService.save(userFormConverter.update(user, form));
//        model.addAttribute("user", userService.findAll());
//        return "HR/userlist";
//    }


    // erster Teil zum Ändern des Passwortes durch den User, prüft altes Passwort

    @GetMapping("/editPassword")
    public String editPassword(Model model) {
        model.addAttribute("user", userService.findCurrentUser());
        return "editPassword";
    }

    // prüft, ob Eingabe des alten Passwortes korrekt war

    @PostMapping("/editPassword")
    public String editPassword(@ModelAttribute("user") User user, Model model) {
        PasswordEncoder passencoder = new BCryptPasswordEncoder();
        if (passencoder.matches(user.getPassword(), userService.findCurrentUser().getPassword()) == false) {
            model.addAttribute("error", "Da stimmt etwas nicht. Versuch's nochmal.");
            return "editPassword";
        }
        return "editPassword2";
    }

    // Ändern des eigenen Passwortes durch den Mitarbeiter

    @GetMapping("/editPassword2")
    public String editPassword2(Model model) {
        model.addAttribute("user", userService.findCurrentUser());
        return "editPassword";
    }

    // Speichern des neuen Passwortes, wenn das neue Passwort kein leerer String ist und nicht dem alten Passwort entspricht

    @PostMapping("/editPassword2")
    public String savePassword2(@ModelAttribute("user") User user, Model model) {
        PasswordEncoder passencoder = new BCryptPasswordEncoder();
        if (user.getPassword() == "" || passencoder.matches(user.getPassword(), userService.findCurrentUser().getPassword()) == true) {
            model.addAttribute("error", "Da hat etwas nicht geklappt. Du hast dein altes oder gar kein Passwort eingegeben.");
            return ("editPassword2");
        }
        userRepository.updateUserPassword(encoder.encode(user.getPassword()), userService.findCurrentUser().getId());
        return "index";
    }


    @GetMapping("/HR/userlist/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "/HR/editUser";
    }

    // Speichern des Users, wenn eine Rolle und ein Nutzername vergeben wurden.

    @PostMapping("/HR/userlist/edit/{id}")
    public String saveUser(@ModelAttribute("user") User user, Model model,@PathVariable("id") int id) {
        PasswordEncoder passencoder = new BCryptPasswordEncoder();
//        if (user.getPassword() == "" || passencoder.matches(user.getPassword(), userService.findCurrentUser().getPassword()) == true) {
//            model.addAttribute("error", "Da hat etwas nicht geklappt. Du hast dein altes oder gar kein Passwort eingegeben.");
//            return ("editPassword2");
//        }
        userRepository.updateUserName(user.getName(), userService.findById(id).getId(),user.getRole());
        return "/HR/userlist";
    }

}