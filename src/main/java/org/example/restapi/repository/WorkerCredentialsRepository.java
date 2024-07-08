package org.example.restapi.repository;

import org.example.restapi.entity.WorkerCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerCredentialsRepository extends JpaRepository<WorkerCredentials,Long> {

}
