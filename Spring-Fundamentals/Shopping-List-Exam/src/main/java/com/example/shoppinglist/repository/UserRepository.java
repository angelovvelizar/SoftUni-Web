package com.example.shoppinglist.repository;

import com.example.shoppinglist.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByUsernameAndPassword(String username, String password);

    UserEntity findUserEntityByUsernameAndPassword(String username, String password);
}
