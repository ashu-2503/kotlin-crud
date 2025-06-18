package com.practice.student_course_system.courses

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, Long> {
    fun existsByTitle(title: String): Boolean
}