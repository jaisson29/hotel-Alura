package com.alura.hotel.hotelAlura.views;

import com.alura.hotel.hotelAlura.controller.HuespedController;
import com.alura.hotel.hotelAlura.controller.ReservaController;
import com.alura.hotel.hotelAlura.util.DateUtil;
import com.alura.hotel.hotelAlura.util.NumUtil;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservaController reservaController;
	private HuespedController huespedController;
	private Integer tbSleccionada = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {

		this.huespedController = new HuespedController();
		this.reservaController = new ReservaController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		modelo.addColumn("Tipo de Habitación");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")),
				scroll_table, null);
		scroll_table.setVisible(true);
		cargarTablaReservas();
		tbReservas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tbSleccionada = 1;
				System.out.println(tbSleccionada);
			}
		});

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		cargarTablaHuespedes();
		tbHuespedes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tbSleccionada = 2;
				System.out.println(tbSleccionada);
			}
		});

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
				// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarTabla();
				NumUtil validarNumero = new NumUtil();

				if (validarNumero.isNumeric(txtBuscar.getText())) {
					buscarReserva(Integer.valueOf(txtBuscar.getText()));
					buscarHuesped(Integer.valueOf(txtBuscar.getText()));
				} else {
					buscarPorApellido(txtBuscar.getText());
				}
			}

		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnClear = new JPanel();
		btnClear.setLayout(null);
		btnClear.setBackground(new Color(12, 138, 199));
		btnClear.setBounds(450, 508, 170, 35);
		btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnClear);

		JLabel lblClear = new JLabel("LIMPIAR BUSQUEDA");
		lblClear.setHorizontalAlignment(SwingConstants.CENTER);
		lblClear.setForeground(Color.WHITE);
		lblClear.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblClear.setBounds(0, 0, 170, 35);
		btnClear.add(lblClear);
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtBuscar.setText("");
				limpiarTabla();
				cargarTablaReservas();
				cargarTablaHuespedes();
			}
		});

		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificar();
			}
		});

		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminar();
			}
		});
		setResizable(false);
	}

	private boolean tieneFilaElegida(JTable tabla) {
		return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
	}

	private void cargarTablaHuespedes() {
		var huespedes = this.huespedController.listar();

		huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(),
				huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
				huesped.getReservaId() }));

	}

	private void cargarTablaReservas() {
		var reservas = this.reservaController.listar();

		reservas.forEach(reserva -> modelo.addRow(new Object[] { reserva.getId(), reserva.getFechaEntrada(),
				reserva.getFechaSalida(), reserva.getValor(), reserva.getFormaPago(), reserva.getTipoHabitacion() }));
	}

	private void modificar() {
		DateUtil conversor = new DateUtil();

		if (tbSleccionada == 1) {
			if (tieneFilaElegida(tbReservas)) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
				return;
			}

			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {

						if (modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString().equals("")
								|| modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString().equals("")
								|| modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString().equals("")
								|| modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString().equals("")
								|| modelo.getValueAt(tbReservas.getSelectedRow(), 4).equals("")
								|| modelo.getValueAt(tbReservas.getSelectedRow(), 5).equals("")) {

							JOptionPane.showMessageDialog(this,
									"Por favor, ningun campo puede quedar vacio o sin ninguna información");
							return;

						} else {

							Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
							Date fechaEntrada = conversor
									.stringDateToSqlDate(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
							Date fechaSalida = conversor
									.stringDateToSqlDate(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
							double valor = Double
									.parseDouble(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
							String formaPago = String.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 4));
							String tipoHabitacion = String.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 5));

							var filaModificada = this.reservaController.modificar(id, fechaEntrada, fechaSalida, valor,
									formaPago, tipoHabitacion);

							JOptionPane.showMessageDialog(this,
									String.format("%d item modificado con éxito!", filaModificada));
						}

					}, () -> JOptionPane.showMessageDialog(this, "Por favor, seleccione un item"));

		} else if (tbSleccionada == 2) {
			if (tieneFilaElegida(tbHuespedes)) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
				return;
			}

			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(filaHuesped -> {

						if (modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString().equals("")
								|| modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).equals("")
								|| modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).equals("")
								|| modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString().equals("")
								|| modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).equals("")
								|| modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).equals("")
								|| modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString().equals("")) {
							JOptionPane.showMessageDialog(this,
									"Por favor, ningun campo puede quedar vacio o sin ninguna información");
							return;

						} else {
							Integer id = Integer
									.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
							String nombre = String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1));
							String apellido = String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2));
							Date fechaNacimiento = conversor.stringDateToSqlDate(
									modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
							String nacionalidad = String
									.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4));
							String telefono = String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5));
							Integer idReserva = Integer
									.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
							var filaHuespedModificada = this.huespedController.modificar(id, nombre, apellido,
									fechaNacimiento, nacionalidad, telefono, idReserva);
							JOptionPane.showMessageDialog(this,
									String.format("%d item modificado con éxito!", filaHuespedModificada));
						}
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, seleccione un item"));
		}

	}

	private void eliminar() {

		if (tbSleccionada == 1) {
			if (tieneFilaElegida(tbReservas)) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
				return;
			}

			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						var reservaEliminada = this.reservaController.eliminar(id);
						modelo.removeRow(tbReservas.getSelectedRow());
						JOptionPane.showMessageDialog(this, reservaEliminada + " Item eliminado con éxito!");
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));

		} else if (tbSleccionada == 2) {
			if (tieneFilaElegida(tbHuespedes)) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
				return;
			}

			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(filaHuesped -> {
						Integer id = Integer
								.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
						var huespedEliminado = this.huespedController.eliminar(id);
						modeloHuesped.removeRow(tbHuespedes.getSelectedRow());
						JOptionPane.showMessageDialog(this, huespedEliminado + " Item eliminado con éxito!");
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		}

	}

	private void limpiarTabla() {
		modelo.getDataVector().clear();
		modeloHuesped.getDataVector().clear();
	}

	private void buscarReserva(Integer id) {
		var reservaBuscada = this.reservaController.listar(id);

		reservaBuscada.forEach(reserva -> modelo.addRow(new Object[] { reserva.getId(), reserva.getFechaEntrada(),
				reserva.getFechaSalida(), reserva.getValor(), reserva.getFormaPago(), reserva.getTipoHabitacion() }));
	}

	private void buscarHuesped(Integer id) {
		var huespedBuscado = this.huespedController.listar(id);

		huespedBuscado.forEach(huesped -> modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(),
				huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
				huesped.getReservaId() }));
	}

	private void buscarPorApellido(String apellido) {
		var huespedApellido = this.huespedController.listar(apellido);

		huespedApellido.forEach(huesped -> modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(),
				huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
				huesped.getReservaId() }));

		buscarReserva(huespedApellido.get(0).getReservaId());

	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
