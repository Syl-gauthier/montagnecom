package com.cimpa.montagnecom.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;

public class Connecteur {
  private static final String URL = "jdbc:mysql://localhost/";
  private static final String LOGIN = "root";
  private static final String PASSWORD = "";
  private static final String DBNAME = "projet";
  private static Connecteur connecteur;
  private Connection connection;

  public Connection getConnection() {
    return connection;
  }
  
  private Connecteur() {
    super();
    Connection c = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("Driver introuvable !!! ");
      e.printStackTrace();
    }
    try {
      c = DriverManager.getConnection(URL + DBNAME, LOGIN, PASSWORD);     
    } catch (SQLException e) {
      System.out.println("Connexion impossible :'(");
      e.printStackTrace();
    }
    connection = c;
    System.out.println("connexion établie =)");
  }
  
  public static Connecteur instance() {
    if(Connecteur.connecteur == null) Connecteur.connecteur = new Connecteur();
    return Connecteur.connecteur;
  };

}
