package org.example.restapi.controller;

import lombok.AllArgsConstructor;
import org.example.restapi.dto.AdministratorDto;
import org.example.restapi.entity.Administrator;
import org.example.restapi.service.model.AdministratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/administrators")
@AllArgsConstructor
public class AdministratorController {
    private final AdministratorService administratorService;

    @GetMapping("/get-all")
    public ResponseEntity<List<AdministratorDto>> getAll(){
        return new  ResponseEntity<>(administratorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministratorDto> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(administratorService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Administrator> createAdministrator(@RequestBody Administrator administrator) {
        return new ResponseEntity<>(administratorService.createAdministrator(administrator), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministratorDto> updateAdministrator(@PathVariable("id") Long id, @RequestBody AdministratorDto administratorDto) {
        return new ResponseEntity<>(administratorService.updateAdministrator(id, administratorDto), HttpStatus.OK);
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<Administrator> changePassword(@PathVariable("id") Long id, @RequestBody String newPassword) {
        return new ResponseEntity<>(administratorService.changePassword(id, newPassword), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrator(@PathVariable("id") Long id) {
        administratorService.deleteAdministrator(id);
        return ResponseEntity.noContent().build();
    }
}
