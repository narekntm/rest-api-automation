package models;

import io.restassured.response.Response;
import lombok.Data;

@Data
public class UserCreateResponseInvalid {
    private String field;
    private String message;
    public static final String IS_BLANK = "can't be blank";

    public static UserCreateResponseInvalid getInvalidResponseData(Response response) {
        return response
                .jsonPath()
                .getObject("data[0]", UserCreateResponseInvalid.class);
    }
}
