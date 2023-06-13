package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jdbc.modelo.Huespedes;
import jdbc.modelo.Reserva;

public class HuespedesDAO {

	private Connection con;

	public HuespedesDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huespedes huespedes) {
		try {
			String sql = "INSERT INTO huespedes (nombre, apellido, fecha_de_nacimiento, nacionalidad,"
					+ "telefono, id_reserva) VALUES (?,?,?,?,?,?)";

			try (PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, huespedes.getNombre());
				pstm.setString(2, huespedes.getApellido());
				pstm.setObject(3, huespedes.getFechaNacimiento());
				pstm.setString(4, huespedes.getNacionalidad());
				pstm.setString(5, huespedes.getTelefono());
				pstm.setInt(6, huespedes.getIdReserva());
				pstm.execute();
				try (ResultSet resultSet = pstm.getGeneratedKeys()) {
					while (resultSet.next()) {
						huespedes.setId(resultSet.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huespedes> mostrar() {
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			String sql = "SELECT id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes";

			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.execute();

				transformarResultado(huespedes, pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<Huespedes> buscarId(String id) {
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			String sql = "SELECT id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes WHERE id=?";

			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();

				transformarResultado(huespedes, pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void ActualizarHuesped(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad,
			String telefono, Integer id_Reserva, Integer id) {
		try (PreparedStatement stm = con.prepareStatement(
				"UPDATE huespedes SET nombre=?, apellido=?, fecha_de_nacimiento=?, nacionalidad=?, telefono=?, id_reserva=? WHERE id=?")) {
			stm.setString(1, nombre);
			stm.setString(2, apellido);
			stm.setObject(3, fechaNacimiento);
			stm.setString(4, nacionalidad);
			stm.setString(5, telefono);
			stm.setInt(6, id_Reserva);
			stm.setInt(7, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void Eliminar(Integer id) {
		try(PreparedStatement stm = con.prepareStatement("DELETE FROM huespedes WHERE id=?")){
			stm.setInt(1, id);
			stm.execute();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void transformarResultado(List<Huespedes> huespedes, PreparedStatement pstm) throws SQLException {
		try (ResultSet resultSet = pstm.executeQuery()) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");
				LocalDate fechaNacimiento = resultSet.getDate("fecha_de_nacimiento").toLocalDate().plusDays(0);
				String nacionalidad = resultSet.getString("nacionalidad");
				String telefono = resultSet.getString("telefono");
				int idReserva = resultSet.getInt("id_reserva");

				Huespedes huesped = new Huespedes(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono,
						idReserva);
				huespedes.add(huesped);
			}
		}
	}
}
