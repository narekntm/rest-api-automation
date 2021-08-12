import org.testng.annotations.DataProvider;
import utils.UserGenerator;

public class UsersTestCases {
    @DataProvider(name = "Users")
    public static Object[][] Users() {
        return new Object[][]{
                {(new UserGenerator()).generateValidUserRequest(), "valid"},
                {(new UserGenerator()).generateInvalidUserRequest("name"), "name"},
                {(new UserGenerator()).generateInvalidUserRequest("email"), "email"},
                {(new UserGenerator()).generateInvalidUserRequest("gender"), "gender"},
                {(new UserGenerator()).generateInvalidUserRequest("status"), "status"}
        };
    }
}
