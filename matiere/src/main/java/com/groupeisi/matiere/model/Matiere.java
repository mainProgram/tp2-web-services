package com.groupeisi.matiere.model;

public class Matiere extends com.groupeisi.matiere.Matiere {
    private int id;
    private String libelle;
    private double coefficient;

    public Matiere() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}