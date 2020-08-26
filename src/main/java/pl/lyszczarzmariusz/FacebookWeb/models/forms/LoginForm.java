package pl.lyszczarzmariusz.FacebookWeb.models.forms;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Data
public class LoginForm {
    @NonNull
    @Size(min=7, max=45)
    private String email;
    @NonNull
    @Size(min=7, max=45)
    private String password;

    public LoginForm() {
    }
}
