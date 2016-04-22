package es.uniovi.asw;

import es.uniovi.asw.CountingSystem.ServicesFactory;
import es.uniovi.asw.CountingSystem.impl.H2ServicesFactory;
import es.uniovi.asw.dbManagement.PersistenceFactory;
import es.uniovi.asw.dbManagement.impl.H2PersistenceFactory;

public class Factories {
	
	public static ServicesFactory services = new H2ServicesFactory();
	public static PersistenceFactory persistence = new H2PersistenceFactory();

}
