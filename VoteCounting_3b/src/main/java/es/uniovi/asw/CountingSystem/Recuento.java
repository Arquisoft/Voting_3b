package es.uniovi.asw.CountingSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.uniovi.asw.dbManagement.model.ColegioData;
import es.uniovi.asw.dbManagement.model.VotoData;

public class Recuento {

	static List<VotoData> votosTotales = null;
	static int votosManuales = 0;
	static int censadosTotales = 0;
	static List<ColegioData> colegios = null;
	static Map<String, Map<String, Integer>> mapaVotosTotales = new HashMap<String, Map<String, Integer>>();
	static Map<String, Integer> censadosComunidades = new HashMap<String, Integer>();
	static Map<String, Integer> censoPorColegio = null;
	static Map<String, Double> participacion = new HashMap<String, Double>();
	static Map<String, Integer> censadosCiudades = new HashMap<String, Integer>();
	static Map<String, Map<String, Integer>> votosPorCiudad = new HashMap<String, Map<String, Integer>>();
	static Map<String, Map<String, Integer>> votosPorComunidad = new HashMap<String, Map<String, Integer>>();
	private static List<String> ciudades = null;
	private static List<String> comunidades = null;

	public static List<ColegioData> getColegios() {
		if (colegios == null)
			colegios = Factories.services.CensusInfo().getColegios();
		return colegios;
	}

	public static Map<String, Map<String, Integer>> getMapaVotosTotales() {
		Map<String, Integer> cantidad = new HashMap<String, Integer>();
		if (mapaVotosTotales.isEmpty()) {
			List<VotoData> votos = getVotosTotales();
			for (VotoData voto : votos) {
				Integer votosSi=0;
				Integer votosNo=0;
				Integer votosBlanco=0;
				if (voto.getOpcion().compareTo(1L) == 0) {
					votosSi = voto.getTotalVotos();
					if (cantidad.containsKey("SI"))
						cantidad.replace("SI", cantidad.get("SI"), cantidad.get("SI") + votosSi);
					else
						cantidad.put("SI", votosSi);
				} else if (voto.getOpcion().compareTo(2L) == 0) {
					votosNo = voto.getTotalVotos();
					if (cantidad.containsKey("NO"))
						cantidad.replace("NO", cantidad.get("NO"), cantidad.get("NO") + votosNo);
					else
						cantidad.put("NO", votosNo);
				} else {
					votosBlanco = voto.getTotalVotos();
					if (cantidad.containsKey("BLANCO"))
						cantidad.replace("BLANCO", cantidad.get("BLANCO"), cantidad.get("BLANCO") + votosBlanco);
					else
						cantidad.put("BLANCO", votosBlanco);
				}

			}
			mapaVotosTotales.put("España", cantidad);
		}
		return mapaVotosTotales;

	}

	public static List<VotoData> getVotosTotales() {
		if (votosTotales == null) {
			votosTotales = new ArrayList<VotoData>();
			votosTotales = Factories.services.VotesInfo().getVotosTotales();

		}
		return votosTotales;
	}

	public static int getCensadosTotales() {
		if (censadosTotales == 0) {
			censadosTotales = Factories.services.CensusInfo().getTotalCenso();
		}
		return censadosTotales;
	}

	public static Integer getCensadosComunidad(String comunidad) {
		if (!censadosComunidades.containsKey(comunidad)) {
			int vot = Factories.services.CensusInfo().getVotantesPorComunidad(comunidad);
			censadosComunidades.put(comunidad, vot);
		}
		return censadosComunidades.get(comunidad);

	}

	public static Integer getCensadosCiudad(String ciudad) {
		Integer vot;
		if (!censadosCiudades.containsKey(ciudad)) {
			vot = Factories.services.CensusInfo().getVotantesPorCiudad(ciudad);
			censadosCiudades.put(ciudad, vot);
		}
		return censadosCiudades.get(ciudad);
	}

	public static Double getParticipacion(String lugar) {
		
		if (participacion.get(lugar) == null) {
			Integer votantesPotenciales = null;
			Integer contador = 0;
			double p = 0;
			Map<String, Integer> vs = new HashMap<String, Integer>();
			if (lugar == "España") {
				vs = Recuento.getMapaVotosTotales().get("España");
				votantesPotenciales = Recuento.getCensadosTotales();
			} else {
				if (Recuento.getCiudades().contains(lugar)) {
					vs = Recuento.getVotosPorCiudad(lugar);
					votantesPotenciales = getCensadosCiudad(lugar);
				}
				if (Recuento.getComunidades().contains(lugar)) {
					vs = Recuento.getVotosPorComunidad(lugar);
					votantesPotenciales = getCensadosComunidad(lugar);
				}
			}
			for (Integer v : vs.values())
				contador += v;
			if(contador > 0){
				p = (contador * 1.0 / votantesPotenciales);
			}else {
				p = 0;
			}
			

			participacion.put(lugar, p * 100);
		}
		return Math.floor(participacion.get(lugar)*100)/100;

	}

