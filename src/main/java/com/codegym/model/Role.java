package com.codegym.model;

import lombok.*;

import javax.persistence.*;

import static lombok.AccessLevel.PUBLIC;

@Entity
@Table
@Data
@Builder
@RequiredArgsConstructor(access = PUBLIC)
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
}
