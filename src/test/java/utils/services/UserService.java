package utils.services;

import models.UserCreateRequest;
import models.UserCreateResponse;
import models.UserModel;

import static io.restassured.RestAssured.given;

public class UserService extends RestService {
    @Override
    protected String getBasePath() {
        return "/users";
    }

    private UserCreateResponse user;
    public UserCreateResponse createUser(UserCreateRequest req) {
        user = given()
                .spec(REQ_SPEC)
                .body(req)
                .when().post()
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getObject("data", UserCreateResponse.class);
        return user;
    }

    public UserModel getUser() {
        return given().given().get("/" + user.getId()).as(UserModel.class);
    }

    public UserModel getUser(int id) {
        return given().given().get("/" + id).as(UserModel.class);
    }
}
