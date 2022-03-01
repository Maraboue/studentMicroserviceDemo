package com.example.demo.repository;

import com.example.demo.model.Role;
import com.example.demo.model.Roles;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
   @Query(" SELECT s FROM Student s WHERE s.mail = ?1")
    Optional<Student> studentEmailExists(String email);

   @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Student s WHERE s.mail =?1")
    Boolean checkIfMailExists(String mail);

   @Transactional
   @Modifying
   @Query("UPDATE Student s set s.role=:role WHERE s.mail =:mail")
    void updateStudentRole(String mail, Roles role);


}
