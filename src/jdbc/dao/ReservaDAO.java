package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jdbc.modelo.Reserva;

public class ReservaDAO {

	private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva reserva) {
		try {
			String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_de_pago)"
					+ "VALUES (?,?,?,?)";
			try (PreparedStatement pstm = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
				pstm.setObject(1, reserva.getDateEntrada());
				pstm.setObject(2, reserva.getDateSalida());
				pstm.setObject(3, reserva.getValor());
				pstm.setObject(4, reserva.getFormaPago());
				pstm.executeUpdate();

				try (ResultSet resultSet = pstm.getGeneratedKeys()) {
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> mostrar() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas";
			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.execute();

				transformarResultado(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<Reserva> buscarId(String id) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas WHERE id=?";
			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();

				transformarResultado(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void Actualizar(LocalDate dateEntrada, LocalDate dateSalida, String valor, String formaPago, Integer id) {
		try (PreparedStatement stm = con.prepareStatement(
				"UPDATE reservas SET fecha_entrada=?, fecha_salida=?, valor=?, forma_de_pago=? WHERE id=?")) {
			stm.setObject(1, java.sql.Date.valueOf(dateEntrada));
			stm.setObject(2, java.sql.Date.valueOf(dateSalida));
			stm.setObject(3, valor);
			stm.setObject(4, formaPago);
			stm.setInt(5, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void Eliminar(Integer id) {
		try {
			Statement state = con.createStatement();
			state.execute("SET FOREIGN_KEY_CHECKS=0");
			PreparedStatement stm = con.prepareStatement("DELETE FROM reservas WHERE id =?");
			stm.setInt(1, id);
			stm.execute();
			state.execute("SET FOREIGN_KEY_CHECKS=1");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void transformarResultado(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet resultSet = pstm.getResultSet()) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				LocalDate fechaEntrada = resultSet.getDate("fecha_entrada").toLocalDate().plusDays(0);
				LocalDate fechaSalida = resultSet.getDate("fecha_salida").toLocalDate().plusDays(0);
				String valor = resultSet.getString("valor");
				String formaPago = resultSet.getString("forma_de_pago");

				Reserva producto = new Reserva(id, fechaEntrada, fechaSalida, valor, formaPago);
				reservas.add(producto);
			}
		}
	}

}
