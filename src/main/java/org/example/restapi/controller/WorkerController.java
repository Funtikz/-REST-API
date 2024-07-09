package org.example.restapi.controller;

import lombok.AllArgsConstructor;
import org.example.restapi.dto.WorkerCreationRequest;
import org.example.restapi.entity.Worker;
import org.example.restapi.entity.WorkerCredentials;
import org.example.restapi.service.model.WorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/worker")
@AllArgsConstructor
public class WorkerController {
    private WorkerService workerService;

    @PostMapping("/create")
    public ResponseEntity<Worker> createWorker(@RequestBody WorkerCreationRequest workerRequest){
        WorkerCredentials workerCredentials = workerRequest.getWorkerCredentials();
        Worker worker = workerRequest.getWorker();
        return new ResponseEntity<>(workerService.createWorker(worker,workerCredentials), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(workerService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorker(@PathVariable("id") Long id ){
        workerService.deleteWorker(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Worker> updateWorker(@PathVariable("id") Long id,
                                               @RequestBody Worker worker){
        return new ResponseEntity<>(workerService.updateWorker(id, worker), HttpStatus.OK);
    }

    @PutMapping("/{workerId}/add-application/{applicationId}")
    public ResponseEntity<Worker> addApplication(@PathVariable("workerId") Long workerId,
                                                 @PathVariable("applicationId") Long applicationId)
    {
        return  new ResponseEntity<>(workerService.addApplication(workerId,applicationId), HttpStatus.OK);
    }
}
