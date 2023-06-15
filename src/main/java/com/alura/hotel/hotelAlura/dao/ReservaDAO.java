package com.alura.hotel.hotelAlura.dao;

import com.alura.hotel.hotelAlura.factory.ConnFactory;
import com.alura.hotel.hotelAlura.model.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

  private Connection connection;

  public ReservaDAO(Connection connection){
    this.connection = connection;
  }


  public void crear(Reserva reserva){

    try{
      connection.setAutoCommit(false);
      final PreparedStatement stmt = connection.prepareStatement(
          "INSERT INTO reserva  (fecha_entrada, fecha_salida, valor, forma_pago, tipoHabitacion) VALUES (?, ?, ?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS);
      try(stmt){
        guardar(reserva, stmt);
        connection.commit();
      }catch (SQLException e){
        e.getMessage();
        connection.rollback();
      }
    }catch (SQLException e){
      throw new RuntimeException(e);
    }
  }

  public void guardar(Reserva reserva, PreparedStatement stmt) throws SQLException {
    stmt.setDate(1, (Date) reserva.getFechaEntrada());
    stmt.setDate(2, (Date) reserva.getFechaSalida());
    stmt.setDouble(3, reserva.getValor());
    stmt.setString(4, reserva.getFormaPago());
    stmt.setString(5, reserva.getTipoHabitacion());

    stmt.execute();

    final ResultSet resultSet = stmt.getGeneratedKeys();

    try(resultSet){
      while(resultSet.next()){
        reserva.setId(resultSet.getInt(1));
        System.out.println("La reserva: " + reserva + " secreo correctamente");
      }
    }
  }

  public List<Reserva> listar(){
    List<Reserva> reservas = new ArrayList<>();

    try{
      final PreparedStatement stmt = connection.prepareStatement(
          "SELECT * FROM reserva"
      );

      try(stmt){
        stmt.execute();
        final ResultSet resultSet = stmt.getResultSet();
        while(resultSet.next()){
          Reserva fila = new Reserva(resultSet.getInt("id"), resultSet.getDate("fecha_entrada"),
              resultSet.getDate("fecha_salida"), resultSet.getDouble("valor"),
              resultSet.getString("forma_pago"), resultSet.getString("tipoHabitacion"));
          reservas.add(fila);
        }
      }
    }catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return reservas;
  }

  public List<Reserva> getById(int id) {
    List<Reserva> reserva = new ArrayList<>();

    try {
      final PreparedStatement stmt = connection.prepareStatement("SELECT id, fecha_entrada, fecha_salida, valor, forma_pago, tipoHabitacion FROM reserva WHERE id = ?");

      try (stmt) {
        stmt.setInt(1, id);
        stmt.execute();
        final ResultSet resultSet = stmt.getResultSet();
        while (resultSet.next()) {
          Reserva fila = new Reserva(resultSet.getInt("id"), resultSet.getDate("fecha_entrada"),
              resultSet.getDate("fecha_salida"), resultSet.getDouble("valor"),
              resultSet.getString("forma_pago"), resultSet.getString("tipoHabitacion"));

          reserva.add(fila);
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return reserva;
  }

  public int modificar(Integer id, Date fecha_entrada, Date fecha_salida, double valor, String forma_pago, String tipoHabitacion){
    try {

      final PreparedStatement stmt = connection.prepareStatement("UPDATE reserva SET " + "fecha_entrada = ?, "
          + "fecha_salida = ?, " + "valor = ?, " + "forma_pago = ?, tipoHabitacion = ? WHERE id = ?");

      try (stmt) {
        stmt.setDate(1, fecha_entrada);
        stmt.setDate(2, fecha_salida);
        stmt.setDouble(3, valor);
        stmt.setString(4, forma_pago);
        stmt.setString(5, tipoHabitacion);
        stmt.setInt(6, id);

        stmt.execute();

        int updateCount = stmt.getUpdateCount();

        return updateCount;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  public int eliminar(Integer id) {

    try {
      final PreparedStatement stmt = connection.prepareStatement("DELETE FROM reserva WHERE id = ? ;");
      try (stmt) {
        stmt.setInt(1, id);
        stmt.execute();
        return stmt.getUpdateCount();
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
