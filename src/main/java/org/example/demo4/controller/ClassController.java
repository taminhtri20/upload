package org.example.demo4.controller;

import org.example.demo4.model.Class;
import org.example.demo4.service.IClassService;
import org.example.demo4.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IClassService classService;
    @GetMapping
    public ModelAndView listProvince() {
        ModelAndView modelAndView = new ModelAndView("/class/home");
        modelAndView.addObject("listClass", classService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/class/create");
        modelAndView.addObject("class", new Class());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView  create(@Valid Class class1,
                                BindingResult result) {
        ModelAndView modelAndView;
        if (result.hasErrors()){
            modelAndView = new ModelAndView("/class/create");
            modelAndView.addObject("listERR", result.getAllErrors());
            return modelAndView;
        }
        modelAndView = new ModelAndView("redirect:/class");
        classService.save(class1);
        return modelAndView;
    }
    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/class/edit");
        modelAndView.addObject("class", classService.findById(id).get());
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView  edit(@Valid Class class1,
                                BindingResult result) {
        ModelAndView modelAndView;
        if (result.hasErrors()){
            modelAndView = new ModelAndView("/class/edit");
            modelAndView.addObject("listERR", result.getAllErrors());
            return modelAndView;
        }
        modelAndView = new ModelAndView("redirect:/class");
        classService.save(class1);
        return modelAndView;
    }
    @GetMapping("/{id}/view")
    public ModelAndView showView(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/class/view");
        modelAndView.addObject("class", classService.findById(id).get());
        return modelAndView;
    }
}
