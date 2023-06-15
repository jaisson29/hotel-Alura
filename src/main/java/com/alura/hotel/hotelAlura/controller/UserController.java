package com.alura.hotel.hotelAlura.controller;

import com.alura.hotel.hotelAlura.dao.UserDAO;
import com.alura.hotel.hotelAlura.factory.ConnFactory;

public class UserController {
  private UserDAO userDAO;

  public UserController() {
    this.userDAO = new UserDAO(new ConnFactory().conectar());
  }

  public boolean validarUsuario(String nombreUsuario, String password) {
    return userDAO.existeUsuario(nombreUsuario, password);
  }
}
