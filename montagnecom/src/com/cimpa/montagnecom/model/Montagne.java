package com.cimpa.montagnecom.model;

public class Montagne {
  private String nom, image, description;
  private int altitude;
  private double prix;
  
  private Chaine chaine;
  private Type type;
  
  public String getNom() {
    return nom;
  }
  public void setNom(String nom) {
    this.nom = nom;
  }
  public String getImage() {
    return image;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public void setImage(String image) {
    this.image = image;
  }
  public int getAltitude() {
    return altitude;
  }
  public void setAltitude(int altitude) {
    this.altitude = altitude;
  }
  public double getPrix() {
    return prix;
  }
  public void setPrix(double prix) {
    this.prix = prix;
  }
  public Chaine getChaine() {
    return chaine;
  }
  public void setChaine(Chaine chaine) {
    this.chaine = chaine;
  }
  public Type getType() {
    return type;
  }
  public void setType(Type type) {
    this.type = type;
  }

  
  public String toString() {
    return String.format("%s : %d m d'altuitude, %f euro", this.nom, this.altitude, this.prix);
  }
  
}
