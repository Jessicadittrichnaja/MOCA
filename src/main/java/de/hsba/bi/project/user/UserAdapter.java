package de.hsba.bi.project.user;

import de.hsba.bi.project.roles.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@RequiredArgsConstructor
class UserAdapter implements UserDetails {

    private final de.hsba.bi.project.user.User user;

    // Entsprechend der vergebenen Rollen, kann ein User verschiedene Berechtigungen erhalten. Da ein User mehrere Rollen haben kann, wird mit einer for-Schleife über die Rollen iteriert.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }

    // Verschiedene Methoden zum Finden/Überprüfen von Usern

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        return user.getIsEnabled();
    }

}
