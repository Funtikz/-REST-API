package org.example.restapi.repository;

import org.example.restapi.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT a FROM Application a where a.firstName = ?1 and " +
            "a.middleName =?3 and a.lastName =?2")
    List<Application> findByFio(String firstName, String lastName, String middleName );

}
