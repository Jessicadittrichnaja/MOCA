package de.hsba.bi.project.roles;

import de.hsba.bi.project.events.Category;
import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.events.Location;
import de.hsba.bi.project.user.User;
import de.hsba.bi.project.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class RoleService {

    // Methoden z.B. zum Finden/ Speichern von Usern

    private final RoleRepository roleRepository;

    public de.hsba.bi.project.roles.Role save(de.hsba.bi.project.roles.Role role) {
        return roleRepository.save(role);
    }

    // Methoden z.B. zum Löschen/ Speichern von Usern

    public Role addRole(Role role)  {
        role = roleRepository.save(role);
        return role;
    }

    public List<Role> findAll() {
        return (List<Role>) roleRepository.findAll();
    }

    public Role findById(Integer id) {
        Role role = roleRepository.findById(id).orElse(null);
        return role;
    }

    public Role findByRole(String roleUser) {
        Role role = roleRepository.findByRole(roleUser);
        return role;
    }

    public void removeRole(Role role)  {
        roleRepository.delete(role);
    }

    public Role findRole(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }
}
