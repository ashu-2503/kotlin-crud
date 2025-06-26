package com.practice.student_course_system.courses

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/courses")
class CourseController(private val courseService: CourseService) {

    @PostMapping
    fun create(@RequestBody @Valid dto: CourseDTO): ResponseEntity<Course> {
        val saved = courseService.createCourse(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(saved)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<Course>> =
        ResponseEntity.ok(courseService.getAllCourses())

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Course> =
        ResponseEntity.ok(courseService.getCourseById(id))
}
