package com.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course{

    // define the fields

    // define the constructors

    // define the getters and the setters

    // define the to string

    // annotate the fields

    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name="title")
    private String title;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}) // fine grain the cascade types
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    public Course(){

    }

    public Course ( String title, Instructor instructor) {
        
        this.title = title;
        this.instructor = instructor;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return String.format("Course [id=%d, title=%s ]", id, title);
    }


}