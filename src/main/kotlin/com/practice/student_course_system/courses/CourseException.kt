package com.practice.student_course_system.courses

import org.springframework.http.HttpStatus

class CourseException private constructor(
    val status: HttpStatus,
    override val message: String
) : RuntimeException(message) {

    companion object {

        fun notFound(courseId: Long) = CourseException(
            HttpStatus.NOT_FOUND,
            "Course with ID $courseId not found"
        )

        fun duplicateTitle(title: String) = CourseException(
            HttpStatus.CONFLICT,
            "Course with title '$title' already exists"
        )

//        fun invalidDescription() = CourseException(
//            HttpStatus.BAD_REQUEST,
//            "Course description must not be blank"
//        )
    }
}
