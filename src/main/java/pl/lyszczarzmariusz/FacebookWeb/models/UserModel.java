package pl.lyszczarzmariusz.FacebookWeb.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import pl.lyszczarzmariusz.FacebookWeb.models.forms.RegisterForm;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String password;
    private String email;
    @Column(name = "phone_number")
    private int phoneNumber;
    @CreationTimestamp
    @Column(name = "day_of_register")
    private Timestamp dayOfRegister;

    public static UserModel createUser(RegisterForm registerForm){
        UserModel userModel = new UserModel();
        userModel.setFirstName(registerForm.getFirstName());
        userModel.setLastName(registerForm.getLastName());
        userModel.setPassword(registerForm.getPassword());
        userModel.setEmail(registerForm.getEmail());
        userModel.setPhoneNumber(registerForm.getPhoneNumber());
        return userModel;
    }
}
