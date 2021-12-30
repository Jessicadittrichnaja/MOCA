package de.hsba.bi.project.web;

import de.hsba.bi.project.roles.Role;
import de.hsba.bi.project.roles.RoleRepository;
import de.hsba.bi.project.user.User;
import de.hsba.bi.project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TestDataCreator {

    // Anlegen von Rollen und Test-Usern beim Start der Anwendung

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @EventListener(ApplicationStartedEvent.class)
    @Transactional

    public void init() {

        Role roleEmployee = new Role ("Mitarbeiter");
        Role roleHR = new Role ("Personalabteilung");
        Role roleEventPlanner = new Role ("Terminverwalter");
        roleRepository.save(roleEmployee);
        roleRepository.save(roleHR);
        roleRepository.save(roleEventPlanner);

        Set<Role> rolesEnrico = new HashSet<>();
        rolesEnrico.add(roleEmployee);
        rolesEnrico.add(roleEventPlanner);

        Set<Role> rolesJessica = new HashSet<>();
        rolesJessica.add(roleHR);
        rolesJessica.add(roleEventPlanner);

        Set<Role> rolesFynn = new HashSet<>();
        rolesFynn.add(roleEventPlanner);

        Set<Role> rolesDaniel = new HashSet<>();
        rolesDaniel.add(roleEmployee);


       // add some users
        User enrico = createUser("Enrico", "password", rolesEnrico);
        User jessica = createUser("Jessica", "password", rolesJessica);
        User fynn = createUser("Fynn", "password", rolesFynn);
        User daniel = createUser("Daniel", "password", rolesDaniel);
    }

    private User createUser(String name, String password, Set<Role> roles) {
        return userService.save(new User(name, passwordEncoder.encode(password), roles));


    }

}
