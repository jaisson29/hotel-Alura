package com.alura.hotel.hotelAlura.controller;

import com.alura.hotel.hotelAlura.dao.HuespedDAO;
import com.alura.hotel.hotelAlura.factory.ConnFactory;
import com.alura.hotel.hotelAlura.model.Huesped;

import java.util.Date;
import java.util.List;

public class HuespedController {
  private HuespedDAO huespedDAO;

  public HuespedController() {
    this.huespedDAO = new HuespedDAO(new ConnFactory().conectar());
  }

  public void crear(Huesped huesped, Integer reservaId) {
    huesped.setId(reservaId);
    huespedDAO.crear(huesped);

  }

  public Integer getReservaActual() {
    return huespedDAO.idReservaActual();
  }

  public List<Huesped> listar() {
    return huespedDAO.listar();
  }

  public List<Huesped> listar(Integer id) {
    return huespedDAO.listar(id);
  }

  public List<Huesped> listar(String apellido) {
    return huespedDAO.listar(apellido);
  }

  public int modificar(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
                       String telefono, int reservaId) {
    return huespedDAO.modificar(id, nombre, apellido, (java.sql.Date) fechaNacimiento, nacionalidad, telefono, reservaId);
  }

  public int eliminar(Integer id) {
    return huespedDAO.eliminar(id);

  }
}
