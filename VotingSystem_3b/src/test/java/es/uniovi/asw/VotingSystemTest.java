package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import es.uniovi.asw.dbUpdate.persistence.Jdbc;
import es.uniovi.asw.dbUpdate.persistence.PersistenceFactory;
import es.uniovi.asw.electors.dbUpdate.ConfigP;
import es.uniovi.asw.electors.dbUpdate.InsertConfig;
import es.uniovi.asw.electors.dbUpdate.WreportR;
import es.uniovi.asw.electors.electorsConfig.ConfigR;
import es.uniovi.asw.electors.reportWriter.WreportP;
import es.uniovi.asw.model.Censos;
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
		Jdbc.restoreDatabase();
	}

	@After
	public void finalize() {
		Jdbc.restoreDatabase();
	}

	@Test
	public void test01Colegio() {
		dao.insertColegio(new ColegioElectoral("4", "Barrio 2", "Gijon", "Asturies"));
		assertEquals(1,dao.findAllColegios().size()); 
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
			List<Votacion> vs = dao.findAllVotaciones();
			assertEquals(1, vs.size());
			
			assertEquals(v.getTipoVotacion(), vs.get(0).getTipoVotacion());
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
			Votacion v = new Votacion(new Date(), new Date(), "General");
			InsertConfig insert = new ConfigP(new WreportR(new WreportP()));
			insert.insert(v);
			List<Votacion> vs = dao.findAllVotaciones();
			Long id = vs.get(0).getId();
			assertEquals(1, vs.size());
			assertEquals(v.getTipoVotacion(), dao.findVotacion(id).getTipoVotacion());
		}

		// Votación sin fecha final

		public void test09Electors() {
			Votacion v = new Votacion(new Date(), new Date(), "");
			InsertConfig insert = new ConfigP(new WreportR(new WreportP()));
			insert.insert(v);

			assertEquals(0, dao.findAllVotaciones().size());
		}

		public void test10Electors() {
			VotacionForm v = new VotacionForm("01/01/2016 10:00","02/01/2016 10:00", "General");
			new ConfigR(v).addVotacion(new ConfigP(new WreportR(new WreportP())));

			assertEquals(2, dao.findAllVotaciones().size());
			assertEquals(v.getTipoVotacion(), dao.findVotacion(new Long(1)).getTipoVotacion());
		}
		
		@Test
		public void test11Opcion() {	
			Votacion v = new Votacion(new Date(), new Date(), "General");
			InsertConfig insert = new ConfigP(new WreportR(new WreportP()));
			insert.insert(v);
			List<Votacion> vs = dao.findAllVotaciones();
			assertEquals(0, dao.findAllOpciones().size()); //3 opciones del script
			dao.insertOpcion(new Opcion(1L, "Opcion 2", vs.get(0).getId()));
			assertEquals(1, dao.findAllOpciones().size());
			dao.deleteOpcion(1L);
			assertEquals(0, dao.findAllOpciones().size());
		}
		
		// Votación con todos los parámetros
		@Test
		public void test12Votacion() {
			
			Votacion v = new Votacion(new Date(), new Date(), "Referendum");
			dao.insertVotacion(v, null);
			List<Votacion> vs = dao.findAllVotaciones();
			assertEquals(1, vs.size());
			assertEquals(v.getTipoVotacion(), dao.findVotacion(vs.get(0).getId())
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

			Votacion v = new Votacion(new Date(), new Date(), "Generales");
			dao.insertVotacion(v, null);
			List<Votacion> vs = dao.findAllVotaciones();
			assertEquals(1, vs.size());
			assertEquals(v.getTipoVotacion(), dao.findVotacion(vs.get(0).getId())
					.getTipoVotacion());
		}
		
		@Test
		public void test16Votos() {
			
			dao = new ObjectDaoImpl();
			dao.insertColegio(new ColegioElectoral("AST001", "Barrio 2", "Gijon", "Asturies"));
			Votacion v = new Votacion(new Date(), new Date(), "General");
			InsertConfig insert = new ConfigP(new WreportR(new WreportP()));
			insert.insert(v);
			List<Votacion> vs = dao.findAllVotaciones();
			dao.insertOpcion(new Opcion(1L, "Opcion 2", vs.get(0).getId()));
			List<Opcion> ops = dao.findAllOpciones();
			dao.insertVotos(new Votos("Web", ops.get(0).getId(), 1, vs.get(0).getId(), "AST001"));
			assertEquals(1, dao.findAllVotos().size());
			List<Votos> votos = dao.findAllVotos();
			assertEquals("Web", dao.findVotos(votos.get(0).getId()).getTipoVoto());
			assertEquals(1, dao.findAllVotos().size());
			
		}
		
		@Test
		public void test17Votos() {
			/*
			es.uniovi.asw.physicalVotes.dBUpdate.WreportR report1 = 
					new es.uniovi.asw.physicalVotes.dBUpdate.WreportR(
							new es.uniovi.asw.physicalVotes.reportWriter.WreportP());
			Insert r1 = new InsertPhysicalR(new RVotes(),
					"src/test/resources/votacionesFisicas.xlsx");
			boolean exito = r1.addVoto(new InsertVotesP(report1));
			assertTrue(exito);
			assertEquals(4, dao.findAllVotos().size());
			*/
			
		}
		
		@Test
		public void test18Votos() {
			
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
			Votacion votacion = new Votacion(new Date(), new Date(), "Referendum");
			dao.insertVotacion(votacion, null);
			List<Votacion> vs = dao.findAllVotaciones();
			dao.insertCensos(new Censos("Prueba", "90500084Y", "prueba", "AST001", "prueba"));
			Votante v = new Votante("90500084Y", "WEB", false, vs.get(0).getId());
			dao.insertVotante(v);
			iV.setVotante(v);
			assertEquals(v.getNif(), 
								iV.getTipoVoto(new InsertVirtualVotesP()).getNif());
			
		}
		
		
		@Test
		public void test20VirtualVotes() {
			Votacion votacion = new Votacion(new Date(), new Date(), "Referendum");
			dao.insertVotacion(votacion, null);
			List<Votacion> vs = dao.findAllVotaciones();
			dao.insertCensos(new Censos("Prueba", "90500084Y", "prueba", "AST001", "prueba"));
			Votante v = new Votante("90500084Y", "WEB", false,vs.get(0).getId());

			iV.setVotante(v);
			iV.setTypeVote(new InsertVirtualVotesP());		
			assertEquals(v.getNif(), 
					iV.getTipoVoto(new InsertVirtualVotesP()).getNif());
			
			
		}
		
		@Test
		public void test21VirtualVotes() {
			Votacion votacion = new Votacion(new Date(), new Date(), "Referendum");
			dao.insertVotacion(votacion, null);
			List<Votacion> votaciones = dao.findAllVotaciones();
			
			dao.insertOpcion(new Opcion(1L, "Opcion 2", votaciones.get(0).getId()));
			List<Opcion> ops = dao.findAllOpciones();
			dao.insertCensos(new Censos("Prueba", "90500084Y", "prueba", "AST001", "prueba"));
			Votante v = new Votante("90500084Y", "WEB", false,votaciones.get(0).getId());
			iV.setVotante(v);
			iV.setTypeVote(new InsertVirtualVotesP());	
			Votos vs = new Votos(1L, "WEB", ops.get(0).getId(), 1,votaciones.get(0).getId(), "AST001");
			iV.setVotos(vs);
			
			iV.getVoteInfo(new InsertVirtualVotesP());
			assertEquals(false, dao.findVotante("90500084Y").isEstado());
			
		}


}
