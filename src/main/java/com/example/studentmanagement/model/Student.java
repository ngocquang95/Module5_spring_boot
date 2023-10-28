package com.example.studentmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Tên cho cột ở trong table
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private double score;

    @ManyToOne
    @JoinColumn(name = "clazz_id")
    private Clazz clazz;
}
