Feature: Login Function

  Scenario Outline: Login Failure
    	Given User is in Login Page
    	When User enters email <user> 
   	 	And User enter password <pass>
    	And User clicks Login 
    	But Credentials are invalid
    	Then Invalid Login message is displayed
  
   Examples: 
      | user  				 | pass 		| 
      | user1					 | p@SSw0rd | 
     

      
 
   Scenario: Login Success
   		Given User is in Login Page
    	When User enters valid Email "jacobliverpoolfc@gmail.com"
   	 	And User enter valid Password "N3wP@33w0rD"
    	And User clicks Login 
    	Then User logs in successfully   