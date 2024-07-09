package org.example.restapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.restapi.dto.AdministratorDto;
import org.example.restapi.entity.Administrator;
import org.example.restapi.mapper.AdministratorMapper;
import org.example.restapi.repository.AdministratorRepository;
import org.example.restapi.service.model.AdministratorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorRepository administratorRepository;
    private final AdministratorMapper administratorMapper;

    @Override
    public List<AdministratorDto> getAll() {
        List<Administrator> all = administratorRepository.findAll();
        return administratorMapper.listToDto(all);
    }

    @Override
    public AdministratorDto getById(Long id) {
        Administrator administrator = administratorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Администратора с таким id не найдено"));
        return administratorMapper.toDto(administrator);
    }

    @Override
    public Administrator createAdministrator(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    @Override
    public AdministratorDto updateAdministrator(Long id, AdministratorDto updatedAdministratorDto) {
        Administrator administrator = administratorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Администратор с таким id не найден"));

        administrator.setFirstName(updatedAdministratorDto.getFirstName());
        administrator.setLastName(updatedAdministratorDto.getLastName());
        administrator.setMiddleName(updatedAdministratorDto.getMiddleName());
        administrator.setPhoneNumber(updatedAdministratorDto.getPhoneNumber());

        Administrator updatedAdministrator = administratorRepository.save(administrator);
        return administratorMapper.toDto(updatedAdministrator);
    }

    @Override
    public void deleteAdministrator(Long administratorId) {
        administratorRepository.deleteById(administratorId);
    }

    @Override
    public Administrator changePassword(Long id, String newPassword) {
        Administrator administrator = administratorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Учетные данные администратора с таким id не найдены"));

        administrator.setPassword(newPassword);
        return administratorRepository.save(administrator);
    }
}
