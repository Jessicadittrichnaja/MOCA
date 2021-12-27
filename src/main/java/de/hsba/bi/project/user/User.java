package de.hsba.bi.project.user;

import javax.persistence.*;
import de.hsba.bi.project.bookingProcess.Booking;
import de.hsba.bi.project.events.Event;
import de.hsba.bi.project.roles.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements Comparable<User> {

    // um angemeldeten User zu ermitteln

    public static String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            ((UserDetails) principal).getUsername();
        }
        return ((UserDetails) principal).getUsername();
    }


    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String password;

    @Getter
    @Setter
    private Boolean isDeactive = false;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Getter
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String password, Set<Role> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public int compareTo(User other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }

}