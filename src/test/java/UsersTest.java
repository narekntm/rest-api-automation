import io.restassured.response.Response;
import models.UserCreateRequest;
import models.UserModel;
import org.testng.annotations.Test;
import utils.UserGenerator;
import utils.assertHelpers.HardAssertHelper;
import utils.assertHelpers.SoftAssertHelper;
import utils.services.UserService;

public class UsersTest {

    @Test
    void createUser() {
        UserService api = new UserService();

        UserCreateRequest userReq = (new UserGenerator()).generateUserRequest();
        Response userRes = api.createUser(userReq);
        UserModel userCreate = UserModel.getUserData(userRes);

        HardAssertHelper.notNull(userCreate, "");

        SoftAssertHelper softAssert = new SoftAssertHelper();
        softAssert.valueEquals(userCreate.getName(), userReq.getName(), "");
        softAssert.valueEquals(userCreate.getEmail(), userReq.getEmail(), "");
        softAssert.valueEquals(userCreate.getGender(), userReq.getGender(), "");
        softAssert.valueEquals(userCreate.getStatus(), userReq.getStatus(), "");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "createUser")
    void updateUser() {
        UserService api = new UserService();

        UserCreateRequest userReq = (new UserGenerator()).generateUserRequest();
        Response userRes = api.createUser(userReq);
        UserModel userCreate = UserModel.getUserData(userRes);

        UserCreateRequest userReqNew = (new UserGenerator()).generateUserRequest();
        Response resUpdate = api.updateUser(userReqNew, userCreate.getId());
        UserModel userUpdate = UserModel.getUserData(resUpdate);

        HardAssertHelper.notNull(userUpdate, "");
        HardAssertHelper.valueEquals(200, resUpdate.statusCode(), "");

        SoftAssertHelper softAssert = new SoftAssertHelper();
        softAssert.valueEquals(userUpdate.getName(), userReqNew.getName(), "");
        softAssert.valueEquals(userUpdate.getEmail(), userReqNew.getEmail(), "");
        softAssert.valueEquals(userUpdate.getGender(), userReqNew.getGender(), "");
        softAssert.valueEquals(userUpdate.getStatus(), userReqNew.getStatus(), "");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "createUser")
    void deleteUser() {
        UserService api = new UserService();

        UserCreateRequest userReq = (new UserGenerator()).generateUserRequest();
        Response userRes = api.createUser(userReq);
        UserModel userCreate = UserModel.getUserData(userRes);

        Response userDeleteRes = api.deleteUser(userCreate.getId());

        HardAssertHelper.notNull(userDeleteRes, "");
        HardAssertHelper.valueEquals(204, userDeleteRes.statusCode(), "");
    }
}
