package com.example.demo;

import com.example.demo.model.Gender;
import com.example.demo.model.Role;
import com.example.demo.model.Roles;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepositoryTest;

    @Test
    void checkIfStudentsEmailExists() {

        Student student = new Student(
                "Gustaf",
                "mail45",
                Gender.MALE
                ,Roles.ROLE_USER//new Role(1,Roles.ROLE_USER)
        );
        studentRepositoryTest.save(student);
        boolean exists = studentRepositoryTest.checkIfMailExists(student.getMail());

        assertThat(exists).isTrue();
    }

    @Test
    void checkIfDoubleMailExits(){

        List<Student> studentsList = studentRepositoryTest.findAll();
        boolean mailAlreadyExists = false;
        for(int i=0;i<studentsList.size();i++)
        {
            for(int y=i+1;y<studentsList.size();y++)
            {
                if(studentsList.get(i).getMail()==studentsList.get(y).getMail())
                {
                    mailAlreadyExists =true;
                }
            }
        }
        assertThat(mailAlreadyExists).isFalse();

       /* List<Student> studentsListFunctional = studentRepositoryTest.findAll().
                stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet().stream()
                .filter( student -> student.getValue() > 1)
                .map(Map.Entry::getKey).collect(Collectors.toList());*/

       Set<String> doubleMail = new HashSet();

        studentRepositoryTest.findAll().stream().
                filter(student -> !doubleMail.add(student.getMail())).collect(Collectors.toSet());

        System.out.println(doubleMail);
    }

    @Test
    void studentRoleUpdated(){

        // Create Student without role.
        Student student = new Student("name22","mail124",Gender.MALE,null);
        studentRepositoryTest.save(student);
        Optional<Student> studentWithoutRole =  studentRepositoryTest.studentEmailExists("mail124");

        // Assert that the student was saved without a role.
        if(studentWithoutRole.isPresent())
            assertThat(studentWithoutRole.get().getRoles()).isNull();

        // Update student role.
        studentRepositoryTest.updateStudentRole("mail124",Roles.ROLE_USER);
        Optional<Student> studentWithRole =  studentRepositoryTest.studentEmailExists("mail124");

        System.out.println(studentWithRole.get().getMail() + studentWithRole.get().getRoles());

        // Assert that the role has been updated.
        if(studentWithRole.isPresent())
            assertThat(studentWithRole.get().getRoles()).isEqualTo(Roles.ROLE_USER);




    }

}