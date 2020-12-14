package com.cimpa.montagnecom.model;

public class Type {
  int id;
  String nom;
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getNom() {
    return nom;
  }
  public void setNom(String nom) {
    this.nom = nom;
  }
  public Type() {
    this(1, "unknown");
  }
  public Type(int id, String nom) {
    super();
    this.id = id;
    this.nom = nom;
  }
  
  public String toString() {
    return this.nom;
  }
}
