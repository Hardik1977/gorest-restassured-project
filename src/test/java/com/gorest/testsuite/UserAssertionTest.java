package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {

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

    // 1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("total.size", equalTo(20));
    }

    //2. Verify the if the name of id = 2272683 is equal to ”Chaitan Iyengar”
    @Test
    public void test0002() {
        response.body("[0].name", equalTo("Chaitan Iyengar"));
    }

    //3.Check the single ‘Name’ in the Array list (Miss Ekaling Ganaka)
    @Test
    public void test003() {
        response.body("[5].name", equalTo("Miss Ekaling Ganaka"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Chaitan Chaturvedi", "Gouranga Johar", "SDharmaketu Iyengar DVM)
    @Test
    public void test004() {
        response.body("name", hasItems("Chaitan Chaturvedi", "Gouranga Johar", "SDharmaketu Iyengar DVM"));
    }

    //5. Verify the emai of userid =  2272683 is equal “iyengar_chaitan@bednar.test”
    @Test
    public void test005() {
        response.body("[0].email", equalTo("iyengar_chaitan@bednar.test"));
    }

    //6.Verify the status is “Active” of user name is “Dharmaketu Iyengar DVM”
    @Test
    public void test006() {
        response.body("[2].status", equalTo("active"));
    }

    // 7. Verify the Gender = male of user name is "Gouranga Johar"
    @Test
    public void test007() {
        response.body("[1].gender", equalTo("male"));
    }

}



