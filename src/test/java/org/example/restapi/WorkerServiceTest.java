package org.example.restapi;

import jakarta.persistence.EntityNotFoundException;
import org.example.restapi.entity.Application;
import org.example.restapi.entity.Worker;
import org.example.restapi.entity.WorkerCredentials;
import org.example.restapi.repository.ApplicationRepository;
import org.example.restapi.repository.WorkerCredentialsRepository;
import org.example.restapi.repository.WorkerRepository;
import org.example.restapi.service.WorkerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WorkerServiceTest {

    @Mock
    WorkerRepository workerRepository;

    @Mock
    WorkerCredentialsRepository workerCredentialsRepository;

    @Mock
    ApplicationRepository applicationRepository;

    @InjectMocks
    WorkerServiceImpl workerService;
    private Worker worker;
    private WorkerCredentials workerCredentials;

    @BeforeEach
    public void setUp(){
        worker = new Worker();
        worker.setFirstName("Никита");
        worker.setLastName("Щербаков");
        worker.setMiddleName("Александрович");
        worker.setId(1L);
        worker.setPhoneNumber("89882505362");
        workerCredentials = new WorkerCredentials();
        workerCredentials.setPassword("12345");
        workerCredentials.setLogin("nikita");
        workerCredentials.setWorker(worker);
    }

    @Test
    public void testCreateWorker(){
        when(workerRepository.save(any(Worker.class))).thenReturn(worker);

        Worker createdWorker = workerService.createWorker(worker, workerCredentials);

        assertEquals(worker, createdWorker);
        assertEquals(worker, workerCredentials.getWorker());
        verify(workerRepository, times(1)).save(any(Worker.class));
    }

    @Test
    public void getById(){
        when(workerRepository.findById(1L)).thenReturn(Optional.ofNullable(worker));

        Worker byId = workerService.getById(1L);
        assertEquals(worker, byId);
    }
    @Test
    public void getByIdNotFound() {
        when(workerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            workerService.getById(1L);
        });
    }

    @Test
    public void updateWorker(){
        Worker createdWorker = new Worker();
        createdWorker.setFirstName("Александр");
        createdWorker.setLastName("Щербаков");
        createdWorker.setMiddleName("Александрович");
        createdWorker.setId(1L);
        createdWorker.setPhoneNumber("89882505362");

        when(workerRepository.findById(1L)).thenReturn(Optional.ofNullable(worker));
        when(workerRepository.save(worker)).thenReturn(createdWorker);

        Worker updatedWorker = workerService.updateWorker(1L, createdWorker);

        assertEquals(createdWorker.getLastName(), updatedWorker.getLastName());
        verify(workerRepository, times(1)).findById(1L);
        verify(workerRepository, times(1)).save(worker);
    }

    @Test
    public void testUpdateWorkerNotFound(){

        when(workerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->{
           workerService.updateWorker(1L, new Worker());
        });
    }

    @Test
    public void deleteWorker(){
        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));

        workerService.deleteWorker(1L);

        verify(workerRepository, times(1)).findById(1L);
        verify(workerRepository, times(1)).delete(worker);
    }

    @Test
    public void changePassword(){
        when(workerCredentialsRepository.findById(1L)).thenReturn(Optional.of(workerCredentials));
        when(workerCredentialsRepository.save(workerCredentials)).thenReturn(workerCredentials);

        WorkerCredentials result = workerService.changePassword(1L, "newpassword");

        assertEquals("newpassword", result.getPassword());
        verify(workerCredentialsRepository, times(1)).findById(1L);
        verify(workerCredentialsRepository, times(1)).save(workerCredentials);
    }

    @Test
    public void addApplication(){
        Application application = new Application();
        application.setId(1L);
        worker.setApplications(new ArrayList<>());

        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));
        when(workerRepository.save(worker)).thenReturn(worker);
        when(applicationRepository.save(application)).thenReturn(application);

        Worker result = workerService.addApplication(1L, application);

        assertEquals(worker, result);
        assertTrue(worker.getApplications().contains(application));
        verify(workerRepository, times(1)).findById(1L);
        verify(workerRepository, times(1)).save(worker);
    }
}
