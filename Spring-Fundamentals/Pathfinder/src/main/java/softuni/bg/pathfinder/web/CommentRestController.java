package softuni.bg.pathfinder.web;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import softuni.bg.pathfinder.model.binding.NewCommentBindingModel;
import softuni.bg.pathfinder.model.service.CommentServiceModel;
import softuni.bg.pathfinder.model.validation.ApiError;
import softuni.bg.pathfinder.model.view.CommentViewModel;
import softuni.bg.pathfinder.service.CommentService;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentRestController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentRestController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/{routeId}/comments")
    public ResponseEntity<List<CommentViewModel>> getComments(@PathVariable Long routeId, Principal principal){
        return ResponseEntity.ok(this.commentService.getComments(routeId));
    }

    @PostMapping("/api/{routeId}/comments")
    public ResponseEntity<CommentViewModel> newComment(@PathVariable Long routeId,
                                                       Principal principal,
                                                       @RequestBody @Valid NewCommentBindingModel newCommentBindingModel){
        CommentServiceModel commentServiceModel = this.modelMapper.map(newCommentBindingModel, CommentServiceModel.class);
        commentServiceModel.setRouteId(routeId);

        CommentViewModel commentViewModel = commentService.createComment(commentServiceModel);

        URI location = URI.create(String.format("/api/%s/comments/%s", routeId, commentViewModel.getId()));

        return ResponseEntity.created(location).body(commentViewModel);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exc){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe -> apiError.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(apiError);
    }
}
