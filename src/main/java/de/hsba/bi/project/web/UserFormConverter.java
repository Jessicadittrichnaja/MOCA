package de.hsba.bi.project.web;

import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserFormConverter {
    @Autowired
    private PasswordEncoder Encoder;

    // siehe UserForm-Kommentar

    UserForm toForm(User user) {
        UserForm form = new UserForm();
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setUserName(user.getUserName());
        form.setPassword(user.getPassword());
        form.setRoles(user.getRoles());
        return form;
    }

    User update(User user, UserForm form) {
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setUserName(form.getUserName());
        user.setPassword(Encoder.encode(form.getPassword()));
        user.setRoles(form.getRoles());
        return user;
    }

}

