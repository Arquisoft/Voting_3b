package es.uniovi.asw;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.electors.dbUpdate.ConfigP;
import es.uniovi.asw.electors.dbUpdate.InsertConfig;
import es.uniovi.asw.electors.dbUpdate.WreportR;
import es.uniovi.asw.electors.electorsConfig.ConfigR;
import es.uniovi.asw.electors.reportWriter.WreportP;
import es.uniovi.asw.model.Votacion;
import es.uniovi.asw.model.VotacionForm;
import es.uniovi.asw.persistence.config.ObjectDaoImpl;

public class ElectorsTest {

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

		InsertConfig insert = new ConfigP(new WreportR(new WreportP()));
		insert.insert(v);
		assertEquals(2, dao.findAllVotaciones().size());
		assertEquals(v.getTipoVotacion(), dao.findVotacion(new Long(2)).getTipoVotacion());
	}

	// Votación sin fecha inicial
	@Test(expected = NullPointerException.class)
	public void test2() {

		Votacion v = new Votacion(null, new Date(), "Referendum");
		InsertConfig insert = new ConfigP(new WreportR(new WreportP()));
		insert.insert(v);
		assertEquals(0, dao.findAllVotaciones().size());
	}

	// Votación sin fecha final

	@Test(expected = NullPointerException.class)
	public void test3() {

		Votacion v = new Votacion(new Date(), null, "Referendum");
		InsertConfig insert = new ConfigP(new WreportR(new WreportP()));
		insert.insert(v);

		assertEquals(0, dao.findAllVotaciones().size());
	}

	// Votacion correcta de tipo Generales
	@Test
	public void test4() {
		dao = new ObjectDaoImpl();
		dao.restoreDatabase();
		Votacion v = new Votacion(new Date(), new Date(), "General");
		InsertConfig insert = new ConfigP(new WreportR(new WreportP()));
		insert.insert(v);

		assertEquals(2, dao.findAllVotaciones().size());
		assertEquals(v.getTipoVotacion(), dao.findVotacion(new Long(1)).getTipoVotacion());
	}

	// Votación sin fecha final

	public void test5() {
		Votacion v = new Votacion(new Date(), new Date(), "");
		InsertConfig insert = new ConfigP(new WreportR(new WreportP()));
		insert.insert(v);

		assertEquals(0, dao.findAllVotaciones().size());
	}

	public void test6() {
		dao.restoreDatabase();
		VotacionForm v = new VotacionForm("01/01/2016 10:00","02/01/2016 10:00", "General");
		new ConfigR(v).addVotacion(new ConfigP(new WreportR(new WreportP())));

		assertEquals(2, dao.findAllVotaciones().size());
		assertEquals(v.getTipoVotacion(), dao.findVotacion(new Long(1)).getTipoVotacion());
	}

}
