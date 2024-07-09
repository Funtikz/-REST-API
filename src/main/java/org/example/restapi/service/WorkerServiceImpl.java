package org.example.restapi.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.restapi.dto.WorkerDto;
import org.example.restapi.entity.Application;
import org.example.restapi.entity.Worker;
import org.example.restapi.mapper.WorkerMapper;
import org.example.restapi.repository.ApplicationRepository;
import org.example.restapi.repository.WorkerRepository;
import org.example.restapi.service.model.WorkerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final ApplicationRepository applicationRepository;
    private final WorkerMapper workerMapper;


    public WorkerDto getById(Long id) {
       return workerMapper.toDto(workerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Пользователь с таким id не найден")));
    }

    @Override
    public List<WorkerDto> getAll() {
        return workerMapper.toDto(workerRepository.findAll());
    }

    @Override
    @Transactional
    public Worker createWorker(Worker worker) {
        worker.setApplications(new ArrayList<>());
        return workerRepository.save(worker);
    }

    @Override
    @Transactional
    public WorkerDto updateWorker(Long id, Worker worker) {
        Worker selectedWorker = workerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Пользователь с таким id не найден"));

        selectedWorker.setApplications(worker.getApplications());
        selectedWorker.setFirstName(worker.getFirstName());
        selectedWorker.setLastName(worker.getLastName());
        selectedWorker.setMiddleName(worker.getMiddleName());
        selectedWorker.setPhoneNumber(worker.getPhoneNumber());

        return workerMapper.toDto(workerRepository.save(selectedWorker));
    }

    @Override
    @Transactional
    public void deleteWorker(Long id) {
        Worker selectedWorker = workerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Пользователь с такими id не найден"));
        workerRepository.delete(selectedWorker);
    }

    @Override
    public Worker changePassword(Long id, String password) {
        Worker worker = workerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Пользователь с данным id не найден"));

        worker.setPassword(password);
        return workerRepository.save(worker) ;
    }

    @Override
    @Transactional
    public WorkerDto addApplication(Long workerId, Long applicationId) {

        Worker selectedWorker = workerRepository.findById(workerId).orElseThrow(() ->
                new EntityNotFoundException("Пользователь с таким id не найден"));

        List<Application> listOfApplications = selectedWorker.getApplications();
        Application application = applicationRepository.findById(applicationId).orElseThrow(
                () -> new EntityNotFoundException("Заявка с таким id не найдена"));

        listOfApplications.add(application);
        application.setWorker(selectedWorker);
        applicationRepository.save(application);
        return workerMapper.toDto(workerRepository.save(selectedWorker));

    }

    @Override
    @Transactional
    public void removeApplicationFromWorker(Long workerId, Long applicationId) {
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
