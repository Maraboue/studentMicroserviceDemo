package com.example.demo;


import com.example.demo.model.Role;
import com.example.demo.model.Roles;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("/getStudentById")
    public Student getStudentById(long id){
        return studentService.findStudentById(id);
    }

    @PutMapping("/addRoleToStudent")
    public String addRoleToStudent(long id, String role) {
        return studentService.addRoleToStudent(id,role);
    }

    @GetMapping("/allStudentMails")
    public List<String> getAllStudentsMail(){
        return studentService.getAllStudentsByMail();
    }

    @GetMapping("/studentsIdOverFive")
    public List<Student> getAllStudentIdsOverFive(){return studentService.getAllStudentsWithIdOverFive();}

    @DeleteMapping(value = "/deleteStudent/{mail}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable String mail){
        System.out.println("in delete student");
            return studentService.deleteStudentByMail(mail);
    }

}
