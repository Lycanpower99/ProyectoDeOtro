
public class Estadistica {
	private String temporada;
	private int jugador;
	private double puntos_por_partido;
	private double asistencias_por_partido;
	private double tapones_por_partido;
	private double rebotes_por_partido;
	
	public Estadistica() {}
	public Estadistica(String temporada, int jugador, double puntos_por_partido, double asistencias_por_partido,
			double tapones_por_partido, double rebotes_por_partido) {
		super();
		this.temporada = temporada;
		this.jugador = jugador;
		this.puntos_por_partido = puntos_por_partido;
		this.asistencias_por_partido = asistencias_por_partido;
		this.tapones_por_partido = tapones_por_partido;
		this.rebotes_por_partido = rebotes_por_partido;
	}
	
	public String getTemporada() {
		return temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	public int getJugador() {
		return jugador;
	}
	public void setJugador(int jugador) {
		this.jugador = jugador;
	}
	public double getPuntos_por_partido() {
		return puntos_por_partido;
	}
	public void setPuntos_por_partido(double puntos_por_partido) {
		this.puntos_por_partido = puntos_por_partido;
	}
	public double getAsistencias_por_partido() {
		return asistencias_por_partido;
	}
	public void setAsistencias_por_partido(double asistencias_por_partido) {
		this.asistencias_por_partido = asistencias_por_partido;
	}
	public double getTapones_por_partido() {
		return tapones_por_partido;
	}
	public void setTapones_por_partido(double tapones_por_partido) {
		this.tapones_por_partido = tapones_por_partido;
	}
	public double getRebotes_por_partido() {
		return rebotes_por_partido;
	}
	public void setRebotes_por_partido(double rebotes_por_partido) {
		this.rebotes_por_partido = rebotes_por_partido;
	}
	
	
}
