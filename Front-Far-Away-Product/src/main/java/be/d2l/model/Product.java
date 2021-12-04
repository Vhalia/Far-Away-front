package be.d2l.model;

import lombok.Data;

@Data
public class Product {

    private  int id;
    private String name;
    private String shortDescription;
    private String detailedDescription;
    private Float price;
    private String category;

}
