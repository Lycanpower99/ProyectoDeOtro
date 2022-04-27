import java.util.ArrayList;

public class jugadorController {
private BDController controladorBD;
	
	public jugadorController() {
		super();
		//Aquí instanciamos el objeto de tipo BDController
		this.controladorBD = new BDController();
	}
	
	public void darAltaJugador(Jugador jugador) {
		int cod = 0;
		if(existeJugador(jugador.getNombre()) == true) {
			System.err.println("****************************************************************");
			System.err.println("Operación Cancelada - Este jugador ya existe en la Base de Datos");
			System.err.println("****************************************************************");
		}else {
			if (this.controladorBD.existeEquipo(jugador.getNombre_equipo())) {
				this.controladorBD.altaJugador(jugador);
				System.out.println("*********************************************************************");
				System.out.println("Operación Exitosa - El jugador se ha dado de alta en la Base de Datos");
				System.out.println("*********************************************************************");
			}else {
				System.err.println("****************************************************************");
				System.err.println("Operación Cancelada - Este equipo no existe en la Base de Datos");
				System.err.println("****************************************************************");
			}
		}
	}
	
	public void eliminarJugador(String nombre) {
		int cod = 0;
		if(existeJugador(nombre) == true) {
			this.controladorBD.eliminarJugador(this.dameCodigoJugador(nombre));
			System.out.println("*********************************************************************");
			System.out.println("Operación Exitosa - El jugador se ha eliminado de la Base de Datos");
			System.out.println("*********************************************************************");
		}else {
			System.err.println("****************************************************************");
			System.err.println("Operación Cancelada - Este jugador no existe en la Base de Datos");
			System.err.println("****************************************************************");
		}
	}
	
	public boolean existeJugador(String nombre) {
		boolean existe = false;
		if(this.controladorBD.existeJugador(nombre) == true) {
			existe = true;
		}
		return existe;
	}
	
	public int dameCodigoJugador(String nombreJugador) {
		int cod = this.controladorBD.buscarCodigoJugador(nombreJugador);
		return cod;
	}
	
	public int dameUltimoCodigoJugador() {
		int cod = this.controladorBD.buscarUltimoCodigoJugador();
		return cod;
	}
	
	public ArrayList<Jugador> listadoJugadores() {
		ArrayList<Jugador> jugadoresTotales = new ArrayList<Jugador>();
		jugadoresTotales = this.controladorBD.jugadoresTotales();
		return jugadoresTotales;
	}
	
	public void listadoJugadoresConTemporadasEnConferencias(String conferencia) {
		ArrayList<Jugador> jugadoresBD = this.controladorBD.listadoJugadoresEnConferenciaX(conferencia);
		
		for (int i = 0; i < jugadoresBD.size(); i++) {
			ArrayList<Estadistica> estadisticas = this.controladorBD.listadoDeTemporadasJugador(jugadoresBD.get(i).getCodigo());
			this.imprimirDatosJugadorTipo1(jugadoresBD.get(i));
			this.imprimirTemporadasJugador(estadisticas);
		}
	}
	
	public void listadoJugadoresConTemporadasEnDivision(String division) {
		ArrayList<Jugador> jugadoresBD = this.controladorBD.listadoJugadoresEnDivisionX(division);
		for (int i = 0; i < jugadoresBD.size(); i++) {
			ArrayList<Estadistica> estadisticas = this.controladorBD.listadoDeTemporadasJugador(jugadoresBD.get(i).getCodigo());
			this.imprimirDatosJugadorTipo1(jugadoresBD.get(i));
			this.imprimirTemporadasJugador(estadisticas);
		}
	}
	
	public void imprimirTemporadasJugador(ArrayList<Estadistica> estadisticas) {
		for (int j = 0; j < estadisticas.size(); j++) {
			System.out.println("	Temporada " + estadisticas.get(j).getTemporada() + " - " + estadisticas.get(j).getPuntos_por_partido() + "ppp - " + estadisticas.get(j).getAsistencias_por_partido() + "app - " + estadisticas.get(j).getRebotes_por_partido() + "rpp - " + estadisticas.get(j).getTapones_por_partido() + "tpp");
		}
	}
	
	public void imprimirDatosJugadorTipo1(Jugador jugador) {
		System.out.println("*******************************************************************************************************************************");
		System.out.println(jugador.getNombre() + " - " + jugador.getNombre_equipo() + " - " + jugador.getAltura() + " pies - " + jugador.getPeso() + " libras - " + jugador.getPosicion());
	}
	
	
	
	public void listadoJugadoresConMuchosFiltros(String procedencia, String conferencia, String division, String ciudad) {
		ArrayList<Jugador> jugadoresBD = this.controladorBD.jugadoresFiltrosos(procedencia, conferencia, division, ciudad);
		for (int i = 0; i < jugadoresBD.size(); i++) {
			System.out.println("*******************************************************************************************************************************");
			System.out.println("Código: " + jugadoresBD.get(i).getCodigo() + " | Nombre: " + jugadoresBD.get(i).getNombre() + " | Procedencia: " + jugadoresBD.get(i).getProcedencia() + " | Altura: " + jugadoresBD.get(i).getAltura() + " | Peso: " + jugadoresBD.get(i).getPeso() + " | Posicion: " + jugadoresBD.get(i).getPosicion() + " | Nombre del equipo: " + jugadoresBD.get(i).getNombre_equipo());
		}
	}
	
}
