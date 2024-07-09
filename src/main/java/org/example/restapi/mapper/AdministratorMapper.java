package org.example.restapi.mapper;

import org.example.restapi.dto.AdministratorDto;
import org.example.restapi.entity.Administrator;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdministratorMapper {
    AdministratorMapper INSTANCE = Mappers.getMapper(AdministratorMapper.class);
    AdministratorDto toDto(Administrator administrator);
    Administrator toEntity(AdministratorDto administratorDto);
    List<AdministratorDto> listToDto(List<Administrator> listAdministrator);
    List<Administrator> listToEntity(List<AdministratorDto> administratorDtoList);
}