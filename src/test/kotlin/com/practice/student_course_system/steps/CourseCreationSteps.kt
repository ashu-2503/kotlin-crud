package com.practice.student_course_system.steps

import com.practice.student_course_system.courses.CourseDTO
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.jupiter.api.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourseCreationSteps {
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    lateinit var requestBody: CourseDTO
    lateinit var response: ResponseEntity<CourseDTO>

    @Given("a course payload with title {string} and description {string}")
    fun createCoursePayloadWithTitleAndDescription(title: String, description: String) {
        requestBody = CourseDTO(title = title, description = description)
    }

    @When("the client sends POST request to {string}")
    fun clientSendsPostRequestTo(url: String) {
        val header = HttpHeaders()
        header.contentType = MediaType.APPLICATION_JSON
        val requestBody = HttpEntity(requestBody, header)

        response = restTemplate.postForEntity(url,requestBody, CourseDTO::class.java)
    }

    @Then("the response status should be {int}")
    fun theResponseStatusShouldBe(expectedStatus: Int) {
        assertEquals(expectedStatus,response.statusCode.value())
    }

    @And("the response body should contain title {string}")
    fun theResponseBodyShouldContainTitle(title: String) {
        assertNotNull(response.body)
        assertEquals(title, response.body!!.title)
    }
}
