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

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    // Methoden z.B. zum Finden/ Speichern von Usern

    private final UserRepository userRepository;


//    public List<de.hsba.bi.project.user.User> findAll() {
//        return userRepository.findAll();
//    }

    public List<de.hsba.bi.project.user.User> findUsers() {
        return userRepository.findByRole(User.MITARBEITER_ROLE);
    }

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

    public void deactiveUser(int id)  {
        userRepository.deactiveUser(id);
    }
    public void activeUser(int id)  {
        userRepository.activeUser(id);
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
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

    public Boolean isUserDeactive(Integer id) {return userRepository.isUserDeactive(id);}
    public Boolean isUserActive(Integer id) {return userRepository.isUserActive(id);}
}
