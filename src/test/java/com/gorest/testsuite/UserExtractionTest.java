package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Extract the All Ids
    @Test
    public void test001() {
        System.out.println("------------------StartingTest---------------------------");
        List<Integer> totalId = response.extract().path("id");
        System.out.println("Total records are : " + totalId);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the all Names
    @Test
    public void test002() {
        System.out.println("------------------StartingTest---------------------------");
        List<String> listOfAllNames = response.extract().path("name");
        System.out.println("All names as per data : " + listOfAllNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th object

    @Test
    public void test0003() {
        System.out.println("------------------StartingTest---------------------------");
        String listName = response.extract().path("[4].name");
        System.out.println("Name of the 5th object : " + listName);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test0004() {
        System.out.println("------------------StartingTest---------------------------");
        List<?> status = response.extract().path("findAll{it.status=='inactive'}.name");
        System.out.println("Names of all object whose status = inactive : " + status);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test0005() {
        System.out.println("------------------StartingTest---------------------------");
        List<?> listOfStatusactive = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("Gender of all the object whose status = active : " + listOfStatusactive);
        System.out.println("------------------End of Test---------------------------");

    }
    //6. Print the names of the object whose gender = female
    @Test
    public void test0006(){
        System.out.println("------------------StartingTest---------------------------");
        List<?> allObjectGenderfemale = response.extract().path("findAll{it.gender=='female'}.id");
        System.out.println("Gender of all the object whose status = active : " + allObjectGenderfemale);
        System.out.println("------------------End of Test---------------------------");
    }
    //7. Get all the emails of the object where status = inactive
    @Test
    public void test0007(){
        System.out.println("------------------StartingTest---------------------------");
        List<?> objectWhereSatusInactive = response.extract().path("findAll{it.status=='inactive'}.gender");
        System.out.println("The emails of the object where status = inactive : " + objectWhereSatusInactive);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test0008(){
        System.out.println("------------------StartingTest---------------------------");
        List<?> idWhereGenderMale = response.extract().path("findAll{it.gender=='male'}.id");
        System.out.println("The list of ids of the object where gender = male : " + idWhereGenderMale);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void test009() {
        List<String> allStatus = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the status  : " + allStatus);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name = Goswami Trivedi
    @Test
    public void test010() {
        List<String> getEmailOfTheObject = response.extract().path("findAll{it.name=='Goswami Trivedi'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("email of the object where name = Goswami Trivedi  : " + getEmailOfTheObject);
        System.out.println("------------------End of Test---------------------------");
    }
    //11. Get gender of id = 2178439
    @Test
    public void test011() {
        List<?> getGenderOfTheObject = response.extract().path("findAll{it.id == '2178439'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get gender of id = 2178439  : " + getGenderOfTheObject);
        System.out.println("------------------End of Test---------------------------");
    }

}