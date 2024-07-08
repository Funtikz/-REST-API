package org.example.restapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;
import org.example.restapi.entity.Status;
import org.example.restapi.entity.Worker;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ApplicationDto {
    @NotBlank(message = "Поле должно быть заполнено.")
    @Size(min = 2, max = 64)
    @Schema(description = "Имя", example = "Никита")
    private String firstName;

    @NotBlank(message = "Поле должно быть заполнено.")
    @Size(min = 2, max = 64)
    @Schema(description = "Фамилия", example = "Щербаков")
    private String middleName;

    @NotBlank(message = "Поле должно быть заполнено.")
    @Size(min = 2, max = 64)
    @Schema(description = "Отчество", example = "Александрович")
    private String lastName;

    @NotBlank(message = "Поле должно быть заполнено.")
    @Pattern(regexp = "\\d{11}", message = "Неверно указанный номер телефона.")
    @Schema(description = "Номер телефона с +", example = "89882505363")
    private String phoneNumber;

    @Positive(message = "Количество счетчиков должно быть больше 0")
    @Schema(description = "Количество счетчиков холодной воды", example = "1")
    private Integer couldCounter;

    @Positive(message = "Количество счетчиков должно быть больше 0")
    @Schema(description = "Количество счетчиков горячей воды", example = "1")
    private Integer hotCounter;

    @Schema(description = "Текущее время. Может быть пустым поставиться время по серверу")
    private LocalDateTime orderTime;

    @NotBlank
    @Schema(description = "Адрес", example = "Город Волгодонск Гагарина 2 кв 40")
    private String address;

    @Schema(description = "Не обязателен! По дефолту ставится IN_PROCESSING, в случае завершения ставится PROCESSED")
    private Status status;

    @Schema(description = "Какое либо дополнение пользователя", example = "Могу сегодня в 12:30")
    private String description;

    @Schema(description = "Рабочий который потом привяжется к заявке", example = "null")
    private Worker worker;


}
