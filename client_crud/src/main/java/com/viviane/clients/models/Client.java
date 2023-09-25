package com.viviane.clients.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "TB_CLIENT")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @NotNull(message = "First name cannot be null.")
    @NotEmpty(message = "First name cannot be empty.")
    private String firstName;

    @NotNull(message = "Last name cannot be null.")
    @NotEmpty(message = "Last name cannot be empty.")
    private String lastName;

    @Past
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @Column(unique = true)
    @NotNull(message = "Email cannot be null.")
    @NotEmpty(message = "Email cannot be empty.")
    @Email(message = "Email should be valid.", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    @Column(unique = true)
    @NotNull(message = "CPF cannot be null.")
    @NotEmpty(message = "CPF cannot be empty.")
    @CPF(message = "CPF should be valid.")
    private String cpf;

}
