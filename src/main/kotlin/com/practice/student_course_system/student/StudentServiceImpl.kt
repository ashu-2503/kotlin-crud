package com.practice.student_course_system.student

import com.practice.student_course_system.courses.CourseRepository
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl(
    private val studentRepository: StudentRepository,
    private val courseRepository: CourseRepository
) : StudentService {
    override fun createStudent(dto: StudentDTO): Student {
        if (dto.courseIds.size > 3) {
            throw StudentException.maxCourseLimit()
        }
        val courses = courseRepository.findAllById(dto.courseIds).toMutableSet()
        val overEnrolled = courses.filter { it.students.size >= 20 }
        if (overEnrolled.isNotEmpty()) {
            val fullCourses = overEnrolled.joinToString { it.title }
            throw StudentException.courseFull(fullCourses)
        }
        if (studentRepository.existsByEmail(dto.email)){
            throw StudentException.duplicateEmail(dto.email)
        }
        if (courses.size != dto.courseIds.size) {
            throw StudentException.invalidCourseIds()
        }
        val student = Student(name = dto.name, email = dto.email, courses = courses)
        return studentRepository.save(student)
    }

    override fun getAllStudents(): List<Student> {
        return studentRepository.findAll()
    }

    override fun getStudentById(id: Long): Student {
        return studentRepository.findById(id).orElseThrow{
            StudentException.notFound(id)
        }
    }

    override fun updateStudent(student: Student): Student {
        val existingStudent = getStudentById(student.id
            ?: throw StudentException.nonNull())
        // Optional: Check if email is being updated to a duplicate
        if (existingStudent.email != student.email && studentRepository.existsByEmail(student.email)) {
            throw StudentException.duplicateEmail(student.email)
        }
        if (student.courses.isNotEmpty() && student.courses != existingStudent.courses) {
            throw StudentException.unauthorizedCourseUpdate()
        }
        existingStudent.name = student.name
        existingStudent.email = student.email
        return studentRepository.save(existingStudent)
    }

    override fun deleteStudent(id: Long) {
        val student = studentRepository.findById(id).orElseThrow { StudentException.notFound(id) }
        studentRepository.delete(student)
    }
}