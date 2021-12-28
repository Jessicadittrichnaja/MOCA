package de.hsba.bi.project.user;

import de.hsba.bi.project.events.Category;
import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.events.Location;
import de.hsba.bi.project.events.Time;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    // Methoden z.B. zum Finden/ Speichern von Usern

    private final UserRepository userRepository;

    public de.hsba.bi.project.user.User save(de.hsba.bi.project.user.User user) {
        return userRepository.save(user);
    }

    public de.hsba.bi.project.user.User findCurrentUser() {
        return userRepository.findByName(de.hsba.bi.project.user.User.getCurrentUsername());
    }


    // Methoden z.B. zum Löschen/ Speichern von Usern

    public User addUser(User user)  {
        user = userRepository.save(user);
        return user;
    }

    public void disableUser(int id)  {
        userRepository.disableUser(id);
    }
    public void enableUser(int id)  {
        userRepository.enableUser(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {

        User user = userRepository.findById(id).orElse(null);

        return user;

    };

    public void removeUser(User user)  {
        userRepository.delete(user);
    }



    public User findUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public Boolean isUserDisabled(Integer id) {
        return userRepository.isUserDisabled(id);
    }

    public Boolean isUserEnsabled(Integer id) {
        return userRepository.isUserEnabled(id);
    }
}
