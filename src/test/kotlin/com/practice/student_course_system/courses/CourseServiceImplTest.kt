package com.practice.student_course_system.courses

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.Assertions.assertThrows
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.Optional

@ExtendWith(MockitoExtension::class)
class CourseServiceImplTest {

    @Mock
    private lateinit var courseRepository: CourseRepository

    private lateinit var courseService: CourseServiceImpl

    val id = 99L
    val courseDto = CourseDTO(title = "Spring Boot", description = "Learn Backend")

    val course1 = Course(
        id = 1L,
        title = "Kotlin Basics",
        description = "Intro to Kotlin",
        students = mutableSetOf()
    )

    val course2 = Course(
        id = 2L,
        title = "Spring Boot",
        description = "Build REST APIs",
        students = mutableSetOf()
    )

    val courses = listOf(course1, course2)

    @BeforeEach
    fun setUp() {
        courseService = CourseServiceImpl(courseRepository)
    }

    @Test
    fun `should create course when title is unique`() {
        whenever(courseRepository.existsByTitle(courseDto.title)).thenReturn(false)
        whenever(courseRepository.save(any<Course>())).thenReturn(course1)

        val result = courseService.createCourse(courseDto)

        assertEquals(course1, result)
        verify(courseRepository).existsByTitle(courseDto.title)
        verify(courseRepository).save(any())
    }

    @Test
    fun `should throw exception when title is not unique`(){
        whenever(courseRepository.existsByTitle(courseDto.title)).thenReturn(true)

        val exception = assertThrows(CourseException::class.java) {
            courseService.createCourse(courseDto)
        }
        assertEquals("Course with title '${courseDto.title}' already exists",exception.message)
        verify(courseRepository).existsByTitle(courseDto.title)
        verify(courseRepository, never()).save(any())

    }

    @Test
    fun `should return all courses when repository returns list`(){
        whenever(courseRepository.findAll()).thenReturn(courses)

        val resultAllCourses = courseService.getAllCourses()

        assertEquals(courses,resultAllCourses)
        verify(courseRepository).findAll()
    }

    @Test
    fun `should return course when course with given id exists`(){
        whenever(courseRepository.findById(id)).thenReturn(Optional.of(course1))

        val resultCourse = courseService.getCourseById(id)

        assertEquals(course1,resultCourse)
        verify(courseRepository).findById(id)
    }

    @Test
    fun `should throw exception when course with given id does not exist`(){
        whenever(courseRepository.findById(id)).thenReturn(Optional.empty())

        val exception = assertThrows(CourseException::class.java){
            courseService.getCourseById(id)
        }

        assertEquals("Course with ID $id not found",exception.message)
    }
}