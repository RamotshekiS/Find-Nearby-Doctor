package com.selloramotsheki.finddoctors.security.user;

import com.selloramotsheki.finddoctors.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDetails implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private String lastName;
    private String firstName;

    @Getter
    private String role;

    private Collection<GrantedAuthority> authorities;

    public AppUserDetails(Long id, String email, String password, String role, List<GrantedAuthority> authorities) {

        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }


    public static AppUserDetails buildUserDetails(User user) {
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        // Assuming the user has only one role
        String role = user.getRoles().stream()
                .findFirst()
                .map(r -> r.getName())
                .orElse(null);

        return new AppUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),// ✅ Correct
                role,
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return email;
    }



}
