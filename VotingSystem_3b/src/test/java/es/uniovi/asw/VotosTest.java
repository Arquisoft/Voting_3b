package es.uniovi.asw;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Votos;
import es.uniovi.asw.persistence.config.ObjectDaoImpl;
import es.uniovi.asw.physicalVotes.dBUpdate.InsertVotesP;
import es.uniovi.asw.physicalVotes.dBUpdate.WreportR;
import es.uniovi.asw.physicalVotes.physicalVotesConfig.Insert;
import es.uniovi.asw.physicalVotes.physicalVotesConfig.InsertPhysicalR;
import es.uniovi.asw.physicalVotes.physicalVotesConfig.RVotes;
import es.uniovi.asw.physicalVotes.reportWriter.WreportP;

public class VotosTest {

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

		/*dao = new ObjectDaoImpl();
		dao.restoreDatabase();
		dao.insertVotos(new Votos("Web", new Long(1), 1, new Long(1), "12"));
		assertEquals(1, dao.findAllVotos().size());
		assertEquals("Web", dao.findVotos(new Long(1)).getTipoVoto());
		dao.insertVotos(new Votos("Fisico", new Long(1), 1, new Long(1), "12"));
		assertEquals(2, dao.findAllVotos().size());
		*/
	}
	
	@Test
	public void testCargarVotos() {
		dao.restoreDatabase();
		
		WreportR report1 = new WreportR(new WreportP());
		Insert r1 = new InsertPhysicalR(new RVotes(),
				"src/test/resources/votacionesFisicas.xlsx");
		boolean exito = r1.addVoto(new InsertVotesP(report1));
		assertEquals(true, exito);
		assertEquals(2, dao.findAllVotos().size());
		
	}
	
	@Test
	public void testCargarVotosMal() {
		dao.restoreDatabase();
		
		WreportR report1 = new WreportR(new WreportP());
		Insert r1 = new InsertPhysicalR(new RVotes(),
				"src/test/resources/votacionesFisicasMAL.xlsx");
		boolean exito = r1.addVoto(new InsertVotesP(report1));
		assertEquals(false, exito);
		
				
	}
	
	

}
