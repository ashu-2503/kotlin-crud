package com.practice.student_course_system.student

import com.practice.student_course_system.courses.Course
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "students")
public class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:NotBlank(message = "Name cannot be blank")
    @Column(nullable= false)
    var name: String,

    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    var email: String,

    @ManyToMany(fetch = FetchType.LAZY, cascade = [ CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "students_courses",
        joinColumns = [JoinColumn(name="student_id")],
        inverseJoinColumns = [JoinColumn(name="course_id")]
    )
    var courses: MutableSet<Course> = mutableSetOf()
)