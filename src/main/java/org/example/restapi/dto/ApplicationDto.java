package org.example.restapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ApplicationDto {
    @NotBlank
    @Size(min = 2, max = 64)
    String firstName;
    @NotBlank
    @Size(min = 2, max = 64)
    String middleName;
    @NotBlank
    @Size(min = 2, max = 64)
    String lastName;
    @Positive
    Integer couldCounter;
    @Positive
    Integer hotCounter;
    LocalDateTime orderTime;
}
