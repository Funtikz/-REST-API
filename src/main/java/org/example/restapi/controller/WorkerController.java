package org.example.restapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.restapi.dto.WorkerDto;
import org.example.restapi.entity.Worker;
import org.example.restapi.service.model.WorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/worker")
@AllArgsConstructor
public class WorkerController {
    private WorkerService workerService;

    @GetMapping("/{id}")
    @Operation(summary = "Позволяет получить пользователя по id")
    public ResponseEntity<WorkerDto> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(workerService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Позволяет получить всех пользователей")
    public ResponseEntity<List<WorkerDto>> getAll(){
        return new ResponseEntity<>(workerService.getAll(), HttpStatus.OK);
    }


    @PostMapping("/create")
    @Operation(summary = "Позволяет получить всех пользователей")
    public ResponseEntity<Worker> createWorker(@Valid @RequestBody Worker workerRequest){
        return new ResponseEntity<>(workerService.createWorker(workerRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Позволяет удалить пользователя по id ")
    public ResponseEntity<Void> deleteWorker(@PathVariable("id") Long id ){
        workerService.deleteWorker(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Позволяет обновить пользователя по id ")
    public ResponseEntity<WorkerDto> updateWorker(@Valid @PathVariable("id") Long id,
                                               @RequestBody Worker worker){
        return new ResponseEntity<>(workerService.updateWorker(id, worker), HttpStatus.OK);
    }

    @PutMapping("/{workerId}/add-application/{applicationId}")
    @Operation(summary = "Позволяет добавить пользователя определенную заявку по Id")
    public ResponseEntity<WorkerDto> addApplication(@PathVariable("workerId") Long workerId,
                                                 @PathVariable("applicationId") Long applicationId)
    {
        return  new ResponseEntity<>(workerService.addApplication(workerId,applicationId), HttpStatus.OK);
    }

    @DeleteMapping("/{workerId}/remove-application/{applicationId}")
    @Operation(summary = "Позволяет удалить у  пользователя определенную заявку по Id")
    public ResponseEntity<Void> removeApplication(@PathVariable("workerId") Long workerId,
                                                    @PathVariable("applicationId") Long applicationId){
        workerService.removeApplicationFromWorker(workerId,applicationId);
        return  ResponseEntity.noContent().build();

    }
}
