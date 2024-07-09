package org.example.restapi.mapper;

import org.example.restapi.dto.WorkerDto;
import org.example.restapi.entity.Worker;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface WorkerMapper {
    WorkerDto toDto(Worker worker);
    Worker toEntity(WorkerDto workerDto);
    List<WorkerDto> toDto(List<Worker> workers);
    List<Worker> toEntity(List<WorkerDto> workers);
}
