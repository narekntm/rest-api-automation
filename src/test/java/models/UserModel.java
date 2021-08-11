package models;

import lombok.Data;

@Data
public class UserModel {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;
}
