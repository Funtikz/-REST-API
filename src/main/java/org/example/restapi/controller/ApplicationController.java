package org.example.restapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.restapi.dto.ApplicationDto;
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

    @Operation(summary = "Позволяет получить всех пользователей")
    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public ResponseEntity<List<ApplicationDto>> findAll() {
        return new ResponseEntity<>(applicationService.getAllApplication(), HttpStatus.OK);
    }

    @Operation(summary = "Позволяет получить пользователя по id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ApplicationDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(applicationService.getApplicationById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/get-by-fio")
    @ResponseBody
    @Operation(summary = "Позволяет найти пользователей по ФИО")
    public ResponseEntity<List<ApplicationDto>> findByFio(@RequestParam("firstName") String firstName,
                                                          @RequestParam("lastName") String lastName,
                                                          @RequestParam("middleName") String middleName){
        return new ResponseEntity<>(applicationService.findByFio(firstName, lastName, middleName), HttpStatus.OK);
    }

    @Operation(summary = "Позволяет создать пользователя")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApplicationDto> create(@Valid @RequestBody ApplicationDto application) {
        return new ResponseEntity<>(applicationService.createApplication(application), HttpStatus.CREATED);
    }

    @Operation(summary = "Позволяет удалить пользователя")
    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") Long id) {
        applicationService.deleteApplicationById(id);
    }

    @Operation(
            summary = "Позволяет обновить пользователя по id"
    )
    @PutMapping("/{id}/update")
    public ResponseEntity<ApplicationDto> update(@PathVariable("id") Long id, @Valid @RequestBody ApplicationDto application) {

        return new ResponseEntity<>(applicationService.updateApplication(id, application), HttpStatus.OK);
    }



}
