package de.hsba.bi.project.roleCreationProcess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.hsba.bi.project.roles.Role;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.user.User;
import de.hsba.bi.project.user.UserService;
import de.hsba.bi.project.bookingProcess.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class RoleTest {

    Event event;
    User user;
    UserService userService;
    BookingRepository bookingRepository;


    @Autowired
    BookingRepository underTest;

    @Test
    @DisplayName("Creating roles and assigning to users should work")
    void testCreateRolesAndAssignToUser() {
        // given
        Role roleHR = new Role ("Personalabteilung");
        Role roleEventPlanner = new Role ("Terminverwalter");
        Role roleEmployee = new Role ("Mitarbeiter");
        // when
        Set<Role> rolesJessica = new HashSet<>();
        rolesJessica.add(roleEventPlanner);
        rolesJessica.add(roleHR);
        rolesJessica.add(roleEmployee);
        // then
        Set<Role> rolesTest = new HashSet<>();
        rolesTest.add(new Role ("Personalabteilung"));
        rolesTest.add(new Role ("Terminverwalter"));
        rolesTest.add(new Role ("Mitarbeiter"));
        assertThat(rolesJessica, containsInAnyOrder(rolesTest));
        System.out.printf("%s entspricht %s.", rolesTest, rolesJessica);
    }

    private void assertThat(Set<Role> rolesJessica, Matcher<Iterable<? extends Set<Role>>> containsInAnyOrder) {
    }

}