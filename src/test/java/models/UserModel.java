package models;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;

    public static UserModel getUserData(Response response) {
        return response
                .jsonPath()
                .getObject("data", UserModel.class);
    }
}
