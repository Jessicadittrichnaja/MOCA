package de.hsba.bi.project.web;

import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.user.User;
import de.hsba.bi.project.user.UserRepository;
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
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserFormAssembler userFormAssembler;

    // Erstellung eines neuen Users

    @GetMapping("/createUser")
    public String Form(Model model) {
        model.addAttribute("user", new User());
        return "HR/createUser";
    }

    // Speichern des neuen Users, wenn valide und der Name noch nicht in der Datenbank vorhanden ist

    @PostMapping("/createUser")
    public String saveUser(@ModelAttribute("user") User user, @Valid UserForm userForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "HR/createUser";
        }
        if (userRepository.countNumberUsersWithSameName(user.getName()) == 1){
            model.addAttribute("user", userService.findCurrentUser());
            return "HR/createUser";
        }
        userService.save(userFormAssembler.update(user, userForm));
        return "HR/userResult";
    }

}
