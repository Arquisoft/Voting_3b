package es.uniovi.asw;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Votacion;
import es.uniovi.asw.persistence.config.ObjectDaoImpl;

public class VotacionTest {

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

	// Votación con todos los parámetros
	@Test
	public void test() {
		
		Votacion v = new Votacion(new Date(), new Date(), "Referendum");
		dao.insertVotacion(v, null);
		assertEquals(2, dao.findAllVotaciones().size());
		assertEquals(v.getTipoVotacion(), dao.findVotacion(new Long(2))
				.getTipoVotacion());
	}
	
	// Votación sin fecha inicial
	
	@Test(expected = NullPointerException.class)
	public void test2(){
		
		Votacion v = new Votacion(null, new Date(), "Referendum");
		
		dao.insertVotacion(v, null);
		assertEquals(0, dao.findAllVotaciones().size());
	}
	
	// Votación sin fecha final
	
	@Test(expected = NullPointerException.class)
	public void test3(){
		
		Votacion v = new Votacion(new Date(), null, "Referendum");
		
		dao.insertVotacion(v, null);
		assertEquals(0, dao.findAllVotaciones().size());
		
	}
	
	// Votacion correcta de tipo Generales
	@Test
	public void test4() {

		dao.restoreDatabase();
		Votacion v = new Votacion(new Date(), new Date(), "Generales");
		dao.insertVotacion(v, null);
		assertEquals(2, dao.findAllVotaciones().size());
		assertEquals(v.getTipoVotacion(), dao.findVotacion(new Long(2))
				.getTipoVotacion());
	}

	

	
}
