package de.hsba.bi.project.userProcess;

import de.hsba.bi.project.bookingProcess.BookingRepository;
import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.roles.Role;
import de.hsba.bi.project.user.User;
import de.hsba.bi.project.user.UserRepository;
import de.hsba.bi.project.user.UserService;
import de.hsba.bi.project.web.UserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.management.Attribute;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

 public class UserTest2{
    Event event;
    User user;
    UserService userService;
    BookingRepository bookingRepository;
    UserController userController;
    UserRepository userRepository;

    @Autowired
    UserRepository underTest;

    @Test
    @DisplayName("Creating new User with chosen role should work")
    void testFindUser(){
        // given
        Role roleEmployee = new Role ("Mitarbeiter");
        Set<Role> rolesMartina = new HashSet<>();
        User user = new User("Martina","Password123$",rolesMartina);
        rolesMartina.add(roleEmployee);
        // when
        User result = underTest.findByName("Martina");
        // then
        assertThat(result).isEqualTo(user);
    }

}