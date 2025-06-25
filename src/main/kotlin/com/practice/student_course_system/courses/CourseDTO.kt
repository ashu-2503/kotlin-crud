package com.practice.student_course_system.courses

import jakarta.validation.constraints.NotBlank

data class CourseDTO(
    @field:NotBlank(message = "Title cannot be blank")
    val title: String,

    val description: String
)
