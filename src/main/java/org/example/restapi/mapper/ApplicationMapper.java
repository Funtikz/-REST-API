package org.example.restapi.mapper;

import org.example.restapi.dto.ApplicationDto;
import org.example.restapi.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);
    @Mapping(target = "address", source = "address")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "description", source = "description")
    ApplicationDto toDto(Application application);

    @Mapping(target = "address", source = "address")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "description", source = "description")
    Application toEntity(ApplicationDto applicationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", source = "address")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "description", source = "description")
    void updateFromDto(ApplicationDto dto, @MappingTarget Application entity);
}
