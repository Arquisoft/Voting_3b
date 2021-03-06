package es.uniovi.asw;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.junit.Test;

import es.uniovi.asw.dbUpdate.InsertP;
import es.uniovi.asw.dbUpdate.WReportR;
import es.uniovi.asw.dbUpdate.persistence.PersistenceFactory;
import es.uniovi.asw.model.Censos;
import es.uniovi.asw.model.ColegioElectoral;
import es.uniovi.asw.model.Opcion;
import es.uniovi.asw.model.TipoVotoForm;
import es.uniovi.asw.model.VotacionForm;
import es.uniovi.asw.model.Votos;
import es.uniovi.asw.parser.InsertR;
import es.uniovi.asw.parser.Votante;
import es.uniovi.asw.parser.carta.CartaCensuses;
import es.uniovi.asw.parser.carta.CartaPDF;
import es.uniovi.asw.parser.carta.CartaTextoPlano;
import es.uniovi.asw.parser.read.RCensus;
import es.uniovi.asw.parser.read.ReadCensus;
import es.uniovi.asw.reportWriter.WReportP;

public class CensusesTest {

@Test
public void LoadUsers(){
	LoadUsers.verContenidoBase();
	String rutaExcel = "src/test/resources/test.xlsx";
	LoadUsers.formatearCartaTxt(rutaExcel);
	String ruta = "src/test/resources/cartas/90500084Y.txt";
    File archivo = new File(ruta);
    assertEquals(true, archivo.exists());
    archivo.delete();

    ruta = "src/test/resources/cartas/19160962F.txt";
    archivo = new File(ruta);
    assertEquals(true, archivo.exists());
    archivo.delete();
    
    ruta = "src/test/resources/cartas/09940449X.txt";
    archivo = new File(ruta);
    assertEquals(true, archivo.exists());
    archivo.delete();
    
    LoadUsers.formatearCartaPdf(rutaExcel);
	ruta = "src/test/resources/cartas/90500084Y.pdf";
    archivo = new File(ruta);
    assertEquals(true, archivo.exists());
    archivo.delete();

    ruta = "src/test/resources/cartas/19160962F.pdf";
    archivo = new File(ruta);
    assertEquals(true, archivo.exists());
    archivo.delete();
    
    ruta = "src/test/resources/cartas/09940449X.pdf";
    archivo = new File(ruta);
    assertEquals(true, archivo.exists());
    archivo.delete();
    
    LoadUsers.verContenidoBase();
    LoadUsers.borrarCamposBD();
    LoadUsers.verContenidoBase();
	
}
	
	
@Test
public void DBUpdate1() {
		
		WReportR report = new WReportR(new WReportP());
		InsertP insertP = new InsertP(report);
		
		//Creamos un nuevo votante
		Votante v = new Votante("Pedro Garcia", "56982104R", "pedro@gmail.com", "AST001", "ghyts52?");
		//Lo insertamos en la base de datos
		insertP.insertar(v);
		Votante v2 = PersistenceFactory.getVotantesPers().findVotante(v.getNIF());
		//Comprobamos que todos los datos se han añadido correctamente
		assertEquals(v.getNombre(), v2.getNombre());
		assertEquals(v.getNIF(), v2.getNIF());
		assertEquals(v.getEmail(), v2.getEmail());
		assertEquals(v.getCodColegioElectoral(), v2.getCodColegioElectoral());
		assertEquals(v.getPassword(), v2.getPassword());
		
		
		
		//Intentamos introducir el mismo votante
		insertP.insertar(v);
		
		//Intentamos buscar un votante inexistente en la base de datos
		v2 = PersistenceFactory.getVotantesPers().findVotante("");
		//assertEquals(v2, null);
		assertNull(v2);
		
		PersistenceFactory.getVotantesPers().delete();
		
		
}

@Test
public void DBUpdate2() {
	
	WReportR report = new WReportR(new WReportP());
	InsertP insertP = new InsertP(report);
	
	//Creamos varios votantes con errores
	ArrayList<Votante> list = new ArrayList<Votante>();
	list.add(new Votante("", "56933104R", "pedro@gmail.com", "AST001", "ghyts52?"));
	list.add(new Votante("Carmen", "", "carmen@gmail.com", "AST001", "alsjd85?"));
	list.add(new Votante("Pablo", "89641208G", "", "AST001", "alsjd85?"));
	list.add(new Votante("Javi", "85214236T", "javi@gmail.com", "", "liksj67(")); //Cambios para que coincida el tipo de dato VARCHAR
	list.add(new Votante("Javi", "85214236T", "javi@gmail.com", "AST001", ""));
	
	Votante v3;
	for(Votante vot: list){
		insertP.insertar(vot);
		 v3= PersistenceFactory.getVotantesPers().findVotante(vot.getNIF());
		 
		//Comprobamos que no se han añadido a la base de datos
		//assertEquals(v3, null);
		 assertNull(v3);
	}

	PersistenceFactory.getVotantesPers().delete();
}

@Test
public void Persistence(){
	Votante v = new Votante("Pedro Garcia", "56982104R", "pedro@gmail.com", "AST001", "ghyts52?");
	PersistenceFactory.getVotantesPers().insert(v, new WReportR(new WReportP()));
	Votante v2 = PersistenceFactory.getVotantesPers().findVotante("56982104R");
	assertEquals(v.toString(), v2.toString());
	
	//Error
		PersistenceFactory.getVotantesPers().insert(v,
				new WReportR(new WReportP()));
		PersistenceFactory.getVotantesPers().delete();
		v2 = PersistenceFactory.getVotantesPers().findVotante("56982104R");
		// assertEquals(v2, null);
		assertNull(v2);	
}

@Test
public void Parser(){
	InsertR insert = new InsertR(new RCensus(), new CartaTextoPlano(), "src/test/resources/test.xlsx");
	WReportR report = new WReportR(new WReportP());
	InsertP insertP = new InsertP(report);
	insert.addVotante(insertP);
	
	String ruta = "src/test/resources/cartas/90500084Y.txt";
    File archivo = new File(ruta);
    assertEquals(true, archivo.exists());
    archivo.delete();

    ruta = "src/test/resources/cartas/19160962F.txt";
    archivo = new File(ruta);
    assertEquals(true, archivo.exists());
    archivo.delete();
    
    ruta = "src/test/resources/cartas/09940449X.txt";
    archivo = new File(ruta);
    assertEquals(true, archivo.exists());
    archivo.delete();
    PersistenceFactory.getVotantesPers().delete();
}

@Test
public void TestVotante(){
	Votante v = new Votante();
	v.setNombre("Pablo");
	v.setNIF("665151G");
	v.setEmail("Pablo@gmail.com");
	v.setCodColegioElectoral("AST001");
	v.setPassword("kasnfkf56&");
	String vot = v.toString();
	String vot2 = "Votante [nombre=" + v.getNombre() + ", NIF=" + v.getNIF() + ", email=" + v.getEmail() 
			+ ", codColegioElectoral="+ v.getCodColegioElectoral() + ", password=" + v.getPassword() + "]";
	
	assertEquals(vot, vot2);
	
}

@Test
public void RCensusTest() {
	
	ReadCensus read = new RCensus();
	Map<Integer, ArrayList<String>> mapa = read.leerFichero("src/test/resources/test.xlsx");
	
	assertEquals(mapa.get(0).get(0), "Juan Torres Pardo");
	assertEquals(mapa.get(0).get(1), "90500084Y");
	assertEquals(mapa.get(1).get(0), "Luis López Fernando");
	assertEquals(mapa.get(1).get(3), "AST001");
	assertEquals(mapa.get(2).get(0), "Ana Torres Pardo");
	
}

@Test
public void CartaTXT() {
	Votante v = new Votante("Pedro Garcia", "58622104P", "pedro@gmail.com", "AST001", "ghyts52?");
	
	//La carta aun no existe
	String ruta = "src/test/resources/cartas/58622104P.txt";
    File archivo = new File(ruta);
    assertEquals(false, archivo.exists());
    
    //Creamos la carta
	CartaCensuses carta = new CartaTextoPlano();
	carta.crearCarta(v);
	
    archivo = new File(ruta);
    //La carta ya existe
    assertEquals(true, archivo.exists());
    
    archivo.delete();
}

@Test
public void CartaPDF() {
	Votante v = new Votante("Pedro Garcia", "58622104S", "pedro@gmail.com", "AST001", "ghyts52?");
	
	//La carta aun no existe
	String ruta = "src/test/resources/cartas/58622104S.pdf";
    File archivo = new File(ruta);
    assertEquals(false, archivo.exists());
    
    //Creamos la carta
	CartaCensuses carta = new CartaPDF();
	carta.crearCarta(v);
	
    archivo = new File(ruta);
    //La carta ya existe
    assertEquals(true, archivo.exists());

    archivo.delete();
    
}
@Test
public void WriteReport(){
	WReportR report = new WReportR(new WReportP());
	InsertP insertP = new InsertP(report);
	
	//Creamos varios votantes con errores
	ArrayList<Votante> list = new ArrayList<Votante>();
	list.add(new Votante("Pedro", "56788104R", "pedro@gmail.com", "AST001", "ghyts52?"));
	list.add(new Votante("Carmen", "96458545Y", "carmen@gmail.com", "AST001", "alsjd85?"));
	list.add(new Votante("Pablo", "896651208G", "pablo@gmail.com", "AST001", "alsjd85?"));
	list.add(new Votante("Javi", "852344236S", "javi@gmail.com", "AST001", "liksj67("));
	for(Votante vot: list){
		insertP.insertar(vot);
	}
	
	String cadena;
	Calendar fechaActual = Calendar.getInstance(); 
	
	try{
	    FileReader f = new FileReader("log.txt");
	    BufferedReader b = new BufferedReader(f);
	    String[] error;
	    
	    while((cadena = b.readLine())!=null) {} //vamos a la última linea
		for(Votante vot: list){
			insertP.insertar(vot);
			
			//LOG
			if((cadena = b.readLine())!=null) {
				error = cadena.split(";");
				//La fecha es correcta
				String fecha = String.valueOf(fechaActual.get(Calendar.DAY_OF_MONTH))
		                 +"/"+String.valueOf(fechaActual.get(Calendar.MONTH)+1)
		                 +"/"+String.valueOf(fechaActual.get(Calendar.YEAR));
				assertEquals(fecha, error[0]);
				
				String error2  = "ERROR: El votante con nombre: " + vot.getNombre() + " y DNI: " + vot.getNIF()
					+ " no se ha podido cargar correctamente en la base de datos.";
				assertEquals(error2, error[2]);
				
		    }
			
		}
		PersistenceFactory.getVotantesPers().delete();
		
		b.close();
	}catch (Exception e) {
		e.getStackTrace();
	}
	
}

@Test
public void Modelo() {
	Censos cen = new Censos("paco", "5656565", "asd@gmail.com", "AT001", "asfln6");
	cen.setEmail("as@gmail.com");
	assertEquals(cen.getEmail(), "as@gmail.com");
	cen.setId(1);
	assertEquals(cen.getId(), 1);
	cen.setNif("51651");
	assertEquals(cen.getNif(), "51651");
	cen.setNombre("jose");
	assertEquals(cen.getNombre(), "jose");
	cen.setNombre("AT001");
	assertEquals(cen.getCofColegioElectoral(), "AT001");
	cen.setPassword("pass");
	assertEquals(cen.getPassword(), "pass");
	
	
	ColegioElectoral col = new ColegioElectoral("AST001", "circ", "ciudad", "com");
	col.setCiudad("ciudad");
	assertEquals(col.getCiudad(), "ciudad");
	col.setCodColegioElectoral("AST001");
	assertEquals(col.getCodColegioElectoral(), "AST001");
	col.setComunidadAutonoma("com");
	assertEquals(col.getComunidadAutonoma(), "com");
	col.setCircunscripcion("circ");
	assertEquals(col.getCircunscripcion(), "circ");
	
	Opcion op = new Opcion(new Long(1), "opcion1", new Long(1));
	assertEquals(op.getId(), new Long(1));
	assertEquals(op.getIdVotacion(), new Long(1));
	assertEquals(op.getNombre(), "opcion1");

	
	TipoVotoForm tip = new TipoVotoForm("WEB");
	tip.getNif();
	assertEquals(tip.getTipoVoto(), "WEB");
	
	Votante vot = new Votante("Pedro", "56788104R", "pedro@gmail.com", "AST001", "ghyts52");
	assertEquals(vot.getNombre(), "Pedro");
	assertEquals(vot.getPassword(), "ghyts52");
	assertEquals(vot.getEmail(), "pedro@gmail.com");
	assertEquals(vot.getNIF(), "56788104R");
	assertEquals(vot.getCodColegioElectoral(), "AST001");


	VotacionForm votf = new VotacionForm(new Date().toString(), new Date().toString(), "s");
	assertEquals(votf.getTipoVotacion(), "s");
	votf.getFechaFin();
	votf.getFechaInicio();
	
	Votos v = new Votos("s", new Long(1), 1, new Long(1), "AST001");
	assertEquals(v.getColegioElectoral(), "AST001");
	assertEquals(v.getOpcionEscogida(), new Long(1));
	assertEquals(v.getIdVotacion(), new Long(1));
	assertEquals(v.getTipoVoto(), "s");
	

}


}
