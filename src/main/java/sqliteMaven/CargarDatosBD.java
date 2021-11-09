package sqliteMaven;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Octet;
import org.javatuples.Quartet;
import org.javatuples.Sextet;

public class CargarDatosBD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String bd = "biblioteca2.db";
		
		//variables a insertar en la base de datos
		Date fecha = new Date(946684800000L);
		Date fechaIniP1 = new Date(1633132800000L);
		Date fechaIniP2 = new Date(fechaIniP1.getTime() + (1000*60*60*24*2)); //2 días más tarde
		Date fechaFinP1 = new Date(fechaIniP1.getTime() + (1000*60*60*24*15)); //15 días más tarde
		Date fechaFinP2 = new Date(fechaIniP2.getTime() + (1000*60*60*24*15)); //15 días más tarde
		Octet <Integer, String, String, String, Integer, String, Integer, Integer> libro1 = Octet.with(1, "t1", "a1", null, null, "ISBN1", null, null);
		Octet <Integer, String, String, String, Integer, String, Integer, Integer> libro2 = Octet.with(2, "t2", "a2", null, null, "ISBN2", null, null);
		Sextet <Integer, String, String, Date, String, String> socio1 = Sextet.with(1, "socio1", "ape1", fecha, null, null);
		Sextet <Integer, String, String, Date, String, String> socio2 = Sextet.with(2, "socio2", "ape2", fecha, null, null);
		Quartet <Integer, Integer, Date, Date> prestamo1 = Quartet.with(libro1.getValue0(), socio1.getValue0(), fechaIniP1, fechaFinP1);
		Quartet <Integer, Integer, Date, Date> prestamo2 = Quartet.with(libro2.getValue0(), socio2.getValue0(), fechaIniP2, fechaFinP2);
		ArrayList<Octet> libros = new ArrayList<Octet>();
		libros.add(libro1);
		libros.add(libro2);
		ArrayList<Sextet> socios = new ArrayList<Sextet>();
		socios.add(socio1);
		socios.add(socio2);
		ArrayList<Quartet> prestamos = new ArrayList<Quartet>();
		prestamos.add(prestamo1);
		prestamos.add(prestamo2);
		//Conexión a la base de datos y carga
		Connection miCon = Conexion.conectar(bd);
		
		// insertar libros:
		try {
			for (Octet libro:libros) {
				PreparedStatement insertar= miCon.prepareStatement("INSERT INTO `LIBRO` (`CODIGO`, `TITULO`, `AUTOR` , `ISBN`) VALUES (?, ?, ?, ?) ");
				insertar.setInt(1, (Integer) libro.getValue(0));
				insertar.setString(2, (String) libro.getValue(1));
				insertar.setString(3, (String) libro.getValue(2));
				insertar.setString(4, (String) libro.getValue(5));
				insertar.executeUpdate();
			}
			for (Sextet socio:socios) {
				PreparedStatement insertar= miCon.prepareStatement("INSERT INTO `SOCIO` (`COD`, `NOMBRE`, `APELLIDOS`, `F_NAC`) VALUES (?, ?, ?, ?) ");
				insertar.setInt(1, (Integer) socio.getValue(0));
				insertar.setString(2, (String) socio.getValue(1));
				insertar.setString(3, (String) socio.getValue(2));
				insertar.setDate(4, (Date) socio.getValue(3));
				insertar.executeUpdate();
			}
			for (Quartet prestamo:prestamos) {
				PreparedStatement insertar= miCon.prepareStatement("INSERT INTO `PRESTAMO` (`CODLIBRO`, `CODSOCIO`, `FINI`, `FFIN`) VALUES (?, ?, ?, ?) ");
				insertar.setInt(1, (Integer) prestamo.getValue(0));
				insertar.setInt(2, (Integer) prestamo.getValue(1));
				insertar.setDate(3, (Date) prestamo.getValue(2));
				insertar.setDate(4, (Date) prestamo.getValue(3));
				insertar.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
