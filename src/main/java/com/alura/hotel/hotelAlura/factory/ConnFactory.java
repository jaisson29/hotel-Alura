package com.alura.hotel.hotelAlura.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnFactory {
  private DataSource datasourse;

  public ConnFactory() {

    var pooledDataSource = new ComboPooledDataSource();
    pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC");
    pooledDataSource.setUser("root");
    pooledDataSource.setPassword("029Jajaisson#5");
    pooledDataSource.setMaxPoolSize(20);

    this.datasourse = pooledDataSource;
  }

  public Connection conectar() {
    try {
      return this.datasourse.getConnection();
    }catch(SQLException e){
      e.getMessage();
      throw new RuntimeException(e);
    }
  }
}
