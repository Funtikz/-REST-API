package org.example.restapi.service;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.restapi.dto.ApplicationDto;
import org.example.restapi.entity.Application;
import org.example.restapi.entity.Status;
import org.example.restapi.mapper.ApplicationMapper;
import org.example.restapi.repository.ApplicationRepository;
import org.example.restapi.service.model.ApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
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
            ApplicationMapper.INSTANCE.updateFromDto(updatedApplicationDto,existingApplication);
            applicationRepository.save(existingApplication);
            return ApplicationMapper.INSTANCE.toDto(existingApplication);
        } else {
            throw new EntityNotFoundException("Пользователя с id " + id + " не существует");
        }
    }

    @Override
    public ApplicationDto createApplication(ApplicationDto applicationDto) {
        Application application = ApplicationMapper.INSTANCE.toEntity(applicationDto);
        application.setStatus(Status.IN_PROCESSING);
        if (application.getOrderTime() == null){
           application.setOrderTime(LocalDateTime.now());
        }
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
