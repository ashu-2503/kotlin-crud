package com.practice.student_course_system.courses

import org.springframework.stereotype.Service

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository
) : CourseService {

    override fun createCourse(dto: CourseDTO): Course {
        if (courseRepository.existsByTitle(dto.title)) {
            throw CourseException.duplicateTitle(dto.title)
        }
        val course = Course(title = dto.title,description = dto.description)
        return courseRepository.save(course)
    }

    override fun getAllCourses(): List<Course> = courseRepository.findAll()

    override fun getCourseById(id: Long): Course =
        courseRepository.findById(id).orElseThrow {
            CourseException.notFound(id)
        }
}
