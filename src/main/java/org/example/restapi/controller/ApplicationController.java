package org.example.restapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.restapi.dto.ApplicationDto;
import org.example.restapi.entity.Status;
import org.example.restapi.service.model.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "main_methods")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Operation(summary = "Позволяет получить все заявки")
    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public ResponseEntity<List<ApplicationDto>> findAll() {
        return new ResponseEntity<>(applicationService.getAllApplication(), HttpStatus.OK);
    }

    @Operation(summary = "Позволяет получить заявку по id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ApplicationDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(applicationService.getApplicationById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/get-by-fio")
    @ResponseBody
    @Operation(summary = "Позволяет найти заявку по ФИО")
    public ResponseEntity<List<ApplicationDto>> findByFio(@RequestParam("firstName") String firstName,
                                                          @RequestParam("lastName") String lastName,
                                                          @RequestParam("middleName") String middleName){
        return new ResponseEntity<>(applicationService.getByFio(firstName, lastName, middleName), HttpStatus.OK);
    }

    @GetMapping(value = "/get-by-phone-number")
    @ResponseBody
    @Operation(summary = "Позволяет найти заявки по номеру телефона")
    public ResponseEntity<List<ApplicationDto>> findByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber){
        return new ResponseEntity<>(applicationService.getByNumberPhone(phoneNumber), HttpStatus.OK);
    }

    @GetMapping(value = "/get-by-status")
    @ResponseBody
    @Operation(summary = "Позволяет найти заявки по статусу IN_PROCESSING, PROCESSED, COMPLETED")
    public ResponseEntity<List<ApplicationDto>> findByStatus(@RequestParam("status") Status status){
        return new ResponseEntity<>(applicationService.getByStatus(status), HttpStatus.OK);
    }

    @Operation(summary = "Позволяет создать заявку")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApplicationDto> create(@Valid @RequestBody ApplicationDto application) {
        return new ResponseEntity<>(applicationService.createApplication(application), HttpStatus.CREATED);
    }

    @Operation(summary = "Позволяет удалить заявку")
    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") Long id) {
        applicationService.deleteApplicationById(id);
    }

    @Operation(
            summary = "Позволяет обновить заявку по id"
    )
    @PutMapping("/{id}/update")
    public ResponseEntity<ApplicationDto> update(@PathVariable("id") Long id, @Valid @RequestBody ApplicationDto application) {

        return new ResponseEntity<>(applicationService.updateApplication(id, application), HttpStatus.OK);
    }



}
