package org.example.restapi.dto;

import lombok.Data;
import org.example.restapi.entity.Worker;
import org.example.restapi.entity.WorkerCredentials;

@Data
public class WorkerCreationRequest {
    private Worker worker;
    private WorkerCredentials workerCredentials;
}

