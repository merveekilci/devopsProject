package com.sau.myapp2.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="person")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;
    @Column(name="img_url") // Assuming this is the column name for the image file name
    private String img_url; // Field to store the image file name

    // Getter and setter methods for the image file name
    public String getImageName() {
        return img_url;
    }

    public void setImageName(String img_url) {
        this.img_url = img_url;
    }

}
