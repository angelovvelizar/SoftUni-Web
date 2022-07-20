package softuni.bg.pathfinder.service.impl;

import org.springframework.stereotype.Service;
import softuni.bg.pathfinder.model.entity.CommentEntity;
import softuni.bg.pathfinder.model.view.CommentViewModel;
import softuni.bg.pathfinder.repository.CommentRepository;
import softuni.bg.pathfinder.repository.RouteRepository;
import softuni.bg.pathfinder.service.CommentService;
import softuni.bg.pathfinder.service.exceptions.ObjectNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final RouteRepository routeRepository;

    public CommentServiceImpl(CommentRepository commentRepository, RouteRepository routeRepository) {
        this.commentRepository = commentRepository;
        this.routeRepository = routeRepository;
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
