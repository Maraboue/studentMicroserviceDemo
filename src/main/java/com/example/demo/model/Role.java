package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Role {
    @Id
    long id;
    Roles role;

    public Role(long id, Roles role){
        this.id = id;
        this.role = role;
    }

    public Role(){};

    @Override
    public String toString(){
        return this.id + this.role.toString();
    }

    @ManyToOne(optional = false)
    private Student students;

    public Student getStudents() {
        return students;
    }

    public void setStudents(Student students) {
        this.students = students;
    }
}
