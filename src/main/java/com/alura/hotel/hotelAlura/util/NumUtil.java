package com.alura.hotel.hotelAlura.util;

public class NumUtil {
  public boolean isNumeric(String cadena){
    try {
      Integer.parseInt(cadena);
      return true;
    } catch (NumberFormatException nfe){
      return false;
    }
  }
}
