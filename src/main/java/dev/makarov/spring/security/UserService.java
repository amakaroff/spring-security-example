package dev.makarov.spring.security;

import dev.makarov.spring.security.entity.Role;
import dev.makarov.spring.security.entity.User;
import dev.makarov.spring.security.repository.RoleRepository;
import dev.makarov.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public void registerUser(User user, boolean isAdmin) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(isAdmin ? getAdminRole() : getUserRole()));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByName(username);
        return userOptional
                .map(this::toSpringUser)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private org.springframework.security.core.userdetails.User toSpringUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                user.getRoles()
        );
    }

    private Role getUserRole() {
        return getRole("ROLE_USER");
    }

    private Role getAdminRole() {
        return getRole("ROLE_ADMIN");
    }

    private Role getRole(String roleName) {
        Optional<Role> roleUser = roleRepository.findByName(roleName);
        if (!roleUser.isPresent()) {
            Role role = new Role(roleName);
            roleRepository.save(role);

            return role;
        }

        return roleUser.get();
    }
}
