Feature: to check new claim submission process 

Background:
Given user is on OHRM login page
When user enters "Admin" credentials
And clicks on login button
Then user is navigated to the home page

Scenario Outline: user is able to add an expense and submit a new claim
When user clicks on claim menu
And user navigates to submit claim tab
When user creates a claim with "<eventType>" and "<currency>" 
Then claim reference ID is created with "<statusMsg>" status
When user adds an expense with "<expenseType>" "<amount>" and "<date>" and clicks on submit
Then a record is created in the expense table with given "<eventType>" and "<date>"
When user clicks on submit
Then a new record is created with the "<eventType>" in the claim menu

Examples: valid claim details
|eventType|currency|statusMsg|expenseType|amount|date|
|Accommodation|Canadian Dollar|Initiated|Accommodation|1414|2025-02-02|
