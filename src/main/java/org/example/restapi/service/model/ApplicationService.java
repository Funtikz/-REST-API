package org.example.restapi.service.model;

import org.example.restapi.dto.ApplicationDto;
import org.example.restapi.entity.Status;

import java.util.List;

public interface ApplicationService {
    void deleteApplicationById(Long id);
    ApplicationDto updateApplication(Long id, ApplicationDto updatedApplicationDto);
    ApplicationDto createApplication(ApplicationDto application);
    ApplicationDto getApplicationById(Long id);
    List<ApplicationDto> getAllApplication();
    List<ApplicationDto> getByFio(String firstName, String lastName, String middleName);
    List<ApplicationDto> getByStatus(Status status);
    List<ApplicationDto> getByNumberPhone(String phoneNumber);
}
