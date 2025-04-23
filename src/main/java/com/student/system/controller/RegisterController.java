package com.student.system.controller;

import com.student.system.dao.StudentDAO;
import com.student.system.model.SinhVien;
import com.student.system.model.TotNghiep;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class RegisterController {

    private final StudentDAO studentDAO;

    public RegisterController() {
        this.studentDAO = new StudentDAO();
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("sinhVien", new SinhVien());
        model.addAttribute("totNghiep", new TotNghiep());
        model.addAttribute("truongList", studentDAO.getAllTruong());
        model.addAttribute("nganhList", studentDAO.getAllNganh());
        return "register";
    }

    @PostMapping("/register")
    public String registerStudent(
            @ModelAttribute("sinhVien") SinhVien sinhVien,
            @ModelAttribute("totNghiep") TotNghiep totNghiep,
            @RequestParam("ngayTN") String ngayTNStr,
            Model model) {
        String soCMND = sinhVien.getSoCMND();
        String maTruong = totNghiep.getMaTruong();
        String maNganh = totNghiep.getMaNganh();

        if (soCMND == null || soCMND.isEmpty() ||
                maTruong == null || maTruong.isEmpty() ||
                maNganh == null || maNganh.isEmpty()) {
            model.addAttribute("truongList", studentDAO.getAllTruong());
            model.addAttribute("nganhList", studentDAO.getAllNganh());
            model.addAttribute("error", "Vui lòng điền đầy đủ các trường bắt buộc (Số CMND, Trường, Ngành).");
            return "register";
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date ngayTN = sdf.parse(ngayTNStr);
            totNghiep.setNgayTN(ngayTN);
            totNghiep.setSoCMND(soCMND);

            studentDAO.saveSinhVien(sinhVien);
            studentDAO.saveTotNghiep(totNghiep);

            return "redirect:/success";
        } catch (Exception e) {
            model.addAttribute("truongList", studentDAO.getAllTruong());
            model.addAttribute("nganhList", studentDAO.getAllNganh());
            model.addAttribute("error", "Ngày tốt nghiệp không hợp lệ (định dạng yyyy-MM-dd).");
            return "register";
        }
    }
}