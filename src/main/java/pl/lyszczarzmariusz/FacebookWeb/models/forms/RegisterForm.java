package pl.lyszczarzmariusz.FacebookWeb.models.forms;

import lombok.Data;
import lombok.NonNull;
import javax.validation.constraints.Size;

@Data
public class RegisterForm {
    @NonNull
    @Size(min=2, max=45)
    private String firstName;
    @NonNull
    @Size(min=2, max=45)
    private String lastName;
    @NonNull
    @Size(min=8, max=45)
    private String password;
    @NonNull
    @Size(min=8, max=45)
    private String repeatedPassword;
    @NonNull
    @Size(min=7, max=45)
    private String email;
    @NonNull
    private int phoneNumber;

    public RegisterForm() {
    }
}
