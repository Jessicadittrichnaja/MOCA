package de.hsba.bi.project.web;

import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.events.EventRepository;
import de.hsba.bi.project.roles.Role;
import de.hsba.bi.project.roles.RoleRepository;
import de.hsba.bi.project.roles.RoleService;
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

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserFormConverter userFormConverter;
    @Autowired
    private UserFormConverter1 userFormConverter1;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EventRepository eventRepository;

    // Erstellung eines neuen Users

    @GetMapping("/HR/createUser")
    public String Form(Model model) {
        model.addAttribute("user", new User());
        List<Role> listRoles = roleService.findAll();
        model.addAttribute("listRoles", listRoles);
        return "HR/createUser";
    }

    // Speichern des neuen Users, wenn valide und der Name noch nicht in der Datenbank vorhanden ist. Wenn Felder nicht gefüllt sind, gibt es eine Fehlermeldung.

    @PostMapping("/HR/createUser")
    public String saveUser(@ModelAttribute("user") @Valid UserForm form, BindingResult result, Model model) {
        List<Role> listRoles = roleService.findAll();
        if (result.hasErrors()) {
            model.addAttribute("listRoles", listRoles);
            return "HR/createUser";
        }
        User user = userFormConverter.update(new User(), form);
        if (userRepository.countNumberUsersWithSameName(user.getName()) == 1){
            model.addAttribute("error", "Den User gibt es schon.");
            model.addAttribute("listRoles", listRoles);
            return "HR/createUser";
        }
        userService.save(user);
        return "HR/userResult";
    }
    @GetMapping("/HR/userlist")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "HR/userlist";
    }

    // Löschen eines Users. Ist nur möglich, wenn es sich nicht um den einzigen User mit der Rolle Personalabteilung handelt.

    @GetMapping("/HR/userlist/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        if (userRepository.checkIfUserHasRoleHR(id) == 1 && userRepository.countNumberUsersWithRoleHR() == 1) {
            model.addAttribute("error", "Dies ist der einige Mitarbeiter mit der Rolle Personalabteilung. Kein Löschen möglich.");
            model.addAttribute("users",userService.findAll());
            return "HR/userlist";
        }
        if (userService.findById(id) == userService.findCurrentUser()) {
            model.addAttribute("error2", "Du kannst dich nicht selber löschen.");
            model.addAttribute("users",userService.findAll());
            return "HR/userlist";
        }
        User user = userService.findById(id);
        eventRepository.addSpotWhenUserDeleted(user);
        userRepository.deleteUser(id);
        model.addAttribute("users",userService.findAll());
        return "HR/userlist";
    }
    // Deaktivieren eines Users, wenn es nicht der einzige User mit der Rolle Personalabteilung ist und der angemeldete User sich nicht selber deaktivieren würde.
    @GetMapping("/HR/userlist/disable/{id}")
    public String deactiveUser(@PathVariable("id") Integer id, Model model) {
        if (userRepository.checkIfUserHasRoleHR(id) == 1 && userRepository.countNumberUsersWithRoleHR() == 1) {
            model.addAttribute("error", "Dies ist der einige Mitarbeiter mit der Rolle Personalabteilung. Kein Deaktivieren möglich.");
            model.addAttribute("users",userService.findAll());
            return "HR/userlist";
        }
        if (userService.findById(id) == userService.findCurrentUser()) {
            model.addAttribute("error2", "Du kannst dich nicht selber deaktivieren.");
            model.addAttribute("users",userService.findAll());
            return "HR/userlist";
        }
        userService.disableUser(id);

        return "redirect:/HR/userlist";
    }

    // Aktivieren eines Users
    @GetMapping("/HR/userlist/enable/{id}")
    public String activeUser(@PathVariable("id") Integer id, Model model) {
        userService.enableUser(id);

        return "redirect:/HR/userlist";
    }


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
        if (!passencoder.matches(user.getPassword(), userService.findCurrentUser().getPassword())) {
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
        // Das neu angelegte Passwort muss mindestens 8 Stellen lang sein.
        if (!user.getPassword().matches(".{8,}")){
            model.addAttribute("error", "Hinweis: Dein Passwort muss mindestens 8 Stellen lang sein ");
            return ("editPassword2");
        };

        // Das neu angelegte Passwort darf keine Leerzeichen enthalten.
        if (user.getPassword().contains(" ")){
            model.addAttribute("error", "Hinweis: Dein Passwort darf keine Leerzeichen enthalten ");
            return ("editPassword2");
        }

        // Das neu angelegte Passwort muss mindestens eine Zahl enthalten.
        if (!user.getPassword().matches(".*\\d.*")){
            model.addAttribute("error", "Hinweis: Dein Passwort muss eine Zahl enthalten ");
            return ("editPassword2");
        };

        // Das neu angelegte Passwort muss mindestens ein Sonderzeichen enthalten.
        if (!user.getPassword().matches(".*?[#?!@$%^&*-]")){
            model.addAttribute("error", "Hinweis: Dein Passwort muss mindestens ein Sonderzeichen enthalten ");
            return ("editPassword2");
        };

        // Das neu angelegte Passwort muss mindestens einen Großbuchstaben enthalten.
        if (!user.getPassword().chars().anyMatch(Character::isUpperCase)){
            model.addAttribute("error", "Hinweis: Dein Passwort muss mindestens einen Großbuchstaben enthalten. ");
            return ("editPassword2");
        }

        // Das neu angelegte Passwort muss mindestens einen Kleinbuchstaben enthalten.
        if (!user.getPassword().chars().anyMatch(Character::isLowerCase)){
            model.addAttribute("error", "Hinweis: Dein Passwort muss mindestens einen Kleinbuchstaben enthalten. ");
            return ("editPassword2");
        }

        // Das neu angelegte Passwort darf nicht dem alten Passwort entsprechen
        if (user.getPassword() == "" || passencoder.matches(user.getPassword(), userService.findCurrentUser().getPassword())) {
            model.addAttribute("error", "Da hat etwas nicht geklappt. Du hast dein altes oder gar kein Passwort eingegeben.");
            return ("editPassword2");
        }

        // Wenn das neue Passwort allen Anforderungen entspricht, wird es in der Datenbank abgespeichert.
        userRepository.updateUserPassword(encoder.encode(user.getPassword()), userService.findCurrentUser().getId());
        return "index";
    }


    @GetMapping("/HR/userlist/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.findById(id));
        List<Role> listRoles = roleService.findAll();
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("userForm1", userFormConverter1.toForm(userService.findUser(id)));
        return "HR/editUser";
    }

    // Speichern des Users, wenn eine Rolle und ein Nutzername vergeben wurden.

    @PostMapping("/HR/userlist/edit/{id}")
    public String saveUser(@ModelAttribute("userForm1") @Valid UserForm1 form, BindingResult binding, Model model,@PathVariable("id") int id) {
        List<Role> listRoles = roleService.findAll();
        if (binding.hasErrors()) {
            model.addAttribute("listRoles", listRoles);
            return "HR/editUser";
        }
        // Wenn der User der einzige User mit Rolle Personalabteilung ist, kann diese nicht geändert werden.

        if (userRepository.checkIfUserHasRoleHR(id) == 1 && userRepository.countNumberUsersWithRoleHR() == 1 && !userService.findById(id).getRoles().contains(roleRepository.findByRole("PERSONALABTEILUNG"))) {
            model.addAttribute("error2", "Dies ist der einige Mitarbeiter mit der Rolle Personalabteilung. Kein Ändern der Rolle möglich.");
            model.addAttribute("listRoles", listRoles);
            return "HR/editUser";
        }

        // Wenn der User, der bearbeitet werden soll, dem angemeldeten User entspricht, gibt es eine Fehlermeldung. User sollen sich nicht selber bearbeiten können.

        if (userService.findById(id) == userService.findCurrentUser()) {
            model.addAttribute("error3", "Du kannst deine Angaben nicht selber bearbeiten.");
            model.addAttribute("listRoles", listRoles);
            return "HR/editUser";
        }
        User user = userFormConverter1.update(userService.findUser(id), form);
        if (userRepository.countNumberUsersWithSameNameThatAreNotEditedUser(user.getName(), user.getId()) == 1){
            model.addAttribute("listRoles", listRoles);
            model.addAttribute("error4", "Den User gibt es schon.");
            return "HR/editUser";
        }
        /*if(user.getRoles().size() == 0) {
            model.addAttribute("error5", "Bitte wähle mindestens eine Rolle aus.");
        */
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/HR/userlist";
    }

}