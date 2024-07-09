package org.example.restapi;

import jakarta.persistence.EntityNotFoundException;
import org.example.restapi.dto.WorkerDto;
import org.example.restapi.entity.Application;
import org.example.restapi.entity.Worker;
import org.example.restapi.mapper.WorkerMapper;
import org.example.restapi.repository.ApplicationRepository;
import org.example.restapi.repository.WorkerRepository;
import org.example.restapi.service.WorkerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkerServiceTest {

    @Mock
    private WorkerRepository workerRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private WorkerMapper workerMapper;

    @InjectMocks
    private WorkerServiceImpl workerService;

    private Worker worker;
    private WorkerDto workerDto;
    private Application application;

    @BeforeEach
    void setUp() {
        worker = new Worker();
        worker.setId(1L);
        worker.setFirstName("John");
        worker.setLastName("Doe");
        worker.setMiddleName("Middle");
        worker.setPhoneNumber("12345678901");

        workerDto = new WorkerDto();
        workerDto.setId(1L);
        workerDto.setFirstName("John");
        workerDto.setLastName("Doe");
        workerDto.setMiddleName("Middle");
        workerDto.setPhoneNumber("12345678901");

        application = new Application();
        application.setId(1L);
        application.setWorker(worker);
        worker.setApplications(List.of(application));
    }

    @Test
    void testGetByIdSuccess() {
        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));
        when(workerMapper.toDto(worker)).thenReturn(workerDto);

        WorkerDto foundWorker = workerService.getById(1L);

        assertNotNull(foundWorker);
        assertEquals("John", foundWorker.getFirstName());
    }

    @Test
    void testGetByIdNotFound() {
        when(workerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> workerService.getById(1L));
    }

    @Test
    void testGetAll() {
        List<Worker> workers = List.of(worker);
        when(workerRepository.findAll()).thenReturn(workers);
        when(workerMapper.toDto(workers)).thenReturn(List.of(workerDto));

        List<WorkerDto> foundWorkers = workerService.getAll();

        assertNotNull(foundWorkers);
        assertEquals(1, foundWorkers.size());
        assertEquals("John", foundWorkers.get(0).getFirstName());
    }

    @Test
    void testCreateWorker() {
        when(workerRepository.save(any(Worker.class))).thenReturn(worker);

        Worker createdWorker = workerService.createWorker(worker);

        assertNotNull(createdWorker);
        assertEquals("John", createdWorker.getFirstName());
        assertTrue(createdWorker.getApplications().isEmpty());
    }

    @Test
    void testUpdateWorkerSuccess() {
        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));
        when(workerRepository.save(any(Worker.class))).thenReturn(worker);
        when(workerMapper.toDto(worker)).thenReturn(workerDto);

        WorkerDto updatedWorker = workerService.updateWorker(1L, worker);

        assertNotNull(updatedWorker);
        assertEquals("John", updatedWorker.getFirstName());
    }

    @Test
    void testUpdateWorkerNotFound() {
        when(workerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> workerService.updateWorker(1L, worker));
    }

    @Test
    void testDeleteWorkerSuccess() {
        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));
        doNothing().when(workerRepository).delete(worker);

        workerService.deleteWorker(1L);

        verify(workerRepository, times(1)).delete(worker);
    }

    @Test
    void testDeleteWorkerNotFound() {
        when(workerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> workerService.deleteWorker(1L));
    }

    @Test
    void testChangePasswordSuccess() {
        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));
        when(workerRepository.save(any(Worker.class))).thenReturn(worker);

        Worker updatedWorker = workerService.changePassword(1L, "newPassword");

        assertNotNull(updatedWorker);
        assertEquals("newPassword", updatedWorker.getPassword());
    }

    @Test
    void testChangePasswordNotFound() {
        when(workerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> workerService.changePassword(1L, "newPassword"));
    }

//    @Test
//    void testAddApplicationSuccess() {
//        worker.setApplications(new ArrayList<>());
//        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));
//        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
//        when(workerRepository.save(any(Worker.class))).thenReturn(worker);
//        when(applicationRepository.save(any(Application.class))).thenReturn(application);
//        when(workerMapper.toDto(worker)).thenReturn(workerDto);
//        WorkerDto updatedWorker = workerService.addApplication(1L, 1L);
//
//        assertNotNull(updatedWorker);
//        assertEquals(1, updatedWorker.getApplications().size());
//    }

    @Test
    void testAddApplicationWorkerNotFound() {
        when(workerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> workerService.addApplication(1L, 1L));
    }

    @Test
    void testAddApplicationNotFound() {
        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));
        when(applicationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> workerService.addApplication(1L, 1L));
    }

//    @Test
//    void testRemoveApplicationFromWorkerSuccess() {
//        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));
//        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
//        when(workerRepository.save(any(Worker.class))).thenReturn(worker);
//        when(applicationRepository.save(any(Application.class))).thenReturn(application);
//
//        workerService.removeApplicationFromWorker(1L, 1L);
//
//        verify(applicationRepository, times(1)).save(application);
//        verify(workerRepository, times(1)).save(worker);
//        assertTrue(worker.getApplications().isEmpty());
//    }

    @Test
    void testRemoveApplicationFromWorkerWorkerNotFound() {
        when(workerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> workerService.removeApplicationFromWorker(1L, 1L));
    }

    @Test
    void testRemoveApplicationFromWorkerApplicationNotFound() {
        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));
        when(applicationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> workerService.removeApplicationFromWorker(1L, 1L));
    }
}
