package org.example.restapi.service.model;

import org.example.restapi.dto.AdministratorDto;
import org.example.restapi.entity.Administrator;

import java.util.List;

public interface AdministratorService {
    public List<AdministratorDto> getAll();
    public AdministratorDto getById(Long id);
    public Administrator createAdministrator(Administrator administrator);
    public AdministratorDto updateAdministrator(Long id, AdministratorDto updatedAdministratorDto);
    public void deleteAdministrator(Long administratorId);
    public Administrator changePassword(Long id, String newPassword);
}
