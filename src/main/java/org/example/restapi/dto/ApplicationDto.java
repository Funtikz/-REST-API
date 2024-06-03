package org.example.restapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ApplicationDto {
    @NotBlank(message = "Поле должно быть заполнено.")
    @Size(min = 2, max = 64)
    private String firstName;

    @NotBlank(message = "Поле должно быть заполнено.")
    @Size(min = 2, max = 64)
    private String middleName;

    @NotBlank(message = "Поле должно быть заполнено.")
    @Pattern(regexp = "\\d{10}", message = "Неверно указанный номер телефона.")
    private String phoneNumber;

    @NotBlank(message = "Поле должно быть заполнено.")
    @Size(min = 2, max = 64)
    private String lastName;

    @NotBlank(message = "Поле должно быть заполнено.")
    @Positive(message = "Количество счетчиков должно быть больше 0")
    private Integer couldCounter;

    @NotBlank(message = "Поле должно быть заполнено.")
    @Positive(message = "Количество счетчиков должно быть больше 0")
    private Integer hotCounter;

    private LocalDateTime orderTime;
}
