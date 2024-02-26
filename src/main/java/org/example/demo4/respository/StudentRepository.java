package org.example.demo4.respository;

import org.example.demo4.model.Class;
import org.example.demo4.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Iterable<Student> findAllByClassName(Class className);

    Page<Student> findAll(Pageable pageable);
    Page<Student> findAllByNameContaining(Pageable pageable, String name);
    Page<Student> findByOrderByScoreAsc(Pageable pageable);
    Page<Student> findByOrderByScoreDesc(Pageable pageable);
    Page<Student> findByNameContainingOrderByScoreAsc(Pageable pageable, String name);
    Page<Student> findByNameContainingOrderByScoreDesc(Pageable pageable, String name);
}
