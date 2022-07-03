package com.example.musicexam.repository;

import com.example.musicexam.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


   boolean existsByUsername(String username);

   boolean existsByEmail(String email);

   boolean existsByUsernameAndPassword(String username, String password);

   UserEntity findUserEntityByUsername(String username);
}
