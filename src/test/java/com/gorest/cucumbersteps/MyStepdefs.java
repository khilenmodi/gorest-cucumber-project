package com.gorest.cucumbersteps;

import com.gorest.studentinfo.UsersSteps;
import com.gorest.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class MyStepdefs {
//

    static ValidatableResponse response;
    static String email = null;
    static String name = null;
    static  String gender;
    static String status;
    static int userId;

    @Steps
    UsersSteps usersSteps;


    @When("^I create a new users by providing the information name \"([^\"]*)\" email \"([^\"]*)\" gender \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iCreateANewUsersByProvidingTheInformationNameEmailGenderStatus(String _name, String _email, String gender, String status) {
        email = TestUtils.getRandomValue()+_email;
        name = TestUtils.getRandomValue()+_name;
        usersSteps.createUsers(name, email, gender, status).statusCode(201);
    }

    @Then("^I get all user information by ID$")
    public void iGetAllUserInformationByID(int userId) {
        response = usersSteps.getUsersById(userId);
    }

    @When("^I update the user with information name \"([^\"]*)\" email \"([^\"]*)\"  gender \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iUpdateTheUserWithInformationNameEmailGenderStatus(String _name, String _email,String gender, String status) {
        name = name + "_updated";
        email = email + "_updated";
        response = usersSteps.updateUsers (name, email, status, userId);
    }

    @Then("^The user deleted successfully from the application$")
    public void theUserDeletedSuccessfullyFromTheApplication() {
        usersSteps.deleteUsers(userId).statusCode(200);

    }


}