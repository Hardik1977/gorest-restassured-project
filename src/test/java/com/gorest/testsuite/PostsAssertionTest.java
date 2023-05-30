package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostsAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size", equalTo(10));
    }

    //2. Verify the if the title of id = 39304 is equal to ”Qui sub magnam acquiro carcer.”
    @Test
    public void test002() {
        response
                .body("[1]", hasEntry("title", "Qui sub magnam acquiro carcer."))
                .body("[1]", hasEntry("id", 39304));
    }

    //3. Check the single user_id in the Array list (2272684)
    @Test
    public void test003() {
        response.body("user_id", hasItem(2272684));
    }

    //4. Check the multiple ids in the ArrayList (2250466,2250463,2272670)
    @Test
    public void test004() {
        response.body("user_id", hasItems(2272670, 2272669, 2272666));
    }

    //5. Verify the body of userid = 2272684 is equal “Adhuc crebro odit. Tres tredecim cubo. Adfectus universe crustulum. Thorax altus varius. Defigo utor succurro. Denique enim aliqua. Similique torqueo cogo. Succurro triginta thymum. Delectatio desolo atrox. Damno expedita accendo. Nemo cursim tenetur. Utor tamen qui. Ambitus quos baiulus. Sollicito vicissitudo cras. Voluptas numquam sperno. Cultellus auris curriculum. Cogito sodalitas quia. Adaugeo blandior amplus. Tolero libero capio."”
    @Test
    public void test005() {
        response.body("[0].body", equalTo("Adhuc crebro odit. Tres tredecim cubo. Adfectus universe crustulum. Thorax altus varius. Defigo utor succurro. Denique enim aliqua. Similique torqueo cogo. Succurro triginta thymum. Delectatio desolo atrox. Damno expedita accendo. Nemo cursim tenetur. Utor tamen qui. Ambitus quos baiulus. Sollicito vicissitudo cras. Voluptas numquam sperno. Cultellus auris curriculum. Cogito sodalitas quia. Adaugeo blandior amplus. Tolero libero capio."));
    }


}








