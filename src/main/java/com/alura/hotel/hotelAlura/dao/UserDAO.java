package com.alura.hotel.hotelAlura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

  Connection con;

  public UserDAO(Connection con) {
    this.con = con;
  }

  public boolean existeUsuario(String nombre, String password) {

    try {
      final PreparedStatement statement = con
          .prepareStatement("SELECT nombre, password FROM user WHERE nombre = ? AND password = ?");
      try (statement) {
        statement.setString(1, nombre);
        statement.setString(2, password);
        statement.execute();
        final ResultSet resultSet = statement.getResultSet();
        if(resultSet.next()) {
          return true;
        }else {
          return false;
        }

      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
