package softuni.bg.pathfinder.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import softuni.bg.pathfinder.model.entity.RoleEntity;
import softuni.bg.pathfinder.model.entity.UserEntity;
import softuni.bg.pathfinder.model.enums.RoleNameEnum;
import softuni.bg.pathfinder.repository.UserRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class PathfinderUserDetailsServiceTest {

    private UserEntity testUser;
    private RoleEntity adminRole, userRole;
    private PathfinderUserDetailsService serviceToTest;


    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init() {
        //ARRANGE
        this.serviceToTest = new PathfinderUserDetailsService(this.mockUserRepository);

        this.adminRole = new RoleEntity();
        this.adminRole.setRole(RoleNameEnum.ADMIN);

        this.userRole = new RoleEntity();
        this.userRole.setRole(RoleNameEnum.USER);


        this.testUser = new UserEntity();
        this.testUser.setUsername("pesho");
        this.testUser.setEmail("pesho@abv.bg");
        this.testUser.setRoles(Set.of(adminRole, userRole));
        this.testUser.setPassword("test");
    }

    @Test
    void testUserNotFound() {
        Assertions.assertThrows(UsernameNotFoundException.class,() -> this.serviceToTest.loadUserByUsername("nqmatakuv"));
    }

    @Test
    void testUserFound() {
        //ARRANGE
        Mockito.when(this.mockUserRepository.findByUsername(this.testUser.getUsername()))
                .thenReturn(Optional.of(this.testUser));

        //ACT
        var actual = this.serviceToTest.loadUserByUsername(this.testUser.getUsername());

        //ASSERT
        Assertions.assertEquals(actual.getUsername(), this.testUser.getUsername());

        String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        String expectedRoles = "ROLE_ADMIN, ROLE_USER";

        Assertions.assertEquals(expectedRoles, actualRoles);


    }

}
