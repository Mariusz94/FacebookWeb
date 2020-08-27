package pl.lyszczarzmariusz.FacebookWeb.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_info")
@Data
public class UserInfoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int userId;
    Date dateOfBirth;
    Gender gender;
    RelationshipStatus relationshipStatus;
    School school;
    Work work;
    String placeOfResidence;


}
