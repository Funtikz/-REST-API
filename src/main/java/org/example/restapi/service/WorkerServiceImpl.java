package org.example.restapi.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.restapi.entity.Application;
import org.example.restapi.entity.Worker;
import org.example.restapi.entity.WorkerCredentials;
import org.example.restapi.repository.ApplicationRepository;
import org.example.restapi.repository.WorkerCredentialsRepository;
import org.example.restapi.repository.WorkerRepository;
import org.example.restapi.service.model.WorkerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final WorkerCredentialsRepository workerCredentialsRepository;
    private final ApplicationRepository applicationRepository;


    public Worker getById(Long id) {
       return workerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Пользователь с таким id не найден"));
    }

    @Override
    @Transactional
    public Worker createWorker(Worker worker, WorkerCredentials workerCredentials) {
        worker.setApplications(new ArrayList<>());
        workerCredentials.setWorker(worker);
        workerRepository.save(worker);
        workerCredentialsRepository.save(workerCredentials);
        return worker;
    }

    @Override
    @Transactional
    public Worker updateWorker(Long id, Worker worker) {
        Worker selectedWorker = workerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Пользователь с таким id не найден"));
        selectedWorker.setApplications(worker.getApplications());
        selectedWorker.setFirstName(worker.getFirstName());
        selectedWorker.setLastName(worker.getLastName());
        selectedWorker.setMiddleName(worker.getMiddleName());
        selectedWorker.setPhoneNumber(worker.getPhoneNumber());
        return workerRepository.save(selectedWorker);
    }

    @Override
    @Transactional
    public void deleteWorker(Long id) {
        Worker selectedWorker = workerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Пользователь с такими id не найден"));
        workerRepository.delete(selectedWorker);
    }

    @Override
    public WorkerCredentials changePassword(Long id, String password) {
        WorkerCredentials selectedWorker = workerCredentialsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Пользователь с таким id не найден"));
        selectedWorker.setPassword(password);
        workerCredentialsRepository.save(selectedWorker);
        return selectedWorker;
    }

    @Override
    @Transactional
    public Worker addApplication(Long workerId, Application application) {
        Worker selectedWorker = workerRepository.findById(workerId).orElseThrow(() ->
                new EntityNotFoundException("Пользователь с таким id не найден"));
        List<Application> listOfApplications = selectedWorker.getApplications();
        listOfApplications.add(application);
        application.setWorker(selectedWorker);
        applicationRepository.save(application);
        return workerRepository.save(selectedWorker);
    }

    @Override
    @Transactional
    public void removeApplication(Long workerId, Long applicationId) {
        Worker selectedWorker = workerRepository.findById(workerId).orElseThrow(() ->
                new EntityNotFoundException("Worker with id " + workerId + " not found"));
        Application application = applicationRepository.findById(applicationId).orElseThrow(() ->
                new EntityNotFoundException("Application with id " + applicationId + " not found"));

        List<Application> applications = selectedWorker.getApplications();
        applications.remove(application);

        application.setWorker(null);
        applicationRepository.save(application);

        workerRepository.save(selectedWorker);
    }


}
