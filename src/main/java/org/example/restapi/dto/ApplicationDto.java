package org.example.restapi.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApplicationDto {
    String firstName;
    String middleName;
    String lastName;
    Integer couldCounter;
    Integer hotCounter;
}
