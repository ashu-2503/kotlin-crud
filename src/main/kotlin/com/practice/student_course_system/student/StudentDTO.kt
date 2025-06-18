package com.practice.student_course_system.student

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class StudentDTO(
    @field:NotBlank(message = "Name cannot be blank")
    val name: String,

    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Email format is invalid")
    val email: String,

    val courseIds: List<Long>
)
