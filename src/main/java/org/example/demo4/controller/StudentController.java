package org.example.demo4.controller;

import org.example.demo4.model.Class;
import org.example.demo4.model.Student;
import org.example.demo4.service.IClassService;
import org.example.demo4.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    public IStudentService iStudentService;
    @Autowired
    public IClassService iClassService;
    Page<Student> students;
    String searchMSG;
    @Value("${file-upload}")
    private String upload;
    @ModelAttribute("class")
    public Iterable<Class> listClass(){
        return iClassService.findAll();
    }
    @RequestMapping("")
    public ModelAndView modelAndView(@PageableDefault(value = 15) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/student/home");
        modelAndView.addObject("listStudent", iStudentService.findAll(pageable));
        modelAndView.addObject("student", new Student());
        searchMSG = null;
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView create(@RequestParam(value = "file", required = false)MultipartFile file, @Valid Student student, BindingResult result) throws IOException {
        ModelAndView modelAndView;
        String fileName = file.getOriginalFilename();
        if (result.hasErrors() || fileName.isEmpty()){
            modelAndView = new ModelAndView("/student/create");
            modelAndView.addObject("listERR", result.getAllErrors());
            if (fileName.isEmpty()){
                modelAndView.addObject("fileERR", "Vui long nhap anh");
            }
            return modelAndView;
        } else {
            modelAndView = new ModelAndView("redirect:/students");
            FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
            iStudentService.save(new Student(student.getId(), student.getName(), student.getBirthDay(), student.getHomeTown(), student.getScore(), student.getClassName(), fileName));
            return modelAndView;
        }
    }
//    @GetMapping("/{id}/update")
//    public ModelAndView showUpdate(@PathVariable int id){
//        ModelAndView modelAndView = new ModelAndView("/student/edit");
//        modelAndView.addObject("student", iStudentService.findById(id).get());
//        return modelAndView;
//    }
    @PostMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "file", required = false)MultipartFile file,
                             Student student,
                             @Valid BindingResult result) throws IOException {
        ModelAndView modelAndView = new ModelAndView("redirect:/students");
        if (result.hasErrors()){
            modelAndView = new ModelAndView("redirect:/students");
            modelAndView.addObject("studentEdit", student);
            modelAndView.addObject("listERR", result.getAllErrors());
            return modelAndView;
        }
        String fileName = null;
        if (file != null){
            fileName = file.getOriginalFilename();
            FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
            iStudentService.save(student);
        }
        if (fileName == null){
            iStudentService.save(student);
        }
        return modelAndView;
    }
    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "id") int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/students");
        iStudentService.remove(id);
        return modelAndView;
    }
    @PostMapping("/search")
    public ModelAndView search(@RequestParam("search")Optional<String> search,@PageableDefault(value = 5) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/student/search");
        searchMSG = search.get();
        if (search.isPresent()){
            students = iStudentService.findAllByName(pageable, search.get());
        }else {
            students = iStudentService.findAll(pageable);
        }
        modelAndView.addObject("listStudent", students);
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView showSearch(@PageableDefault(value = 15) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/student/search");
        modelAndView.addObject("listStudent", iStudentService.findAllByName(pageable, searchMSG));
        return modelAndView;
    }
    @GetMapping("/sortASC")
    public ModelAndView sortASC(@PageableDefault(value = 15) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/student/sortASC");
        if (searchMSG != null){
            modelAndView.addObject("listStudent", iStudentService.findByNameAndSortASC(pageable, searchMSG));
            return modelAndView;
        }
        modelAndView.addObject("listStudent", iStudentService.sortASC(pageable));
        return modelAndView;
    }
    @GetMapping("/sortDESC")
    public ModelAndView sortDESC(@PageableDefault(value = 15) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/student/sortDESC");
        if (searchMSG != null){
            modelAndView.addObject("listStudent", iStudentService.findByNameAndSortDESC(pageable, searchMSG));
            return modelAndView;
        }
        modelAndView.addObject("listStudent", iStudentService.sortDESC(pageable));
        return modelAndView;
    }
    @GetMapping("/{id}/view")
    public ModelAndView view(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/student/view");
        modelAndView.addObject("student", iStudentService.findById(id).get());
        return modelAndView;
    }
}
