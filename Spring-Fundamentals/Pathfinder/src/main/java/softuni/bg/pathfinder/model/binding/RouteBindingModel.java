package softuni.bg.pathfinder.model.binding;

import org.springframework.web.multipart.MultipartFile;
import softuni.bg.pathfinder.model.enums.CategoryNameEnum;
import softuni.bg.pathfinder.model.enums.LevelEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class RouteBindingModel {

    @NotBlank
    @Size(min = 4)
    private String name;

    @NotBlank
    @Size(min = 6)
    private String description;

    private MultipartFile gpxCoordinates;
    @NotNull
    private LevelEnum level;
    private String videoUrl;
    private Set<CategoryNameEnum> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Set<CategoryNameEnum> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryNameEnum> categories) {
        this.categories = categories;
    }
}
