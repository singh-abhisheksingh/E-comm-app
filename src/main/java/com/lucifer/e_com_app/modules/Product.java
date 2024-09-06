package com.lucifer.e_com_app.modules;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private String category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date launchDate;
    private boolean availability;

    private String imageName;
    private String imageType;
    @Lob //Large object type of byte array to store image
    private byte[] imageData;
    // alternatively upload image on AWS and store the link here -> preferred way
}
