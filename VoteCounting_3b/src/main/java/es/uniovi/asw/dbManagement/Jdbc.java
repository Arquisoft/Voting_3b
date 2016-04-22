package es.uniovi.asw.dbManagement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.dbManagement.model.ColegioData;
import es.uniovi.asw.dbManagement.model.OpcionData;
import es.uniovi.asw.dbManagement.model.PersonaData;
import es.uniovi.asw.dbManagement.model.VotoData;

public class Jdbc {

	/*
	 * Configuration for Oracle private static String DRIVER =
	 * "oracle.jdbc.driver.OracleDriver"; private static String URL =
	 * "jdbc:oracle:thin:@156.35.94.99:1521:DESA"; private static String USER =
	 * ""; private static String PASS = "";
	 */
	/*
	 * Configuration for Hsqldb
	 */
	// private static String DRIVER = "org.hsqldb.jdbcDriver";
	// private static String URL = "jdbc:hsqldb:hsql://localhost";
	// private static String USER = "sa";
	// private static String PASS = "";

	/*
	 * Configuration for h2
	 */
	// private static String BD = "h2";
	private static final String DRIVER = "org.h2.Driver";
	private static String URL = "jdbc:h2:~/test";
	private static String USER = "sa";
	private static String PASS = "";

	static {
		try {
			Class.forName(DRIVER);
			crearDB();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Driver not found in classpath", e);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}

	private static void crearDB()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// ejecutamos el script de creacion de la BD

		/*
		 * String _createColegioElectoral =
		 * "CREATE TABLE IF NOT EXISTS COLEGIOELECTORAL(codColegioElectoral VARCHAR(255) not null,circunscripcion VARCHAR(255) not null,ciudad VARCHAR(255) not null,comunidadAutonoma VARCHAR(255) not null,PRIMARY KEY(codColegioElectoral))"
		 * ; String _createCensos =
		 * "CREATE TABLE IF NOT EXISTS CENSOS(id INTEGER NOT NULL,nombre VARCHAR(255),nif VARCHAR(255),email VARCHAR(255),codColegioElectoral VARCHAR(255),password VARCHAR(255),PRIMARY KEY(nif))"
		 * ; String _createVotacion =
		 * "CREATE TABLE IF NOT EXISTS VOTACION(id BIGINT not null,diaInicio DATE not null,diaFin DATE not null,tipoVotacion VARCHAR(255) not null,PRIMARY KEY(id))"
		 * ; String _createVotante =
		 * "CREATE TABLE IF NOT EXISTS VOTANTE(nif VARCHAR(255) not null,tipoVoto VARCHAR(255) not null,estado BOOLEAN not null,idVotacion BIGINT not null,PRIMARY KEY(nif),FOREIGN KEY(idVotacion) REFERENCES VOTACION(id),FOREIGN KEY(nif) REFERENCES CENSOS(nif))"
		 * ; String _createOpcion =
		 * "CREATE TABLE IF NOT EXISTS OPCION(id BIGINT not null,nombre VARCHAR(255),idVotacion BIGINT not null,PRIMARY KEY(id),FOREIGN KEY(idVotacion) REFERENCES VOTACION(id))"
		 * ; String _createVotos =
		 * "CREATE TABLE IF NOT EXISTS VOTOS(id BIGINT not null,tipoVoto VARCHAR(255) not null,opcionEscogida BIGINT not null,totalVotos INTEGER not null,idVotacion BIGINT not null,colegioElectoral VARCHAR(255) not null,PRIMARY KEY(opcionEscogida, idVotacion, colegioElectoral, tipoVoto),FOREIGN KEY(opcionEscogida) REFERENCES OPCION(id),FOREIGN KEY(idVotacion) REFERENCES VOTACION(id),FOREIGN KEY(colegioElectoral) REFERENCES COLEGIOELECTORAL(codColegioElectoral))"
		 * ;
		 */
		Class.forName(DRIVER).newInstance();
		Connection con = getConnection();
		java.sql.Statement stm = con.createStatement();

		stm.execute("drop table if exists votos");
		stm.execute("drop table if exists opcion");
		stm.execute("drop table if exists votante");
		stm.execute("drop table if exists votacion");
		stm.execute("drop table if exists censos");
		stm.execute("drop table if exists colegioelectoral");

		String _createColegioElectoral = "CREATE TABLE COLEGIOELECTORAL(codColegioElectoral VARCHAR(255) not null,circunscripcion VARCHAR(255) not null,ciudad VARCHAR(255) not null,comunidadAutonoma VARCHAR(255) not null,PRIMARY KEY(codColegioElectoral))";
		String _createCensos = "CREATE TABLE CENSOS(id INTEGER NOT NULL,nombre VARCHAR(255),nif VARCHAR(255),email VARCHAR(255),codColegioElectoral VARCHAR(255),password VARCHAR(255),PRIMARY KEY(nif))";
		String _createVotacion = "CREATE TABLE VOTACION(id BIGINT not null,diaInicio DATE not null,diaFin DATE not null,tipoVotacion VARCHAR(255) not null,PRIMARY KEY(id))";
		String _createVotante = "CREATE TABLE VOTANTE(nif VARCHAR(255) not null,tipoVoto VARCHAR(255) not null,estado BOOLEAN not null,idVotacion BIGINT not null,PRIMARY KEY(nif),FOREIGN KEY(idVotacion) REFERENCES VOTACION(id),FOREIGN KEY(nif) REFERENCES CENSOS(nif))";
		String _createOpcion = "CREATE TABLE OPCION(id BIGINT not null,nombre VARCHAR(255),idVotacion BIGINT not null,PRIMARY KEY(id),FOREIGN KEY(idVotacion) REFERENCES VOTACION(id))";
		String _createVotos = "CREATE TABLE VOTOS(id BIGINT not null,tipoVoto VARCHAR(255) not null,opcionEscogida BIGINT not null,totalVotos INTEGER not null,idVotacion BIGINT not null,colegioElectoral VARCHAR(255) not null,PRIMARY KEY(opcionEscogida, idVotacion, colegioElectoral, tipoVoto),FOREIGN KEY(opcionEscogida) REFERENCES OPCION(id),FOREIGN KEY(idVotacion) REFERENCES VOTACION(id),FOREIGN KEY(colegioElectoral) REFERENCES COLEGIOELECTORAL(codColegioElectoral))";
		/*
		 * if (tablaExiste("VOTOS")) stm.execute("delete from VOTOS"); if
		 * (tablaExiste("OPCION")) stm.execute("delete from OPCION"); if
		 * (tablaExiste("VOTANTE")) stm.execute("delete from VOTANTE"); if
		 * (tablaExiste("VOTACION")) stm.execute("delete from VOTACION"); if
		 * (tablaExiste("CENSOS")) stm.execute("delete from CENSOS"); if
		 * (tablaExiste("COLEGIOELECTORAL")) stm.execute(
		 * "delete from COLEGIOELECTORAL");
		 * 
		 * stm.execute(_createColegioElectoral); stm.execute(_createCensos);
		 * stm.execute(_createVotante); stm.execute(_createVotacion);
		 * stm.execute(_createOpcion); stm.execute(_createVotos);
		 */
		stm.execute(_createColegioElectoral);
		stm.execute(_createCensos);
		stm.execute(_createVotacion);
		stm.execute(_createVotante);
		stm.execute(_createOpcion);
		stm.execute(_createVotos);

		rellenarColegioElectoral();
		rellenarVotacion();
		rellenarCensos();
		rellenarOpcion();

		// stm.execute("truncate table VOTOS");

		rellenarVotos();

		stm.close();
		con.close();

	}

	private static void rellenarVotacion() {
		try {
			Connection c = Jdbc.getConnection();
			PreparedStatement ps = c.prepareStatement("INSERT INTO VOTACION VALUES(?,?,?,?)");
			ps.setLong(1, 1L);
			ps.setDate(2, new Date(new java.util.Date().getTime()));
			ps.setDate(3, new Date(new java.util.Date().getTime()));
			ps.setString(4, "REFERENDUM");
			ps.execute();
			ps.close();
			c.close();

		} catch (SQLException e) {
			// String error = "El votante con nombre: " + v.getNombre() + "
			// y DNI: " + v.getNIF();
			// error = error + " no se ha podido cargar correctamente en la
			// base de datos.";
			// reportR.setLog("ERROR: " + error);
			e.printStackTrace();
		}
	}

	private static void rellenarVotos() {

		List<VotoData> votos = new ArrayList<VotoData>();

		votos.add(new VotoData("ELECTRONICO", 0L, 65, 1L, "AST001"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 12, 1L, "AST001"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 25, 1L, "AST001"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 0L, 39, 1L, "AST002"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 23, 1L, "AST002"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 28, 1L, "AST002"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 0L, 10, 1L, "AST003"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 19, 1L, "AST003"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 22, 1L, "AST003"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 0L, 46, 1L, "AST004"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 15, 1L, "AST004"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 26, 1L, "AST004"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 0L, 36, 1L, "AST005"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 32, 1L, "AST005"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 15, 1L, "AST005"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 0L, 68, 1L, "AST006"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 82, 1L, "AST006"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 26, 1L, "AST006"));// 345,456

		votos.add(new VotoData("MANUAL", 0L, 6, 1L, "AST001"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 5, 1L, "AST001"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 3, 1L, "AST001"));// 345,456
		votos.add(new VotoData("MANUAL", 0L, 9, 1L, "AST002"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 8, 1L, "AST002"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 3, 1L, "AST002"));// 345,456
		votos.add(new VotoData("MANUAL", 0L, 5, 1L, "AST003"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 7, 1L, "AST003"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 5, 1L, "AST003"));// 345,456
		votos.add(new VotoData("MANUAL", 0L, 6, 1L, "AST004"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 1, 1L, "AST004"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 2, 1L, "AST004"));// 345,456
		votos.add(new VotoData("MANUAL", 0L, 9, 1L, "AST005"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 5, 1L, "AST005"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 3, 1L, "AST005"));// 345,456
		votos.add(new VotoData("MANUAL", 0L, 4, 1L, "AST006"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 6, 1L, "AST006"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 8, 1L, "AST006"));// 345,456

		votos.add(new VotoData("ELECTRONICO", 0L, 61, 1L, "GAL001"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 85, 1L, "GAL001"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 21, 1L, "GAL001"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 0L, 36, 1L, "GAL002"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 24, 1L, "GAL002"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 95, 1L, "GAL002"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 0L, 24, 1L, "GAL003"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 14, 1L, "GAL003"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 26, 1L, "GAL003"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 0L, 42, 1L, "GAL004"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 16, 1L, "GAL004"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 62, 1L, "GAL004"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 0L, 14, 1L, "GAL005"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 12, 1L, "GAL005"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 11, 1L, "GAL005"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 0L, 35, 1L, "GAL006"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 24, 1L, "GAL006"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 30, 1L, "GAL006"));// 345,456

		votos.add(new VotoData("MANUAL", 0L, 6, 1L, "GAL001"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 2, 1L, "GAL001"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 4, 1L, "GAL001"));// 345,456
		votos.add(new VotoData("MANUAL", 0L, 3, 1L, "GAL002"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 1, 1L, "GAL002"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 8, 1L, "GAL002"));// 345,456
		votos.add(new VotoData("MANUAL", 0L, 7, 1L, "GAL003"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 5, 1L, "GAL003"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 4, 1L, "GAL003"));// 345,456
		votos.add(new VotoData("MANUAL", 0L, 1, 1L, "GAL004"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 6, 1L, "GAL004"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 3, 1L, "GAL004"));// 345,456
		votos.add(new VotoData("MANUAL", 0L, 8, 1L, "GAL005"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 5, 1L, "GAL005"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 4, 1L, "GAL005"));// 345,456
		votos.add(new VotoData("MANUAL", 0L, 1, 1L, "GAL006"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 9, 1L, "GAL006"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 5, 1L, "GAL006"));// 345,456

		votos.add(new VotoData("ELECTRONICO", 0L, 52, 1L, "CAN001"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 10, 1L, "CAN001"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 20, 1L, "CAN001"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 0L, 32, 1L, "CAN002"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 21, 1L, "CAN002"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 26, 1L, "CAN002"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 0L, 15, 1L, "CAN003"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 19, 1L, "CAN003"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 25, 1L, "CAN003"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 0L, 52, 1L, "CAN004"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 10, 1L, "CAN004"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 20, 1L, "CAN004"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 0L, 32, 1L, "CAN005"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 21, 1L, "CAN005"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 26, 1L, "CAN005"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 0L, 32, 1L, "CAN006"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 1L, 21, 1L, "CAN006"));// 345,456
		votos.add(new VotoData("ELECTRONICO", 2L, 26, 1L, "CAN006"));// 345,456

		votos.add(new VotoData("MANUAL", 0L, 5, 1L, "CAN001"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 1, 1L, "CAN001"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 2, 1L, "CAN001"));// 345,456
		votos.add(new VotoData("MANUAL", 0L, 3, 1L, "CAN002"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 2, 1L, "CAN002"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 2, 1L, "CAN002"));// 345,456
		votos.add(new VotoData("MANUAL", 0L, 3, 1L, "CAN003"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 1, 1L, "CAN003"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 2, 1L, "CAN003"));// 345,456
		votos.add(new VotoData("MANUAL", 0L, 5, 1L, "CAN004"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 1, 1L, "CAN004"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 2, 1L, "CAN004"));// 345,456
		votos.add(new VotoData("MANUAL", 0L, 3, 1L, "CAN005"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 2, 1L, "CAN005"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 2, 1L, "CAN005"));// 345,456
		votos.add(new VotoData("MANUAL", 0L, 3, 1L, "CAN006"));// 345,456
		votos.add(new VotoData("MANUAL", 1L, 2, 1L, "CAN006"));// 345,456
		votos.add(new VotoData("MANUAL", 2L, 2, 1L, "CAN006"));// 345,456

		// List<ColegioData> colegios =
		// Factories.persistence.census().getColegios();

		/*
		 * for(ColegioData c:colegios){ List<VotanteData> votantes =
		 * Factories.persistence.votes().getVotantesPorColegio(c.
		 * getCodColegioElectoral()); for(VotanteData v:votantes)
		 * 
		 * }
		 */
		int i = 0;
		for (VotoData v : votos) {
			try {
				Connection c = Jdbc.getConnection();
				// String tipoVoto, Long opcion, Integer totalVotos, Long
				// idVotacion, String codColegioElectoral
				PreparedStatement ps = c.prepareStatement("INSERT INTO VOTOS VALUES(?,?,?,?,?,?)");
				ps.setLong(1, i);
				ps.setString(2, v.getTipoVoto());
				ps.setLong(3, v.getOpcion());
				ps.setLong(4, v.getTotalVotos());
				ps.setLong(5, v.getIdVotacion());
				ps.setString(6, v.getCodColegioElectoral());
				ps.execute();
				i++;
				ps.close();
				c.close();

			} catch (SQLException e) {
				// String error = "El votante con nombre: " + v.getNombre() + "
				// y DNI: " + v.getNIF();
				// error = error + " no se ha podido cargar correctamente en la
				// base de datos.";
				// reportR.setLog("ERROR: " + error);
				e.printStackTrace();
			}
		}

	}

	private static void rellenarColegioElectoral() {
		List<ColegioData> colegios = new ArrayList<ColegioData>();
		colegios.add(new ColegioData("AST001", "AST0", "Oviedo", "Asturias"));
		colegios.add(new ColegioData("AST002", "AST0", "Oviedo", "Asturias"));
		colegios.add(new ColegioData("AST003", "AST0", "Oviedo", "Asturias"));
		colegios.add(new ColegioData("AST004", "AST0", "Oviedo", "Asturias"));
		colegios.add(new ColegioData("AST005", "AST0", "Oviedo", "Asturias"));
		colegios.add(new ColegioData("AST006", "AST0", "Oviedo", "Asturias"));
		colegios.add(new ColegioData("GAL001", "GAL0", "Vigo", "Galicia"));
		colegios.add(new ColegioData("GAL002", "GAL0", "Vigo", "Galicia"));
		colegios.add(new ColegioData("GAL003", "GAL0", "Vigo", "Galicia"));
		colegios.add(new ColegioData("GAL004", "GAL0", "Vigo", "Galicia"));
		colegios.add(new ColegioData("GAL005", "GAL0", "Vigo", "Galicia"));
		colegios.add(new ColegioData("GAL006", "GAL0", "Vigo", "Galicia"));
		colegios.add(new ColegioData("CAN001", "CAN0", "Santander", "Cantabria"));
		colegios.add(new ColegioData("CAN002", "CAN0", "Santander", "Cantabria"));
		colegios.add(new ColegioData("CAN003", "CAN0", "Santander", "Cantabria"));
		colegios.add(new ColegioData("CAN004", "CAN0", "Santander", "Cantabria"));
		colegios.add(new ColegioData("CAN005", "CAN0", "Santander", "Cantabria"));
		colegios.add(new ColegioData("CAN006", "CAN0", "Santander", "Cantabria"));

		for (ColegioData col : colegios) {
			try {
				Connection c = Jdbc.getConnection();
				PreparedStatement ps = c.prepareStatement("INSERT INTO COLEGIOELECTORAL VALUES(?,?,?,?)");

				ps.setString(1, col.getCodColegioElectoral());
				ps.setString(2, col.getCircunscripcion());
				ps.setString(3, col.getCiudad());
				ps.setString(4, col.getComunidadAutonoma());
				ps.execute();
				ps.close();
				c.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private static void rellenarOpcion() {
		List<String> opciones = new ArrayList<String>();
		opciones.add("SI");
		opciones.add("NO");
		opciones.add("BLANCO");
		OpcionData opdata = new OpcionData(opciones, 1L);
		int i = 0;
		for (String v : opdata.getOpciones()) {
			try {
				Connection c = Jdbc.getConnection();
				PreparedStatement ps = c.prepareStatement("INSERT INTO OPCION VALUES(?,?,?)");
				ps.setLong(1, i);
				ps.setString(2, v);
				ps.setLong(3, opdata.getIdVotacion());
				ps.execute();
				ps.close();
				c.close();
				i++;

			} catch (SQLException e) {
				// String error = "El votante con nombre: " + v.getNombre() + "
				// y DNI: " + v.getNIF();
				// error = error + " no se ha podido cargar correctamente en la
				// base de datos.";
				// reportR.setLog("ERROR: " + error);
				e.printStackTrace();
			}
		}

	}

	private static void rellenarCensos() {

		List<PersonaData> personas = new ArrayList<PersonaData>();
		List<String> comunidades = new ArrayList<String>();
		comunidades.add("AST");
		comunidades.add("GAL");
		comunidades.add("CAN");
		personas = RellenarPersonas(comunidades);
		try {
			Connection c = Jdbc.getConnection();
			for (PersonaData v : personas) {
				PreparedStatement ps = c.prepareStatement(
						"INSERT INTO CENSOS (ID,NOMBRE, NIF, EMAIL, CODCOLEGIOELECTORAL, PASSWORD) VALUES(?,?, ?, ?, ?, ?)");
				ps.setInt(1, personas.indexOf(v));
				ps.setString(2, v.getNombre());
				ps.setString(3, v.getNIF());
				ps.setString(4, v.getEmail());
				ps.setString(5, v.getCodColegioElectoral());
				ps.setString(6, v.getPassword());
				ps.execute();
				ps.close();
			}
			c.close();
		} catch (SQLException e) {

			// reportR.setLog("ERROR: " + error);
			e.printStackTrace();
		}
		for (PersonaData v : personas) {
			int i = personas.indexOf(v);
			if (i % 10 != 0) {
				if (i % 6 != 0) {
					rellenaVotante(v, "ELECTRONICO");
					// electronicos++;
				} else {
					rellenaVotante(v, "MANUAL");
					// manuales++;
				}
			}
		}

	}

	private static List<PersonaData> RellenarPersonas(List<String> comunidades) {
		List<PersonaData> personas = new ArrayList<PersonaData>();
		PersonaData persona = null;
		for (String comunidad : comunidades)
			for (Integer i = 0; i < 1000; i++) {

				String sufijo;
				if (i < 300)
					sufijo = "001";
				else if (i < 450)
					sufijo = "002";
				else if (i < 600)
					sufijo = "003";
				else if (i < 750)
					sufijo = "004";
				else if (i < 900)
					sufijo = "005";
				else
					sufijo = "006";
				persona = new PersonaData("Pepe".concat(i.toString()),
						String.valueOf(000000000 + i).concat(comunidad.substring(0, 1)),
						"pepe".concat(i.toString()).concat("@mail.com"), comunidad.concat(sufijo), "P3P3pepÇ");
				personas.add(persona);

			}
		return personas;
	}

	private static void rellenaVotante(PersonaData persona, String tipoVoto) {
		try {
			Connection c = Jdbc.getConnection();
			PreparedStatement ps = c
					.prepareStatement("INSERT INTO VOTANTE (tipoVoto, estado, idVotacion, nif) VALUES (?, ?, ?, ?)");
			ps.setString(1, tipoVoto);
			ps.setBoolean(2, true);
			ps.setLong(3, 1L);
			ps.setString(4, persona.getNIF());
			ps.execute();

			ps.close();
			c.close();

			// votos.add(new VotoData(tipoVoto,(long) (Math.random()*2+0), 300,
			// 1L, "AST000"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Connection createThreadConnection() throws SQLException {
		Connection con = getConnection();
		con.setAutoCommit(false);
		threadConnection.set(con);
		return con;
	}

	private static ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();

	public static Connection getCurrentConnection() {
		return threadConnection.get();
	}

}