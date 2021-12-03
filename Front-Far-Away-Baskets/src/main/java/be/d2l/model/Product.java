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

    public Product(){
    }

    public Product(int id, String name, String shortDescription, String detailedDescription, float price, String category) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.price = price;
        this.category = category;
    }
}
