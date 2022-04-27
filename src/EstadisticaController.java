
public class EstadisticaController {
	private BDController controladorBD;
	
	
	public EstadisticaController() {
		super();
		//Aqu� instanciamos el objeto de tipo BDController
		this.controladorBD = new BDController();
	}
	
	
	public boolean existeEstadistica(int codJugador) {
		boolean existe = false;
		if(this.controladorBD.existeEstadistica(codJugador) == true) {
			existe = true;
		}
		return existe;
	}
	
	public boolean existeEstadisticaPorJugadorYtemporada(Estadistica estadistica) {
		boolean existe = false;
		if(this.controladorBD.existeEstadisticaPorJugadoryTemporada(estadistica.getJugador(),estadistica.getTemporada())==true) {
			existe = true;
		}
		return existe;
	}
	
	public void eliminarEstadisticas(int codJugador) {
		if(existeEstadistica(codJugador) == true) {
			this.controladorBD.eliminarEstadistica(codJugador);
			System.out.println("*********************************************************************");
			System.out.println("El jugador ten�a estadisticas y se han eliminado de la Base de Datos");
			System.out.println("*********************************************************************");
		}else {
			System.err.println("****************************************************************");
			System.err.println("Este jugador no ten�a estad�sticas");
			System.err.println("****************************************************************");
		}
	}
	
	public void anadirEstadisticas(Estadistica estadistica) {
		if(existeEstadisticaPorJugadorYtemporada(estadistica) == false) {
			this.controladorBD.altaEstadistica(estadistica);
			System.out.println("*********************************************************************");
			System.out.println("Operaci�n completada - Se han dado de alta las estad�sticas correctamente");
			System.out.println("*********************************************************************");
		}else {
			System.err.println("****************************************************************");
			System.err.println("Este jugador ya tiene estad�sticas para la temporada insertada");
			System.err.println("****************************************************************");
		}
	}
}
