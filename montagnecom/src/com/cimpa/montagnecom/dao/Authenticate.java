package com.cimpa.montagnecom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticate {
  public static Connection conn;
  
  public Authenticate() {
    super();
    conn = Connecteur.instance().getConnection();
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
