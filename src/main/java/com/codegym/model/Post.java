package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PUBLIC;

@Entity
@Table
@Data
@Builder
@RequiredArgsConstructor(access = PUBLIC)
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Account account;
    private String content;
    private LocalDateTime time;
    private int likeCount;
    private int cmtCount;
    private int status;
}
