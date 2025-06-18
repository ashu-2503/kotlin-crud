package com.practice.student_course_system.student

import org.springframework.http.HttpStatus

class StudentException private constructor(
    val status: HttpStatus,
    override val message: String
): RuntimeException(message) {

    companion object{
        fun duplicateEmail(email: String) = StudentException(
            HttpStatus.CONFLICT,
            "Email '$email' is already registered"
        )

        fun notFound(id: Long) = StudentException(
            HttpStatus.NOT_FOUND,
            "Student with ID $id not found"
        )

        fun invalidCourseIds() = StudentException(
            HttpStatus.NOT_FOUND,
            "Invalid course ID provided"
        )

        fun maxCourseLimit() = StudentException(
            HttpStatus.BAD_REQUEST,"A student can enroll in a maximum of 3 courses"
        )

        fun courseFull(fullCourses : String) = StudentException(
            HttpStatus.BAD_REQUEST,"Courses full: $fullCourses"
        )

        fun nonNull() = StudentException(
            HttpStatus.BAD_REQUEST,
            "Student ID must not be null"
        )

        fun unauthorizedCourseUpdate() = StudentException(
            HttpStatus.BAD_REQUEST,
            "You are not authorised to update the courses")
    }
}