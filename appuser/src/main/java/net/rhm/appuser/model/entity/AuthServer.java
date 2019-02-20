package net.rhm.appuser.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.rhm.appuser.model.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="app_authserver")
public class AuthServer extends AuditModel {

    @Id
    @GeneratedValue(generator = "auth_generator")
    @SequenceGenerator(
            name = "auth_generator",
            sequenceName = "auth_sequence",
            initialValue = 1000
    )
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @NotBlank
    private String protocol;
    private String address;
}
