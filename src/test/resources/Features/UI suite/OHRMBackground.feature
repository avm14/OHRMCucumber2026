Feature: feature to test homepage functionality of OrangeHRM website

Background:
Given user is on OHRM login page
When user enters "Admin" credentials
And clicks on login button
Then user is navigated to the home page

@ui @smoke
Scenario: user gets employee search option on selecting admin tab
When the user clicks on admin tab
Then user is able to see employee search box

@ui @regression
Scenario: user is able to see leave list when he clicks leave tab
When the user clicks on leave tab
Then the user is able to see leave list