package com.practice.student_course_system.courses

import com.fasterxml.jackson.annotation.JsonIgnore
import com.practice.student_course_system.student.Student
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "courses")
public class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable= false)
    var title: String,

    @Column()
    var description: String,

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    var students: MutableSet<Student> = mutableSetOf()
)