	public static Map<String, Integer> getVotosPorCiudad(String lugar) {
		Map<String, Integer> cantidad = new HashMap<String, Integer>();
		List<ColegioData> colegiosCiudad = null;
		if (!votosPorCiudad.containsKey(lugar)) {
			List<VotoData> votos = new ArrayList<VotoData>(getVotosTotales());
			colegiosCiudad = new ArrayList<ColegioData>(getColegios());
			colegiosCiudad.removeIf(colegio -> !colegio.getCiudad().equals(lugar));
			List<VotoData> aux = new ArrayList<VotoData>();
			for (ColegioData colegio : colegiosCiudad)
				votos.forEach(v -> {
					if (v.getCodColegioElectoral().equals(colegio.getCodColegioElectoral()))
						aux.add(v);
				});
			for (VotoData voto : aux) {
				Integer votosSi=0;
				Integer votosNo=0;
				Integer votosBlanco=0;
				if (voto.getOpcion().compareTo(1L) == 0) {
					votosSi = voto.getTotalVotos();
					if (cantidad.containsKey("SI"))
						cantidad.replace("SI", cantidad.get("SI"), cantidad.get("SI") + votosSi);
					else
						cantidad.put("SI", votosSi);
				} else if (voto.getOpcion().compareTo(2L) == 0) {
					votosNo = voto.getTotalVotos();
					if (cantidad.containsKey("NO"))
						cantidad.replace("NO", cantidad.get("NO"), cantidad.get("NO") + votosNo);
					else
						cantidad.put("NO", votosNo);
				} else {
					votosBlanco = voto.getTotalVotos();
					if (cantidad.containsKey("BLANCO"))
						cantidad.replace("BLANCO", cantidad.get("BLANCO"), cantidad.get("BLANCO") + votosBlanco);
					else
						cantidad.put("BLANCO", votosBlanco);

				}

			}
			votosPorCiudad.put(lugar, cantidad);
		}

		return votosPorCiudad.get(lugar);

	}

	public static Map<String, Integer> getVotosPorComunidad(String lugar) {
		Map<String, Integer> cantidad = new HashMap<String, Integer>();
		if (!votosPorComunidad.containsKey(lugar)) {
			List<VotoData> votos = new ArrayList<VotoData>(getVotosTotales());
			List<ColegioData> colegiosComunidad = new ArrayList<ColegioData>(getColegios());
			colegiosComunidad.removeIf(colegio -> !colegio.getComunidadAutonoma().equals(lugar));
			List<VotoData> aux = new ArrayList<VotoData>();
			for (ColegioData colegio : colegiosComunidad)
				votos.forEach(v -> {
					if (v.getCodColegioElectoral().equals(colegio.getCodColegioElectoral()))
						aux.add(v);
				});
			for (VotoData voto : aux) {
				Integer votosSi=0;
				Integer votosNo=0;
				Integer votosBlanco=0;
				if (voto.getOpcion().compareTo(1L) == 0) {
					votosSi = voto.getTotalVotos();
					if (cantidad.containsKey("SI"))
						cantidad.replace("SI", cantidad.get("SI"), cantidad.get("SI") + votosSi);
					else
						cantidad.put("SI", votosSi);
				} else if (voto.getOpcion().compareTo(2L) == 0) {
					votosNo = voto.getTotalVotos();
					if (cantidad.containsKey("NO"))
						cantidad.replace("NO", cantidad.get("NO"), cantidad.get("NO") + votosNo);
					else
						cantidad.put("NO", votosNo);
				} else {
					votosBlanco = voto.getTotalVotos();
					if (cantidad.containsKey("BLANCO"))
						cantidad.replace("BLANCO", cantidad.get("BLANCO"), cantidad.get("BLANCO") + votosBlanco);
					else
						cantidad.put("BLANCO", votosBlanco);

				}

			}
			votosPorComunidad.put(lugar, cantidad);
		}

		return votosPorComunidad.get(lugar);

	}

	public static List<String> getCiudades() {
		if (ciudades == null)
			ciudades = Factories.services.CensusInfo().getCiudades();
		return new ArrayList<String>(ciudades);
	}

	public static List<String> getComunidades() {
		if (comunidades == null)
			comunidades = Factories.services.CensusInfo().getComunidades();
		return new ArrayList<String>(comunidades);
	}

}
