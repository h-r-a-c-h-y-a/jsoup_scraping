package com.cerebrum.jsoupdemo.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.StringJoiner;


@Entity
@Table(name = "tv_sets")
public class TV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String model;

    @Type(type = "com.cerebrum.jsoupdemo.model.Image")
    private Image image;

    private String price;

    public TV(){}

    public TV(String model, Image image, String price) {
        this.model = model;
        this.image = image;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String description) {
        this.model = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", TV.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("model='" + model + "'")
                .add("image=" + image)
                .add("price='" + price + "'")
                .toString();
    }
}
