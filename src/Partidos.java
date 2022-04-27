
public class Partidos {
	private int codigo;
	private String equipo_local;
	private String equipo_visitante;
	private int puntos_local;
	private int puntos_visitante;
	private String temporada;
	
	public Partidos() {}
	public Partidos(int codigo, String equipo_local, String equipo_visitante, int puntos_local, int puntos_visitante,
			String temporada) {
		super();
		this.codigo = codigo;
		this.equipo_local = equipo_local;
		this.equipo_visitante = equipo_visitante;
		this.puntos_local = puntos_local;
		this.puntos_visitante = puntos_visitante;
		this.temporada = temporada;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getEquipo_local() {
		return equipo_local;
	}
	public void setEquipo_local(String equipo_local) {
		this.equipo_local = equipo_local;
	}
	public String getEquipo_visitante() {
		return equipo_visitante;
	}
	public void setEquipo_visitante(String equipo_visitante) {
		this.equipo_visitante = equipo_visitante;
	}
	public int getPuntos_local() {
		return puntos_local;
	}
	public void setPuntos_local(int puntos_local) {
		this.puntos_local = puntos_local;
	}
	public int getPuntos_visitante() {
		return puntos_visitante;
	}
	public void setPuntos_visitante(int puntos_visitante) {
		this.puntos_visitante = puntos_visitante;
	}
	public String getTemporada() {
		return temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

}
