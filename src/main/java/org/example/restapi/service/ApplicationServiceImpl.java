package org.example.restapi.service;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.restapi.dto.ApplicationDto;
import org.example.restapi.entity.Application;
import org.example.restapi.mapper.ApplicationMapper;
import org.example.restapi.repository.ApplicationRepository;
import org.example.restapi.service.model.ApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
@Validated
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public void deleteApplicationById(Long id) {
        if (applicationRepository.existsById(id)) {
            applicationRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Пользователя с id " + id + " не существует");
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
            return ApplicationMapper.INSTANCE.toDto(updatedApplication);
        } else {
            throw new EntityNotFoundException("Пользователя с id " + id + " не существует");
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
        return ApplicationMapper.INSTANCE.toDto(savedApplication);
    }

    @Override
    public ApplicationDto getApplicationById(Long id) {
        Optional<Application> maybeApplication = applicationRepository.findById(id);
        if (maybeApplication.isPresent()){
            return ApplicationMapper.INSTANCE.toDto(maybeApplication.get());
        }
        else {
            throw new EntityNotFoundException("Пользователя с id " + id + " не существует");
        }
    }

    @Override
    public List<ApplicationDto> getAllApplication() {
        return applicationRepository.findAll().stream()
                .map(ApplicationMapper.INSTANCE::toDto)
                .toList();
    }

}
