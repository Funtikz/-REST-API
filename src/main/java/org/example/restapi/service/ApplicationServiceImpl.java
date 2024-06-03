package org.example.restapi.service;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.restapi.entity.Application;
import org.example.restapi.repository.ApplicationRepository;
import org.example.restapi.service.model.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Data
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    @Override
    public Application getById(Long id) {
        return applicationRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Пользователь с " + id + " не найден."));
    }
    @Override
    public void deleteById(Long id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public void update(Long id, Application application) {
        Application current = getById(id);
        current.setFirstName(application.getFirstName());
        current.setMiddleName(application.getMiddleName());
        current.setLastName(application.getLastName());
        current.setHotCounter(application.getHotCounter());
        current.setCouldCounter(application.getCouldCounter());
        applicationRepository.save(current);
    }

    @Override
    public void create(Application application) {
        applicationRepository.save(application);
    }

    @Override
    public List<Application> getAll() {
        return applicationRepository.findAll();
    }
}
