package org.example.restapi.mapper;

import org.example.restapi.dto.ApplicationDto;
import org.example.restapi.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "description", source = "description")
    ApplicationDto toDto(Application application);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "description", source = "description")
    Application toEntity(ApplicationDto applicationDto);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "description", source = "description")
    List<ApplicationDto> listToDto(List<Application> listApplication);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "worker", source = "worker")
    void updateFromDto(ApplicationDto dto, @MappingTarget Application entity);
}
