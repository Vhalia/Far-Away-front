package be.d2l.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private int id;
    private String surname;
    private String firstName;
    private String nickname;
    private Date birthDate;
    private String address;
    private String mail;
    private String password;
}
