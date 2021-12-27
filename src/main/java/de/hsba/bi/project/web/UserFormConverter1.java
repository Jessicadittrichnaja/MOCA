package de.hsba.bi.project.web;

import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.Valid;


@Component
public class UserFormConverter1 {
    @Autowired
    private PasswordEncoder Encoder;

    // siehe UserForm1-Kommentar

    UserForm toForm(User user) {
        UserForm form = new UserForm();
        form.setName(user.getName());
        form.setRoles(user.getRoles());
        return form;
    }

    User update(User user, @Valid UserForm1 form) {
        user.setName(form.getName());
        user.setRoles(form.getRoles());
        return user;
    }

}

