package com.practice.student_course_system.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.student_course_system.courses.CourseRepository
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("integration")
class CourseIntegrationTest (
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper,
    @Autowired val courseRepository: CourseRepository
){
    @BeforeEach
    fun setUp(){
        courseRepository.deleteAll()
    }

    @Test
    fun`should create a course and return it`(){

    }

    @Test
    fun `should fetch all courses`() {

    }
}