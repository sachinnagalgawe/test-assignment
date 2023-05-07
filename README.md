READ ME

-----------
About :
-----------
 - Spring Boot application which can read the .csv file and check validation of data, once validated can save the data in users table.
 - It will show the report in console logs, it will show how many validation errors, duplicate records, and success records there were.


----------------------
Validations used:
----------------------
#1. User name atleast 5 char long

#2. Email id should be valid

#3. Phone Number should be valid with 10 digits


----------------------
Pre-requisite
----------------------
#1. Change the username and password for mysql in the application.properties as per your local mysql settings 

#2. If you want to test with your .csv file then place it in resources folder. (For sample there is already users.csv present)

#3. Change the following property in application.properties file -

csv.file.name=users.csv (replace your file name)


----------------------
How to Run ?
----------------------
#1. Import the project in ide

#2. Once imported you can search for UserimportApplicationTests.class and run userTest unit test method

#3. You should see report in your console and data should get stored/updated in mysql db
