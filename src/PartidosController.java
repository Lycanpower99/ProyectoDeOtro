import java.util.ArrayList;

public class PartidosController {
	private BDController controladorBD;
	
	
	public PartidosController() {
		super();
		//Aquí instanciamos el objeto de tipo BDController
		this.controladorBD = new BDController();
	}
	
	public void listadoPartidosGanadosPor(String Equipo) {
		if(this.controladorBD.existeEquipo(Equipo)) {
			ArrayList<Partidos> partidosjugados = this.controladorBD.partidosGanadospPor(Equipo);
			
			for (int i = 0; i < partidosjugados.size(); i++) {
				String equipoLocal = partidosjugados.get(i).getEquipo_local();
				String ciudadLocal = this.controladorBD.buscarCiudadEquipo(equipoLocal);
				String equipoVisitante = partidosjugados.get(i).getEquipo_visitante();
				String ciudadVisitante = this.controladorBD.buscarCiudadEquipo(equipoVisitante);
				System.out.println("Temporada: " + partidosjugados.get(i).getTemporada() + " " + ciudadLocal + " " + equipoLocal + " " + partidosjugados.get(i).getPuntos_local() + ":" + partidosjugados.get(i).getPuntos_visitante() + ciudadVisitante + " " + equipoVisitante);
			}
		}else {
			System.out.println("********************************");
			System.out.println("El equipo introducido no existe");
			System.out.println("********************************");
		}
	}
	
	public void listadoPartidosEntreDosEquipos(String Equipo1, String Equipo2) {
		if(this.controladorBD.existeEquipo(Equipo1) && this.controladorBD.existeEquipo(Equipo2)) {
			ArrayList<Partidos> partidosjugados = this.controladorBD.partidosEntreEquipos(Equipo1, Equipo2);
			
			for (int i = 0; i < partidosjugados.size(); i++) {
				String equipoLocal = partidosjugados.get(i).getEquipo_local();
				String ciudadLocal = this.controladorBD.buscarCiudadEquipo(equipoLocal);
				String equipoVisitante = partidosjugados.get(i).getEquipo_visitante();
				String ciudadVisitante = this.controladorBD.buscarCiudadEquipo(equipoVisitante);
				System.out.println("Temporada: " + partidosjugados.get(i).getTemporada() + " " + ciudadLocal + " " + equipoLocal + " " + partidosjugados.get(i).getPuntos_local() + ":" + partidosjugados.get(i).getPuntos_visitante() + ciudadVisitante + " " + equipoVisitante);
			}
		}else {
			System.out.println("********************************");
			System.out.println("Uno de los dos equipos no existe");
			System.out.println("********************************");
		}
	}
}
