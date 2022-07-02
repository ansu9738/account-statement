###GETTING STARTED###
For starting the application everything is in place just import in IDE and start as a
java application or spring boot application

1) For accessing localhost:8080/statements?accountId=3 please use browser so that you can
   sign in. Please provide the admin credentials(username: admin, password: user). Other than
   admin users this endpoint is not accessible.
   
2)For accessing the date range specific data please provide the data similar to the below 
  url: localhost:8080/statements/daterange?fromDate=03.09.2020&toDate=29.11.2020
  both the fromDate and toDate should be passed as query parameters.
  This api/endpoint is also accessible for admin users only.
  
 3) For accessing the price range specific data please provide the data similar to the below 
  url: localhost:8080/statements/pricerange?startPrice=120.960701706629&endPrice=183.480688227474
  both the startPrice and endPrice should be passed as query parameters.
  This api/endpoint is also accessible for admin users only.
  
  4) For accessing the localhost:8080/statements/threemonths no parameters are required.
     this api will return the first 3 rows from the DB. This api/endpoint is accessible
     by both user and admin
     
  5) If any other user other than admin and user, the response from the login page will be    
     bad credentials, in backend it is 401 unauthorized.
  
  6) For running all the test please give mvn:test as a goal.

