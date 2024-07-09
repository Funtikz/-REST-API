package org.example.restapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @OneToOne(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private WorkerCredentials credentials;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Application> applications;
}