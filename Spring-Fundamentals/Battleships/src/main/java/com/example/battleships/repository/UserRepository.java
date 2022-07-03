package com.example.battleships.repository;

import com.example.battleships.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String username);

    boolean existsByUsernameAndPassword(String username, String password);

    UserEntity findUserEntityByUsernameAndPassword(String username, String password);
}
