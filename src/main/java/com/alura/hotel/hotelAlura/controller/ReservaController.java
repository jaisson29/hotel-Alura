package com.alura.hotel.hotelAlura.controller;


import com.alura.hotel.hotelAlura.dao.ReservaDAO;
import com.alura.hotel.hotelAlura.factory.ConnFactory;
import com.alura.hotel.hotelAlura.model.Reserva;

import java.util.Date;
import java.util.List;

public class ReservaController {

  private ReservaDAO reservaDAO;

  public ReservaController() {
    this.reservaDAO = new ReservaDAO(new ConnFactory().conectar());
  }

  public void crear(Reserva reserva) {
    reservaDAO.crear(reserva);

  }

  public List<Reserva> listar() {
    return reservaDAO.listar();
  }

  public List<Reserva> listar(int id) {
    return reservaDAO.getById(id);
  }

  public int modificar(Integer id, Date fecha_entrada, Date fecha_salida, double valor, String forma_pago, String tipoHabitacion) {
    return reservaDAO.modificar(id, (java.sql.Date) fecha_entrada, (java.sql.Date) fecha_salida, valor, forma_pago, tipoHabitacion);
  }

  public int eliminar(Integer id) {
    return reservaDAO.eliminar(id);
  }
}
