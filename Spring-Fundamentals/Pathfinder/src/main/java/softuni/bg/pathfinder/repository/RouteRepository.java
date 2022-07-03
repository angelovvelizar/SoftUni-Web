package softuni.bg.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.bg.pathfinder.model.entity.RouteEntity;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
    boolean existsByName(String name);
}
