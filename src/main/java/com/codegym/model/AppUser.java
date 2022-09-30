package com.codegym.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String displayName;
    private LocalDate DOB;
    private String address;
    private String phoneNumber;
    private String aboutMe;
    @OneToOne
    private Account account;

    @OneToMany
    private Set<AppUser> friends;
    @OneToMany
    private Set<AppUser> friendRequest;
    @OneToMany
    private Set<AppUser> blocked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
