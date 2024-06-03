package org.example.restapi.repository;

import org.example.restapi.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findById(Long id);
    List<Application> findAll();
}
