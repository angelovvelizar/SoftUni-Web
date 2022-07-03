package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.StyleEntity;
import com.example.spotifyplaylistapp.model.enums.StyleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<StyleEntity, Long> {

    StyleEntity findByName(StyleNameEnum name);
}
