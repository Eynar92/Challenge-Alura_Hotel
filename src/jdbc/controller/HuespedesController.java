package jdbc.controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import jdbc.dao.HuespedesDAO;
import jdbc.factory.ConexionBase;
import jdbc.modelo.Huespedes;

public class HuespedesController {

	private HuespedesDAO huespedesDAO;

	public HuespedesController() {
		Connection con = new ConexionBase().conectarBase();
		this.huespedesDAO = new HuespedesDAO(con);
	}

	public void guardar(Huespedes huespedes) {
		this.huespedesDAO.guardar(huespedes);
	}

	public List<Huespedes> mostrarHuespedes() {
		return this.huespedesDAO.mostrar();
	}

	public List<Huespedes> buscarHuesped(String id) {
		return this.huespedesDAO.buscarId(id);
	}

	public void actualizarHuesped(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad,
			String telefono, Integer idReserva, Integer id) {
		this.huespedesDAO.ActualizarHuesped(nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva, id);
	}

	public void Eliminar(Integer idReserva) {
		this.huespedesDAO.Eliminar(idReserva);
	}

}
