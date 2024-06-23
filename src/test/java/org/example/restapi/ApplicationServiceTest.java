package org.example.restapi;


import jakarta.persistence.EntityNotFoundException;
import org.example.restapi.dto.ApplicationDto;
import org.example.restapi.entity.Application;
import org.example.restapi.repository.ApplicationRepository;
import org.example.restapi.service.ApplicationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {
    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    private ApplicationDto applicationDto;
    private Application application;

    private ApplicationServiceImpl applicationServiceImpl;


    @BeforeEach
    public void setUp() {
        application = new Application();
        application.setId(1L);
        application.setFirstName("John");
        application.setMiddleName("Doe");
        application.setLastName("Smith");
        application.setPhoneNumber("1234567890");
        application.setCouldCounter(10);
        application.setHotCounter(20);
        application.setOrderTime(LocalDateTime.now());

        applicationDto = new ApplicationDto();
        applicationDto.setFirstName("John");
        applicationDto.setMiddleName("Doe");
        applicationDto.setLastName("Smith");
        applicationDto.setPhoneNumber("1234567890");
        applicationDto.setCouldCounter(10);
        applicationDto.setHotCounter(20);
        applicationDto.setOrderTime(LocalDateTime.now());
    }

    @Test
    public void testCreateApplication(){
        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        ApplicationDto createdApplication = applicationService.createApplication(applicationDto);

        assertEquals(applicationDto.getFirstName(), createdApplication.getFirstName());

        verify(applicationRepository, times(1)).save(any(Application.class));
    }

    @Test
    public void testGetApplicationById(){
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));

        ApplicationDto applicationById = applicationService.getApplicationById(1L);

        assertEquals(applicationDto, applicationById);
    }

    @Test
    void testGetApplicationNotFound(){
        when(applicationRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> applicationService.getApplicationById(1L));

        assertEquals("Пользователя с id 1 не существует", exception.getMessage());
    }

    @Test
    void testGetAllApplication(){
        List<Application> exampleList = List.of(application);
        List<ApplicationDto> trustListOfDto = List.of(applicationDto);

        when(applicationRepository.findAll()).thenReturn(exampleList);

        List<ApplicationDto> createdList = applicationService.getAllApplication();

        assertEquals(createdList.size(), trustListOfDto.size());
        assertEquals(createdList, trustListOfDto);
    }

    @Test
    void testUpdateApplication(){
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        ApplicationDto updatedApplication = applicationService.updateApplication(1L, applicationDto);

        assertEquals(applicationDto.getFirstName(), updatedApplication.getFirstName());
        verify(applicationRepository, times(1)).findById(1L);
        verify(applicationRepository, times(1)).save(any(Application.class));
    }

    @Test
    void testUpdateApplicationNotFound(){
        when(applicationRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> applicationService.updateApplication(1L, applicationDto));

        assertEquals("Пользователя с id 1 не существует", exception.getMessage());
    }

    @Test
    void testDeleteApplicationById(){
        when(applicationRepository.existsById(1L)).thenReturn(true);

        applicationService.deleteApplicationById(1L);

        verify(applicationRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteApplicationByIdNotFound(){
        when(applicationRepository.existsById(1L)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> applicationService.deleteApplicationById(1L));

        assertEquals("Пользователя с id 1 не существует", exception.getMessage());
    }

}
