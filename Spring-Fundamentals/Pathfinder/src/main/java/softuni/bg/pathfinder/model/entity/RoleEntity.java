package softuni.bg.pathfinder.model.entity;

import softuni.bg.pathfinder.model.enums.RoleNameEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private RoleNameEnum role;

    public RoleNameEnum getRole() {
        return role;
    }

    public void setRole(RoleNameEnum name) {
        this.role = name;
    }
}
