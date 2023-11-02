package com.example.studentmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Tên cho cột ở trong table
    @NotNull(message = "Phải chọn lớp")
    private Integer id;

    @Column(name = "name")
    private String name;
//    @OneToMany(mappedBy = "clazz")
//    private Set<Student> students;
}
