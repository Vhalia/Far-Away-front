package be.d2l.model;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private int id;
    private String text;
    private int rating;
    private Date creationDate;
    private Boolean isDeleted;
    private int idUser;
    private int idProduct;
}
