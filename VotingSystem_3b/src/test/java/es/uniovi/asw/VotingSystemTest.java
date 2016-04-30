package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import es.uniovi.asw.electors.dbUpdate.ConfigP;
import es.uniovi.asw.electors.dbUpdate.InsertConfig;
import es.uniovi.asw.electors.dbUpdate.WreportR;
import es.uniovi.asw.electors.electorsConfig.ConfigR;
import es.uniovi.asw.electors.reportWriter.WreportP;
import es.uniovi.asw.model.ColegioElectoral;
import es.uniovi.asw.model.Opcion;
import es.uniovi.asw.model.Votacion;
import es.uniovi.asw.model.VotacionForm;
import es.uniovi.asw.model.Votante;
import es.uniovi.asw.model.Votos;
import es.uniovi.asw.persistence.config.ObjectDaoImpl;
import es.uniovi.asw.physicalVotes.dBUpdate.InsertVotesP;
import es.uniovi.asw.physicalVotes.physicalVotesConfig.Insert;
import es.uniovi.asw.physicalVotes.physicalVotesConfig.InsertPhysicalR;
import es.uniovi.asw.physicalVotes.physicalVotesConfig.RVotes;
import es.uniovi.asw.virtualVotes.dBUpdate.InsertVirtualVotesP;
import es.uniovi.asw.virtualVotes.virtualVotesConfig.InsertVirtual;
import es.uniovi.asw.virtualVotes.virtualVotesConfig.InsertVirtualR;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VotingSystemTest {

	ObjectDaoImpl dao = null;
	InsertVirtual iV = new InsertVirtualR(null);

	@Before
	public void run() {
		dao = new ObjectDaoImpl();
	}

	@After
	public void finalize() {
		dao.restoreDatabase();
	}

	@Test
	public void test01Colegio() {
		dao.insertColegio(new ColegioElectoral("4", "Barrio 2", "Gijon", "Asturies"));
		assertEquals(4,dao.findAllColegios().size()); 
	}
	
	
	// Colegio electoral sin una circunscripcion
	@Test
	public void test02Colegio() {
		dao.insertColegio(new ColegioElectoral("15", null, "Oviedo", "Asturies"));
		assertEquals(null,dao.findColegio("15"));
	}
	
	
	// Colegio electoral sin ciudad
	@Test
	public void test03Colegio() {
		dao.insertColegio(new ColegioElectoral("16", "Barrio 2", null, "Asturies"));
		assertEquals(null,dao.findColegio("16"));
	}
	
	
	//
	@Test
	public void test04Colegio() {
		dao.insertColegio(new ColegioElectoral("17", "Barrio 2", "Aviles", null));
		assertEquals(null,dao.findColegio("17"));
	}
	
	// Votación con todos los parámetros
		@Test
		public void test05Electors() {

			Votacion v = new Votacion(new Date(), new Date(), "Referendum");

			InsertConfig insert = new ConfigP(new WreportR(new WreportP()));
			insert.insert(v);
			assertEquals(2, dao.findAllVotaciones().size());
			assertEquals(v.getTipoVotacion(), dao.findVotacion(new Long(2)).getTipoVotacion());
		}

		// Votación sin fecha inicial
		@Test(expected = NullPointerException.class)
		public void test06Electors() {

			Votacion v = new Votacion(null, new Date(), "Referendum");
			InsertConfig insert = new ConfigP(new WreportR(new WreportP()));
			insert.insert(v);
			assertEquals(0, dao.findAllVotaciones().size());
		}

		// Votación sin fecha final

		@Test(expected = NullPointerException.class)
		public void test07Electors() {

			Votacion v = new Votacion(new Date(), null, "Referendum");
			InsertConfig insert = new ConfigP(new WreportR(new WreportP()));
			insert.insert(v);

			assertEquals(0, dao.findAllVotaciones().size());
		}

		// Votacion correcta de tipo Generales
		@Test
		public void test08Electors() {
			dao = new ObjectDaoImpl();
			dao.restoreDatabase();
			Votacion v = new Votacion(new Date(), new Date(), "General");
			InsertConfig insert = new ConfigP(new WreportR(new WreportP()));
			insert.insert(v);

			assertEquals(3, dao.findAllVotaciones().size());
			assertEquals(v.getTipoVotacion(), dao.findVotacion(new Long(1)).getTipoVotacion());
		}

		// Votación sin fecha final

		public void test09Electors() {
			Votacion v = new Votacion(new Date(), new Date(), "");
			InsertConfig insert = new ConfigP(new WreportR(new WreportP()));
			insert.insert(v);

			assertEquals(0, dao.findAllVotaciones().size());
		}

		public void test10Electors() {
			dao.restoreDatabase();
			VotacionForm v = new VotacionForm("01/01/2016 10:00","02/01/2016 10:00", "General");
			new ConfigR(v).addVotacion(new ConfigP(new WreportR(new WreportP())));

			assertEquals(2, dao.findAllVotaciones().size());
			assertEquals(v.getTipoVotacion(), dao.findVotacion(new Long(1)).getTipoVotacion());
		}
		
		@Test
		public void test11Opcion() {
			dao.restoreDatabase();
			
			assertEquals(3, dao.findAllOpciones().size()); //3 opciones del script
			dao.insertOpcion(new Opcion(new Long(4), "Opcion 2", new Long(1)));
			assertEquals(4, dao.findAllOpciones().size());
			dao.deleteOpcion(new Long(4));
			assertEquals(3, dao.findAllOpciones().size());
		}
		
		// Votación con todos los parámetros
		@Test
		public void test12Votacion() {
			
			Votacion v = new Votacion(new Date(), new Date(), "Referendum");
			dao.insertVotacion(v, null);
			assertEquals(4, dao.findAllVotaciones().size());
			assertEquals(v.getTipoVotacion(), dao.findVotacion(new Long(2))
					.getTipoVotacion());
		}
		
		// Votación sin fecha inicial
		
		@Test(expected = NullPointerException.class)
		public void test13Votacion(){
			
			Votacion v = new Votacion(null, new Date(), "Referendum");
			
			dao.insertVotacion(v, null);
			assertEquals(0, dao.findAllVotaciones().size());
		}
		
		// Votación sin fecha final
		
		@Test(expected = NullPointerException.class)
		public void test14Votacion(){
			
			Votacion v = new Votacion(new Date(), null, "Referendum");
			
			dao.insertVotacion(v, null);
			assertEquals(0, dao.findAllVotaciones().size());
			
		}
		
		// Votacion correcta de tipo Generales
		@Test
		public void test15Votacion() {

			dao.restoreDatabase();
			Votacion v = new Votacion(new Date(), new Date(), "Generales");
			dao.insertVotacion(v, null);
			assertEquals(5, dao.findAllVotaciones().size());
			assertEquals(v.getTipoVotacion(), dao.findVotacion(new Long(5))
					.getTipoVotacion());
		}
		
		@Test
		public void test16Votos() {

			dao = new ObjectDaoImpl();
			dao.insertVotos(new Votos("Web", new Long(1), 1, new Long(1), "AST001"));
			assertEquals(1, dao.findAllVotos().size());
			assertEquals("Web", dao.findVotos(new Long(1)).getTipoVoto());
			dao.insertVotos(new Votos("Fisico", new Long(1), 1, new Long(1), "AST001"));
			assertEquals(2, dao.findAllVotos().size());
			
		}
		
		@Test
		public void test17Votos() {
			es.uniovi.asw.physicalVotes.dBUpdate.WreportR report1 = 
					new es.uniovi.asw.physicalVotes.dBUpdate.WreportR(
							new es.uniovi.asw.physicalVotes.reportWriter.WreportP());
			Insert r1 = new InsertPhysicalR(new RVotes(),
					"src/test/resources/votacionesFisicas.xlsx");
			boolean exito = r1.addVoto(new InsertVotesP(report1));
			assertTrue(exito);
			assertEquals(4, dao.findAllVotos().size());
			
		}
		
		@Test
		public void test18Votos() {
			dao.restoreDatabase();
			
			es.uniovi.asw.physicalVotes.dBUpdate.WreportR report1 = 
					new es.uniovi.asw.physicalVotes.dBUpdate.WreportR(
							new es.uniovi.asw.physicalVotes.reportWriter.WreportP());
			Insert r1 = new InsertPhysicalR(new RVotes(),
					"src/test/resources/votacionesFisicasMAL.xlsx");
			boolean exito = r1.addVoto(new InsertVotesP(report1));
			assertEquals(false, exito);
			
					
		}
		
		@Test
		public void test19VirtualVotes() {
			//dao.restoreDatabase();
			Votante v = new Votante("90500084Y", "WEB", false, new Long(1));
			dao.insertVotante(v);
			iV.setVotante(v);
			assertEquals(v.getNif(), 
								iV.getTipoVoto(new InsertVirtualVotesP()).getNif());
			
		}
		
		
		@Test
		public void test20VirtualVotes() {
			
			Votante v = new Votante("90500084Y", "WEB", false, new Long(1));
			iV.setVotante(v);
			iV.setTypeVote(new InsertVirtualVotesP());		
			assertEquals(v.getNif(), 
					iV.getTipoVoto(new InsertVirtualVotesP()).getNif());
			
			
		}
		
		@Test
		public void test21VirtualVotes() {
			Votante v = new Votante("90500084Y", "WEB", false, new Long(1));
			iV.setVotante(v);
			iV.setTypeVote(new InsertVirtualVotesP());		
			Votos vs = new Votos(new Long (1), "WEB", new Long(1), 1, new Long(1), "13");
			iV.setVotos(vs);
			
			iV.getVoteInfo(new InsertVirtualVotesP());
			assertEquals(false, dao.findVotante("90500084Y").isEstado());
			
		}


}
