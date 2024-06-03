package org.example.restapi.service.model;

import org.example.restapi.entity.Application;

import java.util.List;

public interface ApplicationService {
    void deleteById(Long id);
    void update(Long id, Application application);
    void create(Application application);
    Application getById(Long id);
    List<Application> getAll();
}
