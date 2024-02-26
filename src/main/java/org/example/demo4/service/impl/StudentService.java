package org.example.demo4.service.impl;

import org.example.demo4.model.Class;
import org.example.demo4.model.Student;
import org.example.demo4.respository.ClassRepository;
import org.example.demo4.respository.StudentRepository;
import org.example.demo4.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class StudentService implements IStudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;
    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
        student.getClassName().setCountStudent(student.getClassName().getCountStudent() + 1);
        classRepository.save(student.getClassName());
    }

    @Override
    public Optional<Student> findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public void remove(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Iterable<Student> findAllByClass(Class className) {
        return studentRepository.findAllByClassName(className);
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> findAllByName(Pageable pageable, String name) {
        return studentRepository.findAllByNameContaining(pageable, name);
    }

    @Override
    public Page<Student> sortASC(Pageable pageable) {
        return studentRepository.findByOrderByScoreAsc(pageable);
    }

    @Override
    public Page<Student> sortDESC(Pageable pageable) {
        return studentRepository.findByOrderByScoreDesc(pageable);
    }

    @Override
    public Page<Student> findByNameAndSortASC(Pageable pageable, String name) {
        return studentRepository.findByNameContainingOrderByScoreAsc(pageable, name);
    }

    @Override
    public Page<Student> findByNameAndSortDESC(Pageable pageable, String name) {
        return studentRepository.findByNameContainingOrderByScoreDesc(pageable, name);
    }
}
