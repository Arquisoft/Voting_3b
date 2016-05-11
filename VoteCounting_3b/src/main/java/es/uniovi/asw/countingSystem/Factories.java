package es.uniovi.asw.countingSystem;

import es.uniovi.asw.countingSystem.impl.H2ServicesFactory;
import es.uniovi.asw.dbManagement.PersistenceFactory;
import es.uniovi.asw.dbManagement.impl.H2PersistenceFactory;

public class Factories {
	
	public static ServicesFactory services = new H2ServicesFactory();
	public static PersistenceFactory persistence = new H2PersistenceFactory();

}
