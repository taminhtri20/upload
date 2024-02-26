package org.example.demo4.service.impl;

import org.example.demo4.model.Class;
import org.example.demo4.respository.ClassRepository;
import org.example.demo4.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassService implements IClassService {
    @Autowired
    private ClassRepository classRepository;
    @Override
    public Iterable<Class> findAll() {
        return classRepository.findAll();
    }

    @Override
    public void save(Class aClass) {
        classRepository.save(aClass);
    }

    @Override
    public Optional<Class> findById(int id) {
        return classRepository.findById(id);
    }

    @Override
    public void remove(int id) {
        classRepository.deleteById(id);
    }
}
