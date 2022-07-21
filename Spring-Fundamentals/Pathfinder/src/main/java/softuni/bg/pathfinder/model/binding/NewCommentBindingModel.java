package softuni.bg.pathfinder.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewCommentBindingModel {

    @NotBlank
    @Size(min = 5)
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
