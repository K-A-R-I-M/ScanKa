package com.util.scanka;

import java.io.Serializable;

public class Chapitre implements Serializable {
    private int id;
    private String name;
    private int nb;
    private int nb_pages;
    private int nb_pages_dl;
    private boolean dl_status;
    private int id_livre;

    public Chapitre(int id, String name, int nb, int nb_pages, int nb_pages_dl, boolean dl_status, int id_livre) {
        this.id = id;
        this.name = name;
        this.nb = nb;
        this.nb_pages = nb_pages;
        this.nb_pages_dl = nb_pages_dl;
        this.dl_status = dl_status;
        this.id_livre = id_livre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public int getNb_pages() {
        return nb_pages;
    }

    public void setNb_pages(int nb_pages) {
        this.nb_pages = nb_pages;
    }

    public int getNb_pages_dl() {
        return nb_pages_dl;
    }

    public void setNb_pages_dl(int nb_pages_dl) {
        this.nb_pages_dl = nb_pages_dl;
    }

    public boolean isDl_status() {
        return dl_status;
    }

    public void setDl_status(boolean dl_status) {
        this.dl_status = dl_status;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }
}
