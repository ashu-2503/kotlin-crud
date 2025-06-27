package com.practice.student_course_system.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.student_course_system.courses.Course
import com.practice.student_course_system.courses.CourseDTO
import com.practice.student_course_system.courses.CourseRepository
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("integration")
class CourseIntegrationTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper,
    @Autowired val courseRepository: CourseRepository
) {
    @BeforeEach
    fun setUp() {
        courseRepository.deleteAll()
    }

    @Test
    fun `should create a course and return it`() {
        val courseDto = CourseDTO(title = "Spring Boot", description = "Learn Backend")
        val result = mockMvc.post("/courses") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(courseDto)
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.id") { exists() }
            jsonPath("$.title") { value("Spring Boot") }
            jsonPath("$.description") { value("Learn Backend") }
        }.andReturn()

        val response = objectMapper.readValue(result.response.contentAsString, Course::class.java)
        assertThat(response.title).isEqualTo("Spring Boot")
    }

    @Test
    fun `should fetch all courses`() {

    }
}