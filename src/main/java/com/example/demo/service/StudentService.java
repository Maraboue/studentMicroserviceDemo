package com.example.demo.service;


import com.example.demo.model.Roles;
import com.example.demo.repository.StudentRepository;
import com.example.demo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Transactional
@Service
public class StudentService {

    final Logger logger = LoggerFactory.getLogger(StudentService.class);


    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        logger.info("New search for all students at:" + LocalDate.now() + LocalTime.now());
        return studentRepository.findAll();
    }

    public List<Student> getAllStudentsWithIdOverFive(){
        List<Student> studentIdOverFive = studentRepository.findAll().stream().
                filter(student -> student.getId()>5).collect(Collectors.toList());
        return studentIdOverFive;
    }

    public List<String> getAllStudentsByMail() {
        logger.info("New search for all students by mail at:" + LocalDate.now() + LocalTime.now());
        // Create list of strings containing mail with stream.
        // Mapping the list to student by mail.
        List<String> studentMails = studentRepository.findAll().
                stream().map(student -> student.getMail() ).
                collect(Collectors.toList());
        return studentMails;

    }

    public String addStudent(Student student){

        Optional<Student> existingStudent = studentRepository.studentEmailExists(student.getMail());
        if(existingStudent.isPresent()) {
            Student s = existingStudent.get();
            logger.info("Tried to add new student with mail: "+ s.getMail()+" at:" + LocalDate.now() + LocalTime.now());
            return "Student with "+ s.getMail() + " already exists!";
        }
         else
            studentRepository.save(student);
            logger.info("New student added with mail: "+ student.getMail()+" at:" + LocalDate.now() + LocalTime.now());
            return "Student with name:" + student.getName() + " and mail: "+student.getMail()+" added!";
    }

    public Student findStudentById(long id) {

        Optional<Student> optStudent = studentRepository.findById(id);
        if(optStudent.isPresent()) {
            Student student = optStudent.get();
            return student;
        }
        else
            return null;
    }

    public String addRoleToStudent(long id, String role){
        Optional<Student> optStudent = studentRepository.findById(id);
        System.out.println(optStudent.toString());
        if(optStudent.isPresent()) {
            Student student = optStudent.get();
            studentRepository.updateStudentRole(student.getMail(),Roles.valueOf(role));
            logger.info("Student with mail: "+ student.getMail()+" updated with role: " +role+" at: " + LocalDate.now()+" "+LocalTime.now());
            return "Student with mail: " + student.getMail() + "Updated with role: "+ role;
        }
        else
            return null;
    }

    public ResponseEntity<HttpStatus> deleteStudentByMail(String mail) {
        try{
            Optional<Student> student = studentRepository.studentEmailExists(mail);
            if(student.isPresent()){
                studentRepository.delete(student.get());
                logger.info("Student with mail: "+ mail +" deleted at: " + LocalDate.now()+" "+LocalTime.now());
                return new ResponseEntity<HttpStatus>(HttpStatus.OK);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
