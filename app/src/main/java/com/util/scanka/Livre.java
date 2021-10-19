package com.util.scanka;

import java.io.Serializable;

public class Livre implements Serializable {

    private int id;
    private String title;
    private int id_image;
    private String description;

    public Livre(int id, String title, int id_image, String description) {
        this.id = id;
        this.title = title;
        this.id_image = id_image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId_image() {
        return id_image;
    }

    public void setId_image(int id_image) {
        this.id_image = id_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
