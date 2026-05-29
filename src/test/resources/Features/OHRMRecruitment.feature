Feature: To test recruitment functionality 

Background:
Given user is on OHRM login page
When user enters "Admin" credentials
And clicks on login button
Then user is navigated to the home page
When user clicks on recruitment tab
And user clicks on add button
And user enters candidate deatils for "Payroll Administrator" and clicks submit

#Scenario: A new record is created under the recruitment tab when a new candidate has been added
#Then under recruitment tab a new record is created "Payroll Administrator" with current date

Scenario Outline: User is able to schedule interview and select the candidate
When user clicks on actions button from records table for "Payroll Administrator"
And shortlists the candidate
And schedules an interview with "<date>" and "<time>"
And marks the interview as pass
Then user is able to offer job and application status changes to "<status>"
Examples:

| date | time | status |
| 2026-04-28 | 4:30 PM | Status: Hired |


#Scenario: user is able to schedule interview and reject a candidate 
#dummy comment from my3
