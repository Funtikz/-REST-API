package org.example.restapi.service.model;

import org.example.restapi.entity.Worker;
import org.example.restapi.entity.WorkerCredentials;

public interface WorkerService {
    public Worker createWorker(Worker worker, WorkerCredentials workerCredentials);
    public Worker updateWorker(Long id, Worker worker);
    public void deleteWorker(Long id);
    public WorkerCredentials changePassword(Long id, String password);
    public Worker addApplication(Long workerId, Long applicationId);

    public Worker getById(Long id);
    public void removeApplication(Long workerId, Long applicationId);
}
