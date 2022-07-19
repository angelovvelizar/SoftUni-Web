package softuni.bg.pathfinder.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import softuni.bg.pathfinder.model.entity.UserEntity;
import softuni.bg.pathfinder.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PathfinderUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public PathfinderUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userEntity = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with " + username + " not found!"));

        return map(userEntity);
    }

    private UserDetails map(UserEntity userEntity) {

        Set<GrantedAuthority> grantedAuthorities = userEntity.getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).collect(Collectors.toUnmodifiableSet());

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                grantedAuthorities
        );
    }

}
