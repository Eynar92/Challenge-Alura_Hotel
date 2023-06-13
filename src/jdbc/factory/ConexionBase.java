package jdbc.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConexionBase {

	public DataSource dataSource;
	
	public ConexionBase() {
		ComboPooledDataSource comboPool = new ComboPooledDataSource();
		comboPool.setJdbcUrl("jdbc:mysql://localhost/hotel_alura_bo?useTimeZone=true&serverTimeZone=UTC");
		comboPool.setUser("root");
		comboPool.setPassword("1992");
		
		this.dataSource = comboPool;
	}
	
	public Connection conectarBase() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println("Hubo un error");
			throw new RuntimeException(e);
		}
	}
	
}
