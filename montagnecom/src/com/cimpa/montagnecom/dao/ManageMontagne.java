package com.cimpa.montagnecom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.cimpa.montagnecom.model.Chaine;
import com.cimpa.montagnecom.model.Montagne;
import com.cimpa.montagnecom.model.Type;

public class ManageMontagne {
  public static Connection conn;
  private static ManageMontagne mm;
  
  private ManageMontagne() {
    super();
    conn = Connecteur.instance().getConnection();
  }
  
  public static ManageMontagne instance() {
    if(ManageMontagne.mm == null) ManageMontagne.mm = new ManageMontagne();
    return ManageMontagne.mm;
  }
  
  public void addMontagne(Montagne montagne) {
    try {
      if (montagne.getChaine().getId() == 0) montagne.setChaine(insertChaine(montagne.getChaine().getNom()));
      if (montagne.getType().getId() == 0) montagne.setType(insertType(montagne.getType().getNom()));
      PreparedStatement prst = conn.prepareStatement(
          "Insert into montagne values (?,?,?,?,?,?,?)"
      );
      prst.setString(1, montagne.getNom());
      prst.setDouble(2, montagne.getPrix());
      prst.setString(3, montagne.getImage());
      prst.setInt(4, montagne.getAltitude());
      prst.setString(5, montagne.getDescription());
      prst.setInt(6, montagne.getChaine().getId());
      prst.setInt(7, montagne.getType().getId());
      prst.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public void updateMontagne(Montagne montagne) {
    if (montagne.getChaine().getId() == 0) montagne.setChaine(insertChaine(montagne.getChaine().getNom()));
    if (montagne.getType().getId() == 0) montagne.setType(insertType(montagne.getType().getNom()));
    PreparedStatement prst;
    try {
      prst = conn.prepareStatement(
          "update montagne set altitude = ?, prix = ?, image = ?, description = ?, id_chaine = ?, id_type = ? where nom_montagne = ?"
      );
      prst.setInt(1, montagne.getAltitude());
      prst.setDouble(2, montagne.getPrix());
      prst.setString(3, montagne.getImage());
      prst.setString(4, montagne.getDescription());
      prst.setInt(5, montagne.getChaine().getId());
      prst.setInt(6, montagne.getType().getId());
      prst.setString(7, montagne.getNom());
      prst.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }    
  }
  
  public void deleteMontagne(String nom) {
    try {
      PreparedStatement prst = conn.prepareStatement(
          "delete from montagne where nom_montagne = ?"
      );
      prst.setString(1, nom);
      prst.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public Montagne selectMontagne(String nom) {
    Montagne montagne = null;
    try {
      PreparedStatement prst = conn.prepareStatement(
          "Select * from montagne "
          + "inner join chaine on chaine.id_chaine = montagne.id_chaine "
          + "inner join type on type.id_type = montagne.id_type "
          + "where nom_montagne = ?" 
      );
      prst.setString(1, nom);
      ResultSet rs = prst.executeQuery();
      if (rs != null && rs.next()) {
        montagne = new Montagne();
        montagne.setAltitude(rs.getInt("altitude"));
        montagne.setImage(rs.getString("image"));
        montagne.setNom(rs.getString("nom_montagne"));
        montagne.setPrix(rs.getDouble("prix"));
        montagne.setDescription(rs.getString("description"));
        montagne.setChaine(new Chaine(rs.getInt("id_chaine"), rs.getString("nom_chaine")));
        montagne.setType(new Type(rs.getInt("id_type"), rs.getString("nom_type")));
        return montagne;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return montagne;
  }
  
  public List<Montagne> selectMontagneList(int limit, int offset) {
    List<Montagne> list = new LinkedList<Montagne> ();
    try {
      PreparedStatement prst = conn.prepareStatement(
          "select * from montagne "
          + "join chaine on chaine.id_chaine = montagne.id_chaine "
          + "join type on type.id_type = montagne.id_type limit ?, ?"
      );
      prst.setInt(1, offset);
      prst.setInt(2, limit);
      ResultSet rs = prst.executeQuery();
      if(rs != null) {
        while(rs.next()) {
          Montagne m = new Montagne();
          m.setNom(rs.getString("nom_montagne"));
          m.setAltitude(rs.getInt("altitude"));
          m.setDescription(rs.getString("Description"));
          m.setPrix(rs.getDouble("prix"));
          m.setImage(rs.getString("image"));
          m.setChaine(new Chaine(rs.getInt("id_chaine"), rs.getString("nom_chaine")));
          m.setType(new Type(rs.getInt("id_type"), rs.getString("nom_type")));
          list.add(m);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public List<Type> selectTypeList() {
    List<Type> list = new LinkedList<Type> ();
    try {
      PreparedStatement prst = conn.prepareStatement(
          "select * from type order by nom_type desc"
      );
      ResultSet rs = prst.executeQuery();
      if(rs != null) {
        while(rs.next()) {
          Type t = new Type(rs.getInt("id_type"), rs.getString("nom_type"));
          list.add(t);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public List<Chaine> selectChaineList() {
    List<Chaine> list = new LinkedList<Chaine> ();
    try {
      PreparedStatement prst = conn.prepareStatement(
          "select * from chaine order by nom_chaine desc"
      );
      ResultSet rs = prst.executeQuery();
      if(rs != null) {
        while(rs.next()) {
          Chaine c = new Chaine(rs.getInt("id_chaine"), rs.getString("nom_chaine"));
          list.add(c);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  private Chaine insertChaine(String nom) {
    try {
      PreparedStatement prst_exists = conn.prepareStatement("select * from chaine where nom_chaine = ?");
      prst_exists.setString(1, nom);
      ResultSet rs_exists = prst_exists.executeQuery();
      if(rs_exists != null && rs_exists.next()) {
        return new Chaine(rs_exists.getInt("id_chaine"), rs_exists.getString("nom_chaine"));
      } else {
        PreparedStatement prst = conn.prepareStatement("insert into chaine(nom_chaine) values (?)", Statement.RETURN_GENERATED_KEYS);
        prst.setString(1, nom);
        prst.executeUpdate();
        ResultSet rs = prst.getGeneratedKeys();
        if(rs != null && rs.next()) {
          int id = rs.getInt(1);
          return new Chaine(id, nom);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new Chaine();
  }
  
  private Type insertType(String nom) {
    try {
      PreparedStatement prst_exists = conn.prepareStatement("select * from type where nom_type = ?");
      prst_exists.setString(1, nom);
      ResultSet rs_exists = prst_exists.executeQuery();
      if(rs_exists != null && rs_exists.next()) {
        return new Type(rs_exists.getInt("id_type"), rs_exists.getString("nom_type"));
      } else {
        PreparedStatement prst = conn.prepareStatement("insert into type(nom_type) values (?)", Statement.RETURN_GENERATED_KEYS);
        prst.setString(1, nom);
        prst.executeUpdate();
        ResultSet rs = prst.getGeneratedKeys();
        if(rs != null && rs.next()) {
          int id = rs.getInt(1);
          return new Type(id, nom);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new Type();
  }

}
