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
        form.setName(user.getName());
        form.setPassword(user.getPassword());
        form.setRole(user.getRole());
        return form;
    }

    User update(User user, UserForm form) {
        user.setName(form.getName());
        user.setPassword(Encoder.encode(form.getPassword()));
        user.setRole(form.getRole());

        return user;
    }

}

