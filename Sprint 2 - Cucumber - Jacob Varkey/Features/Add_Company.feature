  
Feature: Adding a Company Function

	Background: Profile Page Setup
			Given User is in Login Page
    	When User enters valid Email "jacobliverpoolfc@gmail.com"
   	 	And User enter valid Password "N3wP@33w0rD" 
    	And User clicks Login
    	Then User is redirected to profile page successfully
	
  Scenario: Adding a new Company
    	Given User is in profile page
    	When User clicks Company tab
    	And User clicks New Company 
   	 	And User enters Company Name "First Company"
    	And User clicks save 
    	Then New company is successfully created
    	

  Scenario: Checking for Null Value in Company 
    	Given User is in profile page
    	When User clicks Company tab
    	And User clicks New Company
    	And User clicks save 
    	Then Company Error Message is returned successfully
    	
  Scenario: Adding a new contact
    	Given User is in profile page
    	When User clicks Contacts tab
    	And User clicks New Contact
   	 	And User enters First Name "Jake"
    	And User enters Last Name "Castle" 
    	And User clicks save
    	Then New contact is successfully created
    	
  Scenario: Checking for Null Value in contact
    	Given User is in profile page
    	When User clicks Contacts tab
    	And User clicks New Contact
    	And User clicks save
    	Then Contact Error Message is returned successfully
    	
  Scenario: Adding a new deal
    	Given User is in profile page
    	When User clicks Deals tab
    	And User clicks New Deal
    	And User enters deal Title "First Deal"
    	And User clicks save
    	Then New deal is successfully created
    	
  Scenario: Checking for Null Value in deal 
    	Given User is in profile page
    	When User clicks Deals tab
    	And User clicks New Deal
    	And User clicks save
    	Then Deal error message is returned
  
  Scenario: Adding a new task
    	Given User is in profile page
    	When User clicks Tasks tab
    	And User clicks New Task
    	And User enters task Title "First Task"
    	And User enters due date "04/12/2020 17:30"
    	And User clicks save
    	Then New task is successfully created
    	
  Scenario: Checking for Null Value in task
    	Given User is in profile page
    	When User clicks Tasks tab
    	And User clicks New Task
    	And User clicks save
    	Then Task Error Message is returned
    	
  Scenario: Viewing a created task on Calendar
			Given User is in profile page
    	When User clicks Calendar tab
    	Then User can view created tasks on calendar successfully
    	
