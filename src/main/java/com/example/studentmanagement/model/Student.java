package com.example.studentmanagement.model;

import javax.persistence.*;

@Entity
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

    public Student() {
    }

    public Student(int id, String name, double score, Clazz clazz) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.clazz = clazz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
