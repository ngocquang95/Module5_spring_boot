package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.StudentCreateDTO;
import com.example.studentmanagement.dto.StudentSearchDTO;
import com.example.studentmanagement.mapper.StudentMapper;
import com.example.studentmanagement.model.Clazz;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.IClazzService;
import com.example.studentmanagement.service.IStudentService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    @Qualifier("studentService") // Chọn triển khai theo tên bean
    private IStudentService studentService;

    @Autowired
    private IClazzService clazzService;

    @Autowired
    private StudentMapper studentMapper;

    @ModelAttribute("clazzList")
    public List<Clazz> getClazzList() {
        return clazzService.findAll();
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("studentCreateDTO", new StudentCreateDTO());
        return "student/create";
    }

    @GetMapping("/edit")
    public String showEdit(Model model, int id) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student/edit";
    }

    @GetMapping("")
    public String showList(Model model, StudentSearchDTO studentSearchDTO,
                           @PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute("clazzList", clazzService.findAll());
        Page<Student> studentList = studentService.search(studentSearchDTO, pageable);
        model.addAttribute("studentList", studentList);
        model.addAttribute("arrayPage", new int[studentList.getTotalPages()]); // 5 trang [0, 0, 0, 0, 0]
        model.addAttribute("sort", pageable.getSort().toString().replace(": ", ","));

        return "student/list";
    }

    @PostMapping("/create")
    public String create(Model model,
                         @Validated @ModelAttribute("studentCreateDTO") StudentCreateDTO studentCreateDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        new StudentCreateDTO().validate(studentCreateDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            // Trường hợp bị lỗi thì gửi lại clazzList
            model.addAttribute("studentCreateDTO", studentCreateDTO);
            return "student/create";
        }

        studentService.create(studentMapper.toStudentFromStudentCreateDTO(studentCreateDTO));
        redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
        return "redirect:/student";
    }



//    private void editStudent(HttpServletRequest request, HttpServletResponse response) {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Student student = studentService.findById(id);
//        student.setName(request.getParameter("name"));
//        student.setScore(Double.parseDouble(request.getParameter("score")));
//    }
}
