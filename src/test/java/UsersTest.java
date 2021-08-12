import io.restassured.response.Response;
import models.UserCreateRequest;
import models.UserCreateResponseInvalid;
import models.UserModel;
import org.testng.annotations.Test;
import utils.UserGenerator;
import utils.assertHelpers.HardAssertHelper;
import utils.assertHelpers.SoftAssertHelper;
import utils.services.UserService;

public class UsersTest {

    @Test(dataProvider = "Users", dataProviderClass = UsersTestCases.class)
    void createUser(UserCreateRequest userReq, String testCase) {

        UserService api = new UserService();

        Response userRes = api.createUser(userReq);
        SoftAssertHelper softAssert = new SoftAssertHelper();

        if (!testCase.equals("valid")) {
            UserCreateResponseInvalid userInvalidData = UserCreateResponseInvalid.getInvalidResponseData(userRes);

            HardAssertHelper.valueEquals(userRes.statusCode(), 422, "");
            softAssert.valueEquals(userInvalidData.getField(), testCase, "");
            softAssert.valueEquals(userInvalidData.getMessage(), UserCreateResponseInvalid.IS_BLANK, "");
            softAssert.assertAll();
            return;
        }

        UserModel userCreate = UserModel.getUserData(userRes);

        HardAssertHelper.notNull(userCreate, "");

        softAssert.valueEquals(userCreate.getName(), userReq.getName(), "");
        softAssert.valueEquals(userCreate.getEmail(), userReq.getEmail(), "");
        softAssert.valueEquals(userCreate.getGender(), userReq.getGender(), "");
        softAssert.valueEquals(userCreate.getStatus(), userReq.getStatus(), "");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "createUser", dataProvider = "Users", dataProviderClass = UsersTestCases.class)
    void updateUser(UserCreateRequest userReq, String testCase) {
        UserService api = new UserService();
        SoftAssertHelper softAssert = new SoftAssertHelper();

        Response userRes = api.createUser(userReq);

        if (!testCase.equals("valid")) {
            UserCreateResponseInvalid userInvalidData = UserCreateResponseInvalid.getInvalidResponseData(userRes);

            HardAssertHelper.valueEquals(userRes.statusCode(), 422, "");
            softAssert.valueEquals(userInvalidData.getField(), testCase, "");
            softAssert.valueEquals(userInvalidData.getMessage(), UserCreateResponseInvalid.IS_BLANK, "");
            softAssert.assertAll();
            return;
        }

        UserModel userCreate = UserModel.getUserData(userRes);

        UserCreateRequest userReqNew = (new UserGenerator()).generateValidUserRequest();
        Response resUpdate = api.updateUser(userReqNew, userCreate.getId());
        UserModel userUpdate = UserModel.getUserData(resUpdate);

        HardAssertHelper.notNull(userUpdate, "");
        HardAssertHelper.valueEquals(resUpdate.statusCode(), 200, "");

        softAssert.valueEquals(userUpdate.getName(), userReqNew.getName(), "");
        softAssert.valueEquals(userUpdate.getEmail(), userReqNew.getEmail(), "");
        softAssert.valueEquals(userUpdate.getGender(), userReqNew.getGender(), "");
        softAssert.valueEquals(userUpdate.getStatus(), userReqNew.getStatus(), "");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "createUser")
    void deleteUser() {
        UserService api = new UserService();

        UserCreateRequest userReq = (new UserGenerator()).generateValidUserRequest();
        Response userRes = api.createUser(userReq);
        UserModel userCreate = UserModel.getUserData(userRes);

        Response userDeleteRes = api.deleteUser(userCreate.getId());

        HardAssertHelper.notNull(userDeleteRes, "");
        HardAssertHelper.valueEquals(userDeleteRes.statusCode(), 204, "");
    }
}
