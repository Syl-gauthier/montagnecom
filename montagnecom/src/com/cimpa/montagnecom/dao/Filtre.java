package com.cimpa.montagnecom.dao;

public class Filtre {
  private String recherche;
  int idChaine, idType;

  public int getIdChaine() {
    return idChaine;
  }

  public void setIdChaine(int idChaine) {
    this.idChaine = idChaine;
  }

  public int getIdType() {
    return idType;
  }

  public void setIdType(int idType) {
    this.idType = idType;
  }

  public String getRecherche() {
    return recherche;
  }

  public void setRecherche(String recherche) {
    this.recherche = recherche;
  }


}
