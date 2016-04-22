package es.uniovi.asw;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Opcion;
import es.uniovi.asw.persistence.config.ObjectDaoImpl;

public class OpcionTest {
	
	ObjectDaoImpl dao = null;

	@Before
	public void run() {
		dao = new ObjectDaoImpl();
		dao.restoreDatabase();
	}

	@After
	public void finalize() {
		dao.restoreDatabase();
	}

	@Test
	public void testDB() {
		dao.restoreDatabase();
		
		assertEquals(3, dao.findAllOpciones().size()); //3 opciones del script
		dao.insertOpcion(new Opcion(new Long(4), "Opcion 2", new Long(1)));
		assertEquals(4, dao.findAllOpciones().size());
		dao.deleteOpcion(new Long(4));
		assertEquals(3, dao.findAllOpciones().size());
	}

}
