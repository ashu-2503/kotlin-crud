package com.practice.student_course_system.student

import org.springframework.stereotype.Service

@Service
interface StudentService {
    fun createStudent(dto: StudentDTO): Student
    fun getAllStudents() : List<Student>
    fun getStudentById(id: Long) : Student
    fun updateStudent(student: Student) : Student
    fun deleteStudent(id:Long)
}