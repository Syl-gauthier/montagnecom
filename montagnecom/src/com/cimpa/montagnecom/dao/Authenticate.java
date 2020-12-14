package com.cimpa.montagnecom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticate {
  private Connection conn;
  private static Authenticate auth;
  
  private Authenticate() {
    super();
    conn = Connecteur.instance().getConnection();
  }
  
  public static Authenticate instance() {
    if(Authenticate.auth == null) { Authenticate.auth = new Authenticate(); }
    return Authenticate.auth;
  }
  
  public boolean isAuth(String user, String password) {
    try {
      PreparedStatement prst = conn.prepareStatement("Select * from utilisateur where nom_utilisateur = ? and mot_de_passe = ?");
      prst.setString(1, user);
      prst.setString(2, password);
      ResultSet rs = prst.executeQuery();
      return rs.next();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
