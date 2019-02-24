package net.rhm.appuser.model.entity;

import lombok.Data;
import net.rhm.appuser.model.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name="app_company")
public class Company extends AuditModel {

    @Id
    @GeneratedValue(generator = "company_generator")
    @SequenceGenerator(
            name = "company_generator",
            sequenceName = "company_sequence",
            initialValue = 100
    )
    private Long id;

    @NotBlank
    @Column(name="name")
    private String name;

    @NotBlank
    @Column(name="identification",nullable = false, unique = true)
    private String identification;

}
