package sqliteMaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsLibrosPrestados {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection miCon=Conexion.conectar("biblioteca2.db");
			PreparedStatement stmt= miCon.prepareStatement("SELECT titulo FROM prestamo LEFT JOIN libro ON prestamo.codlibro=libro.codigo");
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				System.out.println("Libro: "+results.getString("titulo"));
			}
		}catch (SQLException e){
			System.out.println("Error" + e); 
		}
	}

}
