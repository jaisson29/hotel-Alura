package com.alura.hotel.hotelAlura;

import com.alura.hotel.hotelAlura.views.MenuPrincipal;

import java.awt.*;

public class HotelAluraMain {
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          MenuPrincipal frame = new MenuPrincipal();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}
