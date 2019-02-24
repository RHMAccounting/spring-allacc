package net.rhm.appuser.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.rhm.appuser.model.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="app_user")
public class User extends AuditModel {

    @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;


    @Email
    private String email;

    @Column(name="user_name")
    private String userName;




}