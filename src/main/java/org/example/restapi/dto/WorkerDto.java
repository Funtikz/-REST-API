package org.example.restapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.restapi.entity.Application;
import java.util.List;

@Data
public class WorkerDto {
    private Long id;

    @NotBlank(message = "Поле должно быть заполнено.")
    @Size(min = 2, max = 64)
    @Schema(description = "Имя", example = "Никита")
    private String firstName;

    @NotBlank(message = "Поле должно быть заполнено.")
    @Size(min = 2, max = 64)
    @Schema(description = "Фамилия", example = "Щербаков")
    private String lastName;

    @NotBlank(message = "Поле должно быть заполнено.")
    @Size(min = 2, max = 64)
    @Schema(description = "Отчество", example = "Александрович")
    private String middleName;

    @NotBlank(message = "Поле должно быть заполнено.")
    @Pattern(regexp = "\\d{11}", message = "Неверно указанный номер телефона.")
    @Schema(description = "Номер телефона с +", example = "89882505363")
    private String phoneNumber;

    private List<Application> applications;
}
