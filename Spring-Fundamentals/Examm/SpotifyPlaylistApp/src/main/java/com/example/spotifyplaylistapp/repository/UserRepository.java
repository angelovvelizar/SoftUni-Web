package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsernameOrEmail(String username, String email);

    boolean existsByUsernameAndPassword(String username, String password);

    UserEntity findUserEntityByUsername(String username);
}
