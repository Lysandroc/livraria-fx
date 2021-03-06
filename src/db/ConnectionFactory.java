package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static void main(String[] args) {
		try {
			Connection conn = new ConnectionFactory().getConnection();
			System.out.println("Conexao aberta, e agora?");
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost/livraria";
		try {
			return DriverManager.getConnection(url, "root", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
