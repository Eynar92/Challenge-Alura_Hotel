package jdbc.factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {

	public static void main(String[] args) throws SQLException {
		ConexionBase con = new ConexionBase();
		Connection cone = con.conectarBase();
		
		System.out.println("Conexión correcta");
		cone.close();
		
		System.out.println("Conexión cerrada correctamente");
	}
	
}
