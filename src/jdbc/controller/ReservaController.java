package jdbc.controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import jdbc.dao.ReservaDAO;
import jdbc.factory.ConexionBase;
import jdbc.modelo.Reserva;

public class ReservaController {

	private ReservaDAO reservaDAO;

	public ReservaController() {
		Connection con = new ConexionBase().conectarBase();
		this.reservaDAO = new ReservaDAO(con);
	}

	public void guardar(Reserva reserva) {
		this.reservaDAO.guardar(reserva);
	}

	public List<Reserva> mostrar() {
		return this.reservaDAO.mostrar();
	}

	public List<Reserva> buscar(String id) {
		return this.reservaDAO.buscarId(id);
	}

	public void actualizarReserva(LocalDate dateEntrada, LocalDate dateSalida, String valor, String formaPago,
			Integer id) {
		this.reservaDAO.Actualizar(dateEntrada, dateSalida, valor, formaPago, id);
	}

	public void Eliminar(Integer id) {
		this.reservaDAO.Eliminar(id);
	}
}
