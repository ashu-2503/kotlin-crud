Feature: Course Creation

  Scenario: Create a new course successfully
    Given a course payload with title "Kotlin Basics" and description "Intro to Kotlin"
    When the client sends POST request to "/courses"
    Then the response status should be 201
    And the response body should contain title "Kotlin Basics"