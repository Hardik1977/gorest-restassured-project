package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static com.gorest.utils.TestUtils.getRandomValue;
import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase{


        @Test
        public void verifyUserCreatedSuccessfully() {
            UserPojo userPojo = new UserPojo();
            userPojo.setName("Manan Shah");
            userPojo.setEmail(getRandomValue()+"1234test@gmail.com");
            userPojo.setGender("Male");
            userPojo.setStatus("Active");
            Response response =
                    given().log().all()
                            .header("Content-Type", "application/json")
                            .header("Authorization", "Bearer 046d0902c2f16250ea507c8925b1c0f06339b73f2509801ebdd3724d5ce52893")
                            .body(userPojo)
                            .when()
                            .post("/users");
            response.then().statusCode(201);
            response.prettyPrint();
        }

        @Test
        public void getAllCustomersInfo() {
            Response response = given()
                    .when()
                    .get("/users");
            response.then().statusCode(200);
            response.prettyPrint();

        }

        @Test
        public void verifyUserUpdateSuccessfully() {
            UserPojo userPojo = new UserPojo();
            userPojo.setName("Pratik Patel");
            userPojo.setEmail("priam@gmail.com");
            userPojo.setGender("Male");
            userPojo.setStatus("Active");

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer 046d0902c2f16250ea507c8925b1c0f06339b73f2509801ebdd3724d5ce52893")
                    .body(userPojo)
                    .when()
                    .put("/users/10459");
            response.then().statusCode(200);
            response.prettyPrint();
        }

        @Test
        public void deleteUser() {
            Response response = given()

                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer 046d0902c2f16250ea507c8925b1c0f06339b73f2509801ebdd3724d5ce52893")
                    .when()
                    .delete("/users/10437");//need take other uder/id
            response.then().statusCode(204);
            response.prettyPrint();
        }

    }



