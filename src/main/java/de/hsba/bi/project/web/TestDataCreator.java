package de.hsba.bi.project.web;

import de.hsba.bi.project.user.User;
import de.hsba.bi.project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TestDataCreator {

    // Anlegen von Testusern

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationStartedEvent.class)
    @Transactional
    public void init() {
        // add some users
        User enrico = createUser("Enrico", "password", User.MITARBEITER_ROLE);
        User jessica = createUser("Jessica", "password", User.PERSONALABTEILUNG_ROLE);
        User fynn = createUser("Fynn", "password", User.TERMINVERWALTER_ROLE);
        User daniel = createUser("Daniel", "password", User.MITARBEITER_ROLE);
    }

    private User createUser(String name, String password, String role) {
        return userService.save(new User(name, passwordEncoder.encode(password), role));
    }
}
