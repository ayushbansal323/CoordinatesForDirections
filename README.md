# CoordinatesForDirections
gives the direction for equal distance coordinates

## Test the application 

### Heroku Deployed Application

 An application is already deployed
 
  * To test the application following curl can be used
  * these are the following variable that you can change from the curl to test the application
    *   startLatitude :- latitude of the start coordinate
    *   startLongitude :- longitude of the start coordinate
    *   endLatitude :- latitude of the end coordinate 
    *   endLongitude :- longitude of the end coordinate
 
 ```
 curl --location --request GET 'https://coordinatesfordirections.herokuapp.com/directions?startLatitude=12.93175&startLongitude=77.62872&endLatitude=12.92662&endLongitude=77.63696'
 ```

### Run Locally  

#### As a Java application

PREREQ
  * jdk >= 1.8 
  * maven
 
STEPS
  * clone the repo ```  git clone https://github.com/ayushbansal323/CoordinatesForDirections.git ```
  * open terminal
  * set ENV variable API_KEY to AIzaSyAEQvKUVouPDENLkQlCF6AAap1Ze-6zMos https://www.poftut.com/how-to-set-environment-variables-for-linux-windows-bsd-and-macosx/
  * ``` mvn clean install ```
  * ``` mvn exec:java -Dexec.mainClass="com.locus.main.Main" ```

### As a server application


PREREQ
  * jdk >= 1.8 
  * maven
  * tomcat

STEPS
  * ``` mvn clear install package ```
  * copy the war file in the target to your server
  * set ENV variable in tomcat/global ENV variable with key API_KEY to AIzaSyAEQvKUVouPDENLkQlCF6AAap1Ze-6zMos 
  * hit the following curl to test
  ``` 
 curl --location --request GET 'BASE_URL/directions?startLatitude=12.93175&startLongitude=77.62872&endLatitude=12.92662&endLongitude=77.63696'
 ```
