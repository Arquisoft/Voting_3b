package es.uniovi.asw;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.ColegioElectoral;
import es.uniovi.asw.persistence.config.ObjectDaoImpl;

public class ColegioElectoralTest {

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
	public void test() {
		dao.insertColegio(new ColegioElectoral("4", "Barrio 2", "Gijon", "Asturies"));
		assertEquals(4,dao.findAllColegios().size()); 
	}
	
	
	// Colegio electoral sin una circunscripcion
	@Test
	public void test2() {
		dao.insertColegio(new ColegioElectoral("15", null, "Oviedo", "Asturies"));
		assertEquals(null,dao.findColegio("15"));
	}
	
	
	// Colegio electoral sin ciudad
	@Test
	public void test3() {
		dao.insertColegio(new ColegioElectoral("16", "Barrio 2", null, "Asturies"));
		assertEquals(null,dao.findColegio("16"));
	}
	
	
	//
	@Test
	public void test4() {
		dao.insertColegio(new ColegioElectoral("17", "Barrio 2", "Aviles", null));
		assertEquals(null,dao.findColegio("17"));
	}


}
