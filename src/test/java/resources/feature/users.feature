Feature: Testing different request on the gorest application

  Scenario Outline: Create a new user & verify if the user is added
    When I create a new users by providing the information name "<name>" email "<email>" gender "<gender>" status "<status>"
    Examples:
      | name       | email             | gender | status |
      | khilenmodi | khilen1@gmail.com | Male   | Active |

  Scenario: Get user detail
    Then I get all user information by ID

  Scenario Outline: update user detail
    When I update the user with information name "<name>" email "<email>"  gender "<gender>" status "<status>"
    Examples:
      | name      | email               | gender | status |
      | Som Pilla | khilen123@gmail.com | Female   | Inactive |

  Scenario: Delete user detail
    Then The user deleted successfully from the application







