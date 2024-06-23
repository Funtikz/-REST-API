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
    ApplicationDto toDto(Application application);
    Application toEntity(ApplicationDto applicationDto);
    @Mapping(target = "id", ignore = true)
    void updateFromDto(ApplicationDto dto, @MappingTarget Application entity);
}
