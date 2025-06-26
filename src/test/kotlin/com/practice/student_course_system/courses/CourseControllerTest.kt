package com.practice.student_course_system.courses

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CourseController::class)
@AutoConfigureMockMvc
class CourseControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var courseService: CourseService

    val objectMapper = ObjectMapper().registerKotlinModule()
    val id = 99L
    val courseDto = CourseDTO(title = "Spring Boot", description = "Learn Backend")
    val invalidCourseDto = CourseDTO(title = "", description = "Learn Backend")
    val course = Course(
        id = 99L,
        title = "Kotlin Basics",
        description = "Intro to Kotlin",
        students = mutableSetOf()
    )

    @Test
    fun `should create user when title is unique`() {
        whenever(courseService.createCourse(courseDto)).thenReturn(course)

        val courseJson = objectMapper.writeValueAsString(courseDto)

        mockMvc.perform(
            post("/courses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(courseJson)
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(99L))
            .andExpect(jsonPath("$.title").value("Kotlin Basics"))
            .andExpect(jsonPath("$.description").value("Intro to Kotlin"))
    }

    @Test
    fun `should return 400 when title is blank`(){
        val courseJson = objectMapper.writeValueAsString(invalidCourseDto)

        mockMvc.perform(
            post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(courseJson)
        )
            .andExpect(status().isBadRequest)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value("Title cannot be blank"))
    }

    @Test
    fun `should return course when course with given id exists`(){
        whenever(courseService.getCourseById(id)).thenReturn(course)

        val courseJson = objectMapper.writeValueAsString(id)

        mockMvc.perform(
            get("/courses/$id")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.title").value("Kotlin Basics"))
            .andExpect(jsonPath("$.description").value("Intro to Kotlin"))
    }

}