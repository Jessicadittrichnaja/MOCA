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


    @GetMapping("/createUser")
    public String Form(Model model) {
        model.addAttribute("user", new User());
        return "createUser";
    }

    @PostMapping("/createUser")
    public String saveEvent(@ModelAttribute("user") @Valid UserForm userForm, BindingResult result) {
        if (result.hasErrors()) {
            return "createUser";
        }
        userService.save(userFormAssembler.update(new User(), userForm));
        return "userResult";
    }
}
