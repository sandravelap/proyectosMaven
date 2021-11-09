package sqliteMaven;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBD {

	public static void main(String[] args) {
		
		String bd = "biblioteca2.db";
		File archivo = new File("C:/sqlite/biblioteca.db");
		if (archivo.delete()){
			System.out.println("Archivo borrado");
		}
		Connection miCon = Conexion.conectar(bd);
		String crearTablaLibro="CREATE TABLE IF NOT EXISTS LIBRO (CODIGO INT NOT NULL PRIMARY KEY, TITULO VARCHAR(15), "
				+ "AUTOR VARCHAR(20), EDITORIAL VARCHAR(15), AGNO INT, ISBN VARCHAR(20) NOT NULL UNIQUE,"
				+ "NUMEJEMPLARES INT, NUMPAGINAS INT)";
		String crearTablaSocio="CREATE TABLE IF NOT EXISTS SOCIO (COD INT NOT NULL PRIMARY KEY, NOMBRE VARCHAR(15), "
				+ "APELLIDOS VARCHAR(20), F_NAC DATE, DOMICILIO VARCHAR(30), TELEFONO VARCHAR(9))";
		String crearTablaPrestamo="CREATE TABLE IF NOT EXISTS PRESTAMO (FINI DATE, FFIN DATE, CODLIBRO INT NOT NULL, CODSOCIO INT NOT NULL, "
				+ "PRIMARY KEY (CODLIBRO, CODSOCIO), FOREIGN KEY (CODLIBRO) REFERENCES LIBRO (CODIGO), " 
				+ "FOREIGN KEY (CODSOCIO) REFERENCES SOCIO (COD))";
		try {
			Statement crearTabla = miCon.createStatement();
			crearTabla.execute(crearTablaLibro);
			crearTabla.execute(crearTablaSocio);
			crearTabla.execute(crearTablaPrestamo);
		} catch (SQLException e) {
			System.out.println("Error " + e);
		} 
	}

}

