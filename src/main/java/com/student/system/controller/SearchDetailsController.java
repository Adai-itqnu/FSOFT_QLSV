package com.student.system.controller;

import com.student.system.dao.StudentDAO;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchDetailsController {

    private final StudentDAO studentDAO;

    public SearchDetailsController() {
        this.studentDAO = new StudentDAO();
    }

    @GetMapping("/searchDetails")
    public String showSearchDetailsForm() {
        return "searchDetails";
    }

//    @PostMapping("/searchDetails")
//    public String searchDetails(@RequestParam("keyword") String keyword, Model model) {
//        model.addAttribute("results", studentDAO.searchGraduationAndJob(keyword));
//        return "searchDetails";
//    }
    @PostMapping("/searchDetails")
    public String searchDetails(@RequestParam("keyword") String keyword, Model model) {
        System.out.println("Từ khóa tìm kiếm: " + keyword); // Debug
        List<Object[]> results = studentDAO.searchGraduationAndJob(keyword);
        System.out.println("Kết quả tìm kiếm: " + results); // Debug
        model.addAttribute("results", results);
        return "searchDetails";
    }
}