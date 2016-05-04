package es.uniovi.asw.persistence.config;

import java.util.List;

import es.uniovi.asw.electors.dbUpdate.WriteReport;
import es.uniovi.asw.model.Censos;
import es.uniovi.asw.model.ColegioElectoral;
import es.uniovi.asw.model.Opcion;
import es.uniovi.asw.model.Votacion;
import es.uniovi.asw.model.Votante;
import es.uniovi.asw.model.Votos;

public interface ObjectDao {

	// ColegioElectoral
	public void insertColegio(ColegioElectoral v);
	
	public ColegioElectoral findColegio(String codigo);

	public List<ColegioElectoral> findAllColegios();

	// Censos
	public void insertCensos(Censos c);

	public Censos findCensosByNif(String nif);

	// Votacion
	public void insertVotacion(Votacion v, WriteReport r);

	public Votacion findVotacion(Long id);

	public List<Votacion> findAllVotaciones();

	// Opcion
	public void insertOpcion(Opcion o);

	public void deleteOpcion(Long id);

	public List<Opcion> findAllOpciones();

	// Votos

	boolean insertVotos(Votos v);

	Votos findVotos(Long id);

	boolean updateVotos(Votos v);

	// Votante
	boolean insertVotante(Votante v);

	boolean setTipoVoto(Votante v);

	boolean setEstadoVoto(Votante v);

	Votante findVotante(String NIF);

	List<Votos> findAllVotos();

	Censos findByEmailAndPassword(String user, String password) ;

	

}
