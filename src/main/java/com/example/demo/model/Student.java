package com.example.demo.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "student_sequence",
            strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String mail;
    private Gender gender;

    private Roles role;


    public Student(){

    }

    public Student(String name,String mail,Gender gender, Roles role){
        this.name = name;
        this.mail = mail;
        this.gender = gender;
        this.role = role;
    }

    public Student(String name, String mail, Gender gender) {
        this.name = name;
        this.mail = mail;
        this.gender = gender;
    }

    public Long getId(){ return this.id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setRole(Roles role){
        this.role = role;
    }

    public Roles getRoles(){
        return role;
    }

    // ADD builder class.

}
