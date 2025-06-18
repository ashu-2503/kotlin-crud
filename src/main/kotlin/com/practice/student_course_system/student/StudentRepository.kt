package com.practice.student_course_system.student

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<Student,Long> {
    fun existsByEmail(email: String): Boolean
}
