@Android @ComputeSum
Feature: Compute Sum
  Background:
    Given The user is on the landing screen

  Scenario Outline: The user adds two numbers and computes a sum
    And The user enters the first "<firstnumber>"
    And The user enters the second "<secondnumber>"
    And The user clicks the compute button
    Then The sum is "<sum>"
    Examples:
      |firstnumber|secondnumber|sum|
      |10         |10          |20 |
      |-14        |12          |-2 |

  Scenario: Verify page title is correct
    Then The title reads "Welcome to NBK DVP"










