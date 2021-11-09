package sqliteMaven;

import java.sql.*;

public class Conexion{
	
	private static String URL="jdbc:sqlite:C:/sqlite/";
	
	public static Connection conectar(String bd) {
		Connection conexion = null;
		URL = URL +bd;
		try {
			conexion = DriverManager.getConnection(URL);
			System.out.println("Conexión OK");
		}catch (SQLException e) {
			System.out.println("Error en la conexión" + e);
		}
		return conexion;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Connection miCon = conectar("chinook.db");
	}

}

