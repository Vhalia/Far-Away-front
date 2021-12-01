package be.d2l.model;

import lombok.Data;


@Data
public class Basket {

    private int id;
    private int idUser;
    private int idProduct;
    private int quantity;
    private Product product;

    public Basket() {

    }





}