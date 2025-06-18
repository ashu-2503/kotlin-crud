package com.practice.student_course_system.courses

interface CourseService {
    fun createCourse(dto: CourseDTO): Course
    fun getAllCourses(): List<Course>
    fun getCourseById(id: Long): Course
}


