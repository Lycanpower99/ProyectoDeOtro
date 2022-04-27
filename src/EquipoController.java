import java.util.ArrayList;

public class EquipoController {
	private BDController controladorBD;
	
	
	public EquipoController() {
		super();
		//Aquí instanciamos el objeto de tipo BDController
		this.controladorBD = new BDController();
	}
	
	public boolean existeEquipo(String nombreEquipo) {
		boolean existe = false;
		if(this.controladorBD.existeEquipo(nombreEquipo) == true) {
			existe = true;
		}
		return existe;
	}
	
	public ArrayList<Equipo> listadoEquipos() {
		ArrayList<Equipo> equiposTotales = new ArrayList<Equipo>();
		equiposTotales = this.controladorBD.equiposTotales();
		return equiposTotales;
	}
	
	public ArrayList<Equipo> listadoEquiposOrdenadosDivision() {
		ArrayList<Equipo> equiposTotales = new ArrayList<Equipo>();
		equiposTotales = this.controladorBD.equiposTotalesOrdenadosDivison();
		return equiposTotales;
	}
	
	public void equiposPerdidosDiferencia40() {
		ArrayList<Partidos> partidos= this.controladorBD.equiposPerdidopartidoDife40();
		ArrayList<String> equiposManejados = new ArrayList<String>();
		for (int i = 0; i < partidos.size(); i++) {
			if (partidos.get(i).getPuntos_local() > partidos.get(i).getPuntos_visitante()) {
				if (!equiposManejados.contains(partidos.get(i).getEquipo_local())) {
					equiposManejados.add(partidos.get(i).getEquipo_local());
				}
			}else {
				if (!equiposManejados.contains(partidos.get(i).getEquipo_visitante())) {
					equiposManejados.add(partidos.get(i).getEquipo_visitante());
				}
			}
		}
		ArrayList<Equipo> equiposTotales = this.controladorBD.datosEquipos_Nombre_Conferencia_Division(equiposManejados);
		for (int i = 0; i < equiposTotales.size(); i++) {
			System.out.println(equiposTotales.get(i).getCiudad() + " " + equiposTotales.get(i).getNombre() + " Conferencia: " + equiposTotales.get(i).getConferencia() + " - División: " + equiposTotales.get(i).getDivision());
		}
	}
}
