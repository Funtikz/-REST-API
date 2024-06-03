package org.example.restapi.service;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.restapi.dto.ApplicationDto;
import org.example.restapi.entity.Application;
import org.example.restapi.repository.ApplicationRepository;
import org.example.restapi.service.model.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public void deleteApplicationById(Long id) {
        if (applicationRepository.existsById(id)) {
            applicationRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Application with id " + id + " not found");
        }
    }

    public ApplicationDto updateApplication(Long id, ApplicationDto updatedApplicationDto) {
        Optional<Application> optionalApplication = applicationRepository.findById(id);
        if (optionalApplication.isPresent()) {
            Application existingApplication = optionalApplication.get();
            existingApplication.setFirstName(updatedApplicationDto.getFirstName());
            existingApplication.setMiddleName(updatedApplicationDto.getMiddleName());
            existingApplication.setLastName(updatedApplicationDto.getLastName());
            existingApplication.setPhoneNumber(updatedApplicationDto.getPhoneNumber());
            existingApplication.setCouldCounter(updatedApplicationDto.getCouldCounter());
            existingApplication.setHotCounter(updatedApplicationDto.getHotCounter());
            existingApplication.setOrderTime(updatedApplicationDto.getOrderTime());

            Application updatedApplication = applicationRepository.save(existingApplication);
            return mapToDto(updatedApplication);
        } else {
            throw new EntityNotFoundException("Application with id " + id + " not found");
        }
    }

    @Override
    public ApplicationDto createApplication(ApplicationDto applicationDto) {
        Application application = new Application();
        application.setFirstName(applicationDto.getFirstName());
        application.setMiddleName(applicationDto.getMiddleName());
        application.setLastName(applicationDto.getLastName());
        application.setPhoneNumber(applicationDto.getPhoneNumber());
        application.setCouldCounter(applicationDto.getCouldCounter());
        application.setHotCounter(applicationDto.getHotCounter());
        application.setOrderTime(applicationDto.getOrderTime());
        Application savedApplication = applicationRepository.save(application);
        return mapToDto(savedApplication);
    }

    @Override
    public ApplicationDto getApplicationById(Long id) {
        Optional<Application> maybeApplication = applicationRepository.findById(id);
        if (maybeApplication.isPresent()){
            return mapToDto(maybeApplication.get());
        }
        else {
            throw new EntityNotFoundException("Пользователя с " + id + " не существует");
        }
    }

    @Override
    public List<ApplicationDto> getAllApplication() {
        return applicationRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }
    private ApplicationDto mapToDto(Application application) {
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setFirstName(application.getFirstName());
        applicationDto.setMiddleName(application.getMiddleName());
        applicationDto.setLastName(application.getLastName());
        applicationDto.setPhoneNumber(application.getPhoneNumber());
        applicationDto.setCouldCounter(application.getCouldCounter());
        applicationDto.setHotCounter(application.getHotCounter());
        applicationDto.setOrderTime(application.getOrderTime());
        return applicationDto;
    }
}
