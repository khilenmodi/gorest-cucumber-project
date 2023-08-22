package com.gorest.testsuite;

import com.gorest.studentinfo.UsersSteps;
import com.gorest.testbase.TestBase;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;

@UseTestDataFrom("src/test/java/resources/testdata/user.csv")
//@RunWith(SerenityParameterizedRunner.class)
public class UserDataDrivenTest extends TestBase {

    private String name;
    private String email;
    private String gender;
    private String status;

    @Steps
    UsersSteps usersSteps;

    @Title("user Data Driven Test for Multiple users")
    @Test
    public void createUsersWith(){
        usersSteps.createUsers(name,email,gender,status).statusCode(201);
    }
}
