package es.uniovi.asw.countingSystem.impl.classes;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.countingSystem.Factories;
import es.uniovi.asw.dbManagement.model.ColegioData;
import es.uniovi.asw.dbManagement.model.PersonaData;

public class PersonasCenso {

	public int findAll() {
		return Factories.persistence.census().getPersonas().size();
	}

	public int findByComunidad(String comunidad) {
		List<PersonaData> personas = Factories.persistence.census().getPersonas();
		List<ColegioData> colegios = Factories.persistence.census().getColegios();
		List<String> codigos = new ArrayList<String>();
		int contador = 0;
		for (ColegioData c : colegios)
			if (c.getComunidadAutonoma().equals(comunidad))
				codigos.add(c.getCodColegioElectoral());
		for (PersonaData p : personas)
			if (codigos.contains(p.getCodColegioElectoral()))
				contador++;
		return contador;

	}

	public Integer findByCiudad(String ciudad) {
		List<PersonaData> personas = Factories.persistence.census().getPersonas();
		List<ColegioData> colegios = Factories.persistence.census().getColegios();
		List<String> codigos = new ArrayList<String>();
		int contador = 0;
		for (ColegioData c : colegios)
			if (c.getCiudad().equals(ciudad))
				codigos.add(c.getCodColegioElectoral());
		for (PersonaData p : personas)
			if (codigos.contains(p.getCodColegioElectoral()))
				contador++;
		return contador;
	}

}
