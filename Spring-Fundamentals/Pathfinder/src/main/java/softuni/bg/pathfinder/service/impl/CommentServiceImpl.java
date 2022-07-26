package softuni.bg.pathfinder.service.impl;

import org.springframework.stereotype.Service;
import softuni.bg.pathfinder.model.entity.CommentEntity;
import softuni.bg.pathfinder.model.service.CommentServiceModel;
import softuni.bg.pathfinder.model.view.CommentViewModel;
import softuni.bg.pathfinder.repository.CommentRepository;
import softuni.bg.pathfinder.repository.RouteRepository;
import softuni.bg.pathfinder.repository.UserRepository;
import softuni.bg.pathfinder.service.CommentService;
import softuni.bg.pathfinder.service.exceptions.ObjectNotFoundException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final RouteRepository routeRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, RouteRepository routeRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public List<CommentViewModel> getComments(Long routeId) {
        var routeOpt = this.routeRepository.findById(routeId);
        if (routeOpt.isEmpty()) {
            throw new ObjectNotFoundException("Route with id " + routeId + " not found!");
        }


        return routeOpt.get().getComments()
                .stream()
                .map(this::mapAsComment).collect(Collectors.toList());
    }

    @Override
    public CommentViewModel createComment(CommentServiceModel commentServiceModel) {
        var route = this.routeRepository.findById(commentServiceModel.getRouteId()).orElseThrow(() -> new ObjectNotFoundException("Not found!"));

        var author = this.userRepository.findByUsername(commentServiceModel.getCreator()).orElseThrow(() -> new ObjectNotFoundException("Not found!"));

        CommentEntity newComment = new CommentEntity();
        newComment.setApproved(false);
        newComment.setTextContext(commentServiceModel.getMessage());
        newComment.setCreated(LocalDateTime.now());
        newComment.setRoute(route);
        newComment.setAuthor(author);

        CommentEntity saveComment = this.commentRepository.save(newComment);

        return mapAsComment(saveComment);
    }


    private CommentViewModel mapAsComment(CommentEntity commentEntity){
        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel.setId(commentEntity.getId());
        commentViewModel.setCanApprove(true);
        commentViewModel.setCanDelete(true);
        commentViewModel.setCreated(commentEntity.getCreated());
        commentViewModel.setMessage(commentEntity.getTextContext());
        commentViewModel.setUser(commentEntity.getAuthor().getFullName());

        return  commentViewModel;
    }
}
