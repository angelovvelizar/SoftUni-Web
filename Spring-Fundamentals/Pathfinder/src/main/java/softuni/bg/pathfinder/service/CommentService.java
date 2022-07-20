package softuni.bg.pathfinder.service;

import softuni.bg.pathfinder.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {
    List<CommentViewModel> getComments(Long routeId);
}
