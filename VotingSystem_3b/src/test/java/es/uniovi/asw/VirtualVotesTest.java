package es.uniovi.asw;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.asw.model.Votante;
import es.uniovi.asw.model.Votos;
import es.uniovi.asw.persistence.config.ObjectDaoImpl;
import es.uniovi.asw.virtualVotes.dBUpdate.InsertVirtualVotesP;
import es.uniovi.asw.virtualVotes.virtualVotesConfig.InsertVirtual;
import es.uniovi.asw.virtualVotes.virtualVotesConfig.InsertVirtualR;

public class VirtualVotesTest {
	
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
	public void test() {
		//dao.restoreDatabase();
		Votante v = new Votante("123", "WEB", false, new Long(1));
		dao.insertVotante(v);
		iV.setVotante(v);
		assertEquals(v.getNif(), 
							iV.getTipoVoto(new InsertVirtualVotesP()).getNif());
		
	}
	
	
	@Test
	public void test2() {
		
		Votante v = new Votante("123", "WEB", false, new Long(1));
		iV.setVotante(v);
		iV.setTypeVote(new InsertVirtualVotesP());		
		assertEquals(v.getNif(), 
				iV.getTipoVoto(new InsertVirtualVotesP()).getNif());
		
		
	}
	
	@Test
	public void test3() {
		Votante v = new Votante("123", "WEB", false, new Long(1));
		iV.setVotante(v);
		iV.setTypeVote(new InsertVirtualVotesP());		
		Votos vs = new Votos(new Long (1), "WEB", new Long(1), 1, new Long(1), "13");
		iV.setVotos(vs);
		
		iV.getVoteInfo(new InsertVirtualVotesP());
		assertEquals(false, dao.findVotante("123").isEstado());
		
		
		
		
	}
	

}
