package org.example.restapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @NotBlank(message = "Поле должно быть заполнено.")
    @Size(min = 2, max = 64)
    @Schema(description = "Имя", example = "Никита")
    @Column(name = "firstName")
    private String firstName;

    @NotBlank(message = "Поле должно быть заполнено.")
    @Size(min = 2, max = 64)
    @Schema(description = "Фамилия", example = "Щербаков")
    @Column(name = "lastName")
    private String lastName;

    @Column(name = "middleName")
    @NotBlank(message = "Поле должно быть заполнено.")
    @Size(min = 2, max = 64)
    @Schema(description = "Отчество", example = "Александрович")
    private String middleName;

    @Column(name = "phoneNumber")
    @NotBlank(message = "Поле должно быть заполнено.")
    @Pattern(regexp = "\\d{11}", message = "Неверно указанный номер телефона.")
    @Schema(description = "Номер телефона с +", example = "89882505363")
    private String phoneNumber;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Application> applications = new ArrayList<>();
}