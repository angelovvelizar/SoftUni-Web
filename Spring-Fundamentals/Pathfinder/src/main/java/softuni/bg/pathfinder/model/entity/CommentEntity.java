package softuni.bg.pathfinder.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{

    @Column
    private boolean approved;

    @Column
    private LocalDateTime created;

    @Column(name = "text_context", columnDefinition = "TEXT")
    private String textContext;

    @ManyToOne
    private UserEntity author;

    @ManyToOne
    private RouteEntity route;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getTextContext() {
        return textContext;
    }

    public void setTextContext(String textContext) {
        this.textContext = textContext;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public RouteEntity getRoute() {
        return route;
    }

    public void setRoute(RouteEntity route) {
        this.route = route;
    }
}
