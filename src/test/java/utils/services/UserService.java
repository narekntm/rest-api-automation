package utils.services;

import io.restassured.response.Response;
import models.UserCreateRequest;
import models.UserModel;

import static io.restassured.RestAssured.given;

public class UserService extends RestService {
    @Override
    protected String getBasePath() {
        return "/users";
    }

    public Response createUser(UserCreateRequest req) {
        return given()
                .spec(REQ_SPEC)
                .body(req)
                .when().post()
                .then()
                .extract().response();
    }

    public UserModel getUser(int id) {
        return given().given().get("/" + id).as(UserModel.class);
    }

    public Response updateUser(UserCreateRequest req, int id) {
        return given()
                .spec(REQ_SPEC)
                .body(req)
                .when().put("/" + id)
                .then()
                .extract().response();
    }

    public Response deleteUser(int id) {
        return given()
                .spec(REQ_SPEC)
                .when().delete("/" + id)
                .then()
                .extract().response();
    }
}
