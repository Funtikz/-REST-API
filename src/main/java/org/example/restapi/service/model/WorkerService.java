package org.example.restapi.service.model;

import org.example.restapi.dto.WorkerDto;
import org.example.restapi.entity.Worker;

import java.util.List;

public interface WorkerService {
    public List<WorkerDto> getAll();
    public Worker createWorker(Worker worker);
    public WorkerDto updateWorker(Long id, Worker worker);
    public void deleteWorker(Long id);
    public Worker changePassword(Long id, String password);
    public WorkerDto addApplication(Long workerId, Long applicationId);
    public WorkerDto getById(Long id);
    public void removeApplicationFromWorker(Long workerId, Long applicationId);
}
