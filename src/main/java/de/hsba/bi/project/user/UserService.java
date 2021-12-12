package de.hsba.bi.project.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    // Methoden z.B. zum Finden/ Speichern von Usern

    private final UserRepository userRepository;

    public List<de.hsba.bi.project.user.User> findAll() {
        return userRepository.findAll();
    }

    public List<de.hsba.bi.project.user.User> findUsers() {
        return userRepository.findByRole(User.MITARBEITER_ROLE);
    }

    public de.hsba.bi.project.user.User save(de.hsba.bi.project.user.User user) {
        return userRepository.save(user);
    }

    public de.hsba.bi.project.user.User findCurrentUser() {
        return userRepository.findByName(de.hsba.bi.project.user.User.getCurrentUsername());
    }
}
