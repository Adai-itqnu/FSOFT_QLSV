package com.student.system.controller;

import com.student.system.dao.StudentDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchBasicController {

    private final StudentDAO studentDAO;

    public SearchBasicController() {
        this.studentDAO = new StudentDAO();
    }

    @GetMapping("/searchBasic")
    public String showSearchBasicForm() {
        return "searchBasic";
    }

    @PostMapping("/searchBasic")
    public String searchBasic(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("results", studentDAO.searchSinhVien(keyword));
        return "searchBasic";
    }
}