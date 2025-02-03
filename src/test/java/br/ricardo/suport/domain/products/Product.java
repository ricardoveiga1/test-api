package br.ricardo.suport.domain.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private int id;
    private String title;
    private String description;
    private String category;
    private float price;

    public float discountPercentage;
    public float rating;
    public int stock;
    public ArrayList<String> tags;
    public String brand;
    public String sku;
    public int weight;
    public Dimensions dimensions;
    public String warrantyInformation;
    public String shippingInformation;
    public String availabilityStatus;
    public ArrayList<Review> reviews;
    public String returnPolicy;
    public int minimumOrderQuantity;
    public Meta meta;
    public ArrayList<String> images;
    public String thumbnail;
}
