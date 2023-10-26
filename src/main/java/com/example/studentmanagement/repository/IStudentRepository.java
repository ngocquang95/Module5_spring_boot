package com.example.studentmanagement.repository;

import com.example.studentmanagement.dto.StudentSearchDTO;
import com.example.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
    // Phụ thuộc vào tên phương thức để tạo câu truy vấn
    List<Student> findByNameContaining(String name);

    //HQL
    // Phụ phuộc vào @Query để tạo câu truy vấn
    @Query(value = "FROM Student s where (:name = '' or :name is null or s.name like concat('%', :name, '%'))" +
            " and (:fromScore = '' or :fromScore is null or s.score >= :fromScore)" +
            " and (:toScore = '' or :toScore is null or s.score <= :toScore)" +
            " and (:clazzId = '' or :clazzId is null or s.clazz.id = :clazzId)"
    )
    List<Student> search(@Param("name") String name, @Param("fromScore") String fromScore, @Param("toScore") String toScore, @Param("clazzId") String clazzId);// Phụ phuộc vào @Query để tạo câu truy vấn

    // SỬ dụng nativeQuery => Tùy thuộc vào DB đang dùng => MySQL
    @Query(value = "select s.id, s.name, s.score, s.clazz_id FROM Student s where (:name = '' or :name is null or s.name like concat('%', :name, '%'))\n" +
            "             and (:fromScore = '' or :fromScore is null or s.score >= :fromScore)\n" +
            "             and (:toScore = '' or :toScore is null or s.score <= :toScore)\n" +
            "             and (:clazzId = '' or :clazzId is null or s.clazz_id = :clazzId)", nativeQuery = true
    )
    List<Student> search2(@Param("name") String name, @Param("fromScore") String fromScore, @Param("toScore") String toScore, @Param("clazzId") String clazzId);
//    List<Student> search(StudentSearchDTO studentSearchDTO);
//
//    Student findById(int id);
//
//    void create(Student student);
}
