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

#### As a server application


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


## Code Structure 

* com/locus/main/Main.java is the starting point for the JAVA application
* com/locus/controller/DirectionsController.java is the starting controller for the server application
* com.locus.util package contains all the Utility files
* com.locus.spring package contains spring config and initialization packages
* com.locus.pojo contains all the pojos
* com/locus/util/Configurations.java files contains configurations that are needed in the application which are initialize during run time no instance of the class can be created


## Sample request and response

```
curl --location --request GET 'https://coordinatesfordirections.herokuapp.com/directions?startLatitude=12.93175&startLongitude=77.62872&endLatitude=12.92662&endLongitude=77.63696'
```

response 

``` 
{
    "points": [
        "12.93167000,77.62855000",
        "12.93126750,77.62873563",
        "12.93085500,77.62892375",
        "12.93046000,77.62910625",
        "12.93004000,77.62928000",
        "12.92993875,77.62970609",
        "12.92983469,77.63014402",
        "12.92972766,77.63062078",
        "12.92963076,77.63106652",
        "12.92954000,77.63152000",
        "12.92947000,77.63197500",
        "12.92959000,77.63241000",
        "12.92975500,77.63281000",
        "12.92993375,77.63320875",
        "12.93004313,77.63347625",
        "12.92976375,77.63367063",
        "12.92954000,77.63405500",
        "12.92935500,77.63442750",
        "12.92917250,77.63482688",
        "12.92896625,77.63522875",
        "12.92876691,77.63561793",
        "12.92854625,77.63604875",
        "12.92816000,77.63592500",
        "12.92793000,77.63627000",
        "12.92767000,77.63663500",
        "12.92737250,77.63697750",
        "12.92699500,77.63717000",
        "12.92659000,77.63712000"
    ]
} 
```

## Test cases

test cases can be found in 
https://docs.google.com/spreadsheets/d/1XyrvWBsKxaNdnBVlt1HsrkUBxx4hTUjx6TIrS289W_Q/edit?usp=sharing


## sample results

![picture alt](https://drive.google.com/uc?export=view&id=1tK4i_44G3CixIPUEHDB3QxF9VzU_gUEZ "Curve Road")

![picture alt](https://drive.google.com/uc?export=view&id=1VAt2_y_FCzu6wn4SGiY_TuU3UognEhyP "straight road")

![picture alt](https://drive.google.com/uc?export=view&id=1zWHm_mtj_PbyOOGGpO7FA2aMMObZfItB "zigzag road")

