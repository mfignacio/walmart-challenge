# Solution for Walmart Challenge

This is my proposed solution for the Walmart challenge completed for the Linets interview process.
I have created a REST API which has three endpoints:

- /getAllProducts/ : which will get all porducts listed in the database.
- /getProductById/{id} : which will get a single product fot that id.
- /getProductByBrandAndDescription/{search} : which will return all the products that contian the search term in either in their description or brand. 

If the search is a palindrome, the price of the products that belong to the result of the query will have their price discounted in half.

To run this solution:
You should move to the ´resources´ directory where the ´docker-compose.yml´ file is located (challenge\src\main\resources) and run the ´docker-compose up´ command.
The following images will be downloaded from DockerHub:
- mfignacio12/spring-walmart
- mfignacio12/mongo

After that both containers will start and the spring-walmart one will populate the mongo container with test data. 

You should be able to make requests to the spring-walmart container importing the following Postman requests: https://www.getpostman.com/collections/dc850ecff22afcc19d65
