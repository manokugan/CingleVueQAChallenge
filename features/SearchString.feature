Feature: Search Functionality

Scenario: Search String "Test" in Google

Given Google Page is Loaded
When Search String Test
Then returned result is more than 1000000000
