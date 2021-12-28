package de.hsba.bi.project.UserCreationProcess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.hsba.bi.project.roles.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.user.User;
import de.hsba.bi.project.user.UserService;
import de.hsba.bi.project.bookingProcess.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class UserTest {

    Event event;
    User user;
    UserService userService;
    BookingRepository bookingRepository;


    @Autowired
    BookingRepository underTest;

    @Test
    @DisplayName("Creating new User with chosen role should work")
    void testCreateUserWithRoleEmployee() {
        // given
        Role roleEmployee = new Role ("Mitarbeiter");
        Set<Role> rolesEnrico = new HashSet<>();
        rolesEnrico.add(roleEmployee);
        user  = new User("Enrico", "password", rolesEnrico);
        // when
        Set<Role> roles = user.getRoles();
        // then
        assertThat(roles).isEqualTo(rolesEnrico);

    }

}