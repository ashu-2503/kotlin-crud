package com.practice.student_course_system.student

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

    @RestController
    @RequestMapping("students")
    class StudentController(private val studentService: StudentService){

        @PostMapping()
        fun createStudents(@RequestBody @Valid dto: StudentDTO) : ResponseEntity<Student>{
            val saved = studentService.createStudent(dto)
            return ResponseEntity.status(HttpStatus.CREATED).body(saved)
        }

        @GetMapping()
        fun getAllStudents() : ResponseEntity<List<Student>?> =
            ResponseEntity.ok(studentService.getAllStudents())

        @GetMapping("{id}")
        fun getStudentById(@PathVariable id : Long) : ResponseEntity<Student> =
            ResponseEntity.ok(studentService.getStudentById(id))

        @PutMapping()
        fun updateStudent(@RequestBody @Valid student: Student) : ResponseEntity<Student>{
            val updatedStudent = studentService.updateStudent(student)
            return ResponseEntity.ok(updatedStudent)
        }

        @DeleteMapping("{id}")
        fun deleteStudent(@PathVariable id : Long) : ResponseEntity<Void> {
            studentService.deleteStudent(id)
            return ResponseEntity.noContent().build()
        }
    }
