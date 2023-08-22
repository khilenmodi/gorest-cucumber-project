package com.gorest.studentinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.PostsPojo;
import com.gorest.model.UserPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;


public class UsersSteps {
    static final String token = "Bearer 729b4b499368ff27fc26cd366161b6db88400b49b82f58751588a296c6bfd498";
    //
    @Step("creating user with name : {0}, email : {1}, gender : {2} and status : {3}")
    public ValidatableResponse createUsers(String name, String email, String gender, String status){
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setName(name);
        postsPojo.setEmail(email);
        postsPojo.setGender(gender);
        postsPojo.setStatus(status);
        return SerenityRest.given()
                .header("Content-Type","application/json")
             // .header("Authorization",token)
              //  .header("Connection", "keep-alive")
                .body(postsPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();

    }

    @Step("Getting user ID : {0}")
    public ValidatableResponse getUsersById(int userId) {
        return SerenityRest.given().log().all()
                .header("Authorization", token)
                .header("Connection", "keep-alive")
                .pathParam("id", userId)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then().log().all();

    }

    @Step ("Getting the users information with name : {0}")
    public HashMap<String, Object> getUsersInfoByName(String name) {
        String s1 = "findAll{it.name='";
        String s2 = "'}.get(0)";
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_USERS_NAME)
                .then().statusCode(200)
                .extract()
                .path(s1 + name + s2);
    }


    @Step("updating user information with userID : {0}")
    public ValidatableResponse updateUsers ( String name, String email, String status, int userId){
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .header("Connection", "keep-alive")
                .pathParam("id",userId)
                .when()
                .body(userPojo)
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then().statusCode(200);

    }

    @Step("Deleting the user information by userID : {0}")
    public ValidatableResponse deleteUsers(int userId) {
        return SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .header("Connection", "keep-alive")
                .pathParam("id",userId)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }
    @Step("verify the user by userId : {0}")
    public ValidatableResponse getUserById(int userId){
        return SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .header("Connection", "keep-alive")
                .pathParam("id", userId)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then();
    }

}


