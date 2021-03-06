package de.hsba.bi.project.web;

import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.events.EventRepository;
import de.hsba.bi.project.events.EventService;
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
    @Autowired
    private EventService eventService;

    // Erstellung eines neuen Users
    @GetMapping("/HR/createUser")
    public String Form(Model model) {
        model.addAttribute("user", new User());
        List<Role> listRoles = roleService.findAll();
        model.addAttribute("listRoles", listRoles);
        return "HR/createUser";
    }

    // Speichern des neuen Users, wenn valide und der Name noch nicht in der Datenbank vorhanden ist. Wenn Felder nicht gef??llt sind, gibt es eine Fehlermeldung.
    @PostMapping("/HR/createUser")
    public String saveUser(@ModelAttribute("user") @Valid UserForm form, BindingResult result, Model model) {
        List<Role> listRoles = roleService.findAll();
        if (result.hasErrors()) {
            model.addAttribute("listRoles", listRoles);
            return "HR/createUser";
        }
        if (userService.countUsersWithSameName(form.getUserName()) == 1){
            model.addAttribute("error", "Den Benutzernamen gibt es schon.");
            model.addAttribute("listRoles", listRoles);
            return "HR/createUser";
        }
        if (form.getRoles().contains(roleService.findByRole("Terminverwalter")) && form.getRoles().contains(roleService.findByRole("Personalabteilung")) && form.getRoles().contains(roleService.findByRole("Mitarbeiter"))){
            model.addAttribute("error", "Der User kann entweder die Rolle Mitarbeiter haben oder die Rolle Terminverwalter und/ oder Personalabteilung, aber nicht alle 3.");
            model.addAttribute("listRoles", listRoles);
            return "HR/createUser";
        }
        if (form.getRoles().contains(roleService.findByRole("Personalabteilung")) && form.getRoles().contains(roleService.findByRole("Mitarbeiter"))){
            model.addAttribute("error", "Der User kann nicht gleichzeitig die Rolle Mitarbeiter und Personalabteilung haben.");
            model.addAttribute("listRoles", listRoles);
            return "HR/createUser";
        }
        if (form.getRoles().contains(roleService.findByRole("Terminverwalter")) && form.getRoles().contains(roleService.findByRole("Mitarbeiter"))){
            model.addAttribute("error", "Der User kann nicht gleichzeitig die Rolle Mitarbeiter und Terminverwalter haben.");
            model.addAttribute("listRoles", listRoles);
            return "HR/createUser";
        }
        if (form.getPassword().contains(" ")) {
            model.addAttribute("error", "Das Passwort darf keine Leerzeichen enthalten.");
            model.addAttribute("listRoles", listRoles);
            return "HR/createUser";
        }
        if (!form.getPassword().matches(".{4,20}$")) {
            model.addAttribute("error", "Das Passwort muss mindestens 4 und h??chstens 20 Stellen haben.");
            model.addAttribute("listRoles", listRoles);
            return ("HR/createUser");
        }
        User user = userFormConverter.update(new User(), form);
        userService.save(user);
        return "HR/userResult";
    }

    // Anzeigen der User-Liste
    @GetMapping("/HR/userlist")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "HR/userlist";
    }

    // L??schen eines Users. Ist nur m??glich, wenn es sich nicht um den einzigen User mit der Rolle Personalabteilung handelt.
    @GetMapping("/HR/userlist/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        if (userService.checkIfUserHasRoleHR(id) == 1 && userService.countUsersWithRoleHR() == 1) {
            model.addAttribute("error", "Dies ist der einige Mitarbeiter mit der Rolle Personalabteilung. Kein L??schen m??glich.");
            model.addAttribute("users",userService.findAll());
            return "HR/userlist";
        }
        if (userService.findById(id) == userService.findCurrentUser()) {
            model.addAttribute("error2", "Du kannst dich nicht selber l??schen.");
            model.addAttribute("users",userService.findAll());
            return "HR/userlist";
        }
        User user = userService.findById(id);
        eventService.addSpotWhenUserDeleted(user);
        userService.deleteUser(id);
        model.addAttribute("users",userService.findAll());
        return "HR/userlist";
    }

    // Deaktivieren eines Users, wenn es nicht der einzige User mit der Rolle Personalabteilung ist und der angemeldete User sich nicht selber deaktivieren w??rde.
    @GetMapping("/HR/userlist/disable/{id}")
    public String deactiveUser(@PathVariable("id") Integer id, Model model) {
        if (userService.checkIfUserHasRoleHR(id) == 1 && userService.countUsersWithRoleHR() == 1) {
            model.addAttribute("error", "Dies ist der einige Mitarbeiter mit der Rolle Personalabteilung. Kein Deaktivieren m??glich.");
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

    // erster Teil zum ??ndern des Passwortes durch den User, pr??ft altes Passwort
    @GetMapping("/editPassword")
    public String editPassword(Model model) {
        model.addAttribute("user", userService.findCurrentUser());
        return "editPassword";
    }

    // pr??ft, ob Eingabe des alten Passwortes korrekt war
    @PostMapping("/editPassword")
    public String editPassword(@ModelAttribute("user") User user, Model model) {
        PasswordEncoder passencoder = new BCryptPasswordEncoder();
        if (!passencoder.matches(user.getPassword(), userService.findCurrentUser().getPassword())) {
            model.addAttribute("error", "Da stimmt etwas nicht. Versuch's nochmal.");
            return "editPassword";
        }
        return "editPassword2";
    }

    // ??ndern des eigenen Passwortes durch den Mitarbeiter
    @GetMapping("/editPassword2")
    public String editPassword2(Model model) {
        model.addAttribute("user", userService.findCurrentUser());
        return "editPassword";
    }

    // Speichern des neuen Passwortes, wenn das neue Passwort den unten aufgef??hrten Kriterien entspricht
    @PostMapping("/editPassword2")
    public String savePassword2(@ModelAttribute("user") User user, Model model) {
        PasswordEncoder passencoder = new BCryptPasswordEncoder();
        // regul??rer Ausdruck, um Kriterien f??r das Passwort zu ??berpr??fen (siehe editPassword2.html)
        if (!user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()!])(?=\\S+$).{8,20}$")) {
            model.addAttribute("error", "Hinweis: Dein Passwort erf??llt nicht alle Kriterien.");
            return ("editPassword2");
        }
        // Das neu angelegte Passwort darf nicht dem alten Passwort entsprechen
        if (user.getPassword() == "" || passencoder.matches(user.getPassword(), userService.findCurrentUser().getPassword())) {
            model.addAttribute("error", "Da hat etwas nicht geklappt. Du hast anscheinend dein altes Passwort eingegeben.");
            return ("editPassword2");
        }
        // Wenn das neue Passwort allen Anforderungen entspricht, wird es in der Datenbank abgespeichert.
        userService.updateUserPassword(encoder.encode(user.getPassword()), userService.findCurrentUser().getId());
        model.addAttribute("events", eventService.findTop3());
        return "index";
    }

    // Aufrufen der Bearbeiten-Seite f??r einen User
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
        // Wenn der User der einzige User mit Rolle Personalabteilung ist, kann diese nicht ge??ndert werden.
        if (userService.checkIfUserHasRoleHR(id) == 1 && userService.countUsersWithRoleHR() == 1 && !userService.findById(id).getRoles().contains(roleService.findByRole("PERSONALABTEILUNG"))) {
            model.addAttribute("error", "Dies ist der einige Mitarbeiter mit der Rolle Personalabteilung. Kein ??ndern der Rolle m??glich.");
            model.addAttribute("listRoles", listRoles);
            return "HR/editUser";
        }
        // Wenn der User, der bearbeitet werden soll, dem angemeldeten User entspricht, gibt es eine Fehlermeldung. User sollen sich nicht selber bearbeiten k??nnen.
        if (userService.findById(id) == userService.findCurrentUser()) {
            model.addAttribute("error", "Du kannst deine Angaben nicht selber bearbeiten.");
            model.addAttribute("listRoles", listRoles);
            return "HR/editUser";
        }
        // Der Name vom User darf nicht einem Namen entsprechen, den es schon in der Datenbank gibt, wenn es nicht der des angemeldeten Users ist.
        if (userService.countUsersWithSameNameThatAreNotEditedUser(form.getUserName(), id) == 1){
            model.addAttribute("listRoles", listRoles);
            model.addAttribute("error", "Den Benutzernamen gibt es schon.");
            return "HR/editUser";
        }
        if (form.getRoles().contains(roleService.findByRole("Terminverwalter")) && form.getRoles().contains(roleService.findByRole("Personalabteilung")) && form.getRoles().contains(roleService.findByRole("Mitarbeiter"))){
            model.addAttribute("error", "Der User kann entweder die Rolle Mitarbeiter haben oder die Rolle Terminverwalter und/ oder Personalabteilung, aber nicht alle 3.");
            model.addAttribute("listRoles", listRoles);
            return "HR/editUser";
        }
        if (form.getRoles().contains(roleService.findByRole("Personalabteilung")) && form.getRoles().contains(roleService.findByRole("Mitarbeiter"))){
            model.addAttribute("error", "Der User kann nicht gleichzeitig die Rolle Mitarbeiter und Personalabteilung haben.");
            model.addAttribute("listRoles", listRoles);
            return "HR/editUser";
        }
        if (form.getRoles().contains(roleService.findByRole("Terminverwalter")) && form.getRoles().contains(roleService.findByRole("Mitarbeiter"))){
            model.addAttribute("error", "Der User kann nicht gleichzeitig die Rolle Mitarbeiter und Terminverwalter haben.");
            model.addAttribute("listRoles", listRoles);
            return "HR/editUser";
        }
        User user = userFormConverter1.update(userService.findUser(id), form);
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "HR/userlist";
    }

}