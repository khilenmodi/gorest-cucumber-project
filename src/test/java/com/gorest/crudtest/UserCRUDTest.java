package com.gorest.crudtest;

import com.gorest.studentinfo.UsersSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

//@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {


    static String name = "kamlesh" + TestUtils.getRandomValue();
    static String email = "kamleshpatel123@gmail.com" + TestUtils.getRandomValue();
    static String gender = "male" + TestUtils.getRandomValue();
    static String status = "Active" + TestUtils.getRandomValue();

    static int userId;

    @Steps
    UsersSteps usersSteps;

    @Title("This is will create new User")
    @Test
    public void test001(){
        usersSteps.createUsers(name,email,gender,status).statusCode(201);
        //ValidatableResponse response=
        //userId=response.extract().path("usersID");
    }

    @Title ("verify if the user was added to the list")
    @Test
    public void test002(){
        HashMap<String, Object> usersMap = usersSteps.getUsersInfoByName(name);
        Assert.assertThat(usersMap,hasValue(name));
        userId = (int) usersMap.get("id");
    }

    @Title ("update the user information and varify the updated information")
    @Test
    public void test003(){
        name = name + "_updated";
        usersSteps.updateUsers(name, email, status,userId).statusCode(200);
        HashMap<String, Object> usersMap = usersSteps.getUsersInfoByName(name);
        Assert.assertThat(usersMap,hasValue(name));
    }
    @Title("Delete the user and verify if the user is deleted!")
    @Test
    public void test004(){
        usersSteps.deleteUsers(userId).statusCode(200);
        usersSteps.getUserById(userId).statusCode(404);

    }

}
