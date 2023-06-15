package com.alura.hotel.hotelAlura.model;

import java.util.Date;

public class Reserva {
  private Integer id;
  private Date fechaEntrada;
  private Date fechaSalida;
  private Double valor;
  private String formaPago;
  private String tipoHabitacion;

  public Reserva() {
  }

  public Reserva(Date fechaEntrada, Date fechaSalida, double valor, String formaPago, String tipoHabitacion) {
    this.fechaEntrada = fechaEntrada;
    this.fechaSalida = fechaSalida;
    this.valor = valor;
    this.formaPago = formaPago;
    this.tipoHabitacion = tipoHabitacion;
  }

  public Reserva(int id, Date fechaEntrada, Date fechaSalida, double valor, String formaPago, String tipoHabitacion) {
    this.id = id;
    this.fechaEntrada = fechaEntrada;
    this.fechaSalida = fechaSalida;
    this.valor = valor;
    this.formaPago = formaPago;
    this.tipoHabitacion = tipoHabitacion;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getFechaEntrada() {
    return fechaEntrada;
  }

  public void setFechaEntrada(Date fechaEntrada) {
    this.fechaEntrada = fechaEntrada;
  }

  public Date getFechaSalida() {
    return fechaSalida;
  }

  public void setFechaSalida(Date fechaSalida) {
    this.fechaSalida = fechaSalida;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public String getFormaPago() {
    return formaPago;
  }

  public void setFormaPago(String formaPago) {
    this.formaPago = formaPago;
  }

  public String getTipoHabitacion() {
    return tipoHabitacion;
  }

  public void setTipoHabitacion(String tipoHabitacion) {
    this.tipoHabitacion = tipoHabitacion;
  }

  @Override
  public String toString() {
    return "{id:" + this.id + "," +
        "fecha de entrada:" + this.fechaEntrada + "," +
        "fecha de salida:" + this.fechaSalida + "," +
        "valor: " + this.valor + "}";
  }
}
