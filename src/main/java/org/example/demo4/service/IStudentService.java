package org.example.demo4.service;

import org.example.demo4.model.Class;
import org.example.demo4.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService extends IGenerateService<Student> {
    Iterable<Student> findAllByClass(Class className);
    Page<Student> findAll(Pageable pageable);
    Page<Student> findAllByName(Pageable pageable, String name);
    Page<Student> sortASC(Pageable pageable);
    Page<Student> sortDESC(Pageable pageable);
    Page<Student> findByNameAndSortASC(Pageable pageable, String name);
    Page<Student> findByNameAndSortDESC(Pageable pageable, String name);
}
