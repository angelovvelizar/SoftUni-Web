package softuni.bg.pathfinder.model.binding;

import javax.validation.constraints.*;

public class UserRegisterBindingModel {

    @NotBlank
    @Size(min = 2)
    private String username;
    @NotBlank
    @Size(min = 3)
    private String fullName;
    @NotBlank
    @Email
    private String email;

    @NotNull
    @Min(13)
    private Integer age;
    @NotBlank
    @Size(min = 2)
    private String password;
    @NotBlank
    @Size(min = 2)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
