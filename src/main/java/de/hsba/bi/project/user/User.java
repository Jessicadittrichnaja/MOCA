package de.hsba.bi.project.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    private String firstName;

    @Getter
    @Setter
    @Basic(optional = false)
    private String lastName;

    @Getter
    @Setter
    @Basic(optional = false)
    private String userName;

    @Basic(optional = false)
    private String password;

    @Getter
    @Setter
    private Boolean isEnabled = true;

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
        this.userName = userName;
    }

    public User(String firstName, String lastName, String userName, String password, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public int compareTo(User other) {
        return this.userName.compareTo(other.userName);
    }

    @Override
    public String toString() {
        return userName;
    }

}