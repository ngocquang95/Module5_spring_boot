package com.example.studentmanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Tên cho cột ở trong table
    @NotNull(message = "Phải chọn lớp")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Clazz() {
    }

    public Clazz(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
