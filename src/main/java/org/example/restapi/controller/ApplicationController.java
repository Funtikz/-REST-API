package org.example.restapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.restapi.dto.ApplicationDto;
import org.example.restapi.service.model.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<ApplicationDto>> findAll() {
        return new ResponseEntity<>(applicationService.getAllApplication(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ApplicationDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(applicationService.getApplicationById(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApplicationDto> create(@Valid @RequestBody ApplicationDto application) {
        return new ResponseEntity<>(applicationService.createApplication(application), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") Long id) {
        applicationService.deleteApplicationById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationDto> update(@PathVariable("id") Long id, @Valid @RequestBody ApplicationDto application) {
        return new ResponseEntity<>(applicationService.updateApplication(id, application), HttpStatus.OK);
    }
    

}
