import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class BDController {
	private Connection conexion;
	
	public BDController() {
		super();
		try {
			this.conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/nba", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en la conexión a la Bade de Datos - BDController.Contructor_Vacio");
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	
	public boolean existeJugador(String nombre){
		boolean encontrado = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select nombre from jugadores where nombre like '" + nombre +"'" );
			if(rs.next()==true) {
				encontrado = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error consulta existeJugador - BDController");
		}
		
		return encontrado;
	}
	
	public boolean existeEquipo(String nombreEquipo){
		boolean encontrado = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select nombre from equipos where nombre like '" + nombreEquipo +"'" );
			if(rs.next()==true) {
				encontrado = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error consulta existeEquipo - BDController");
		}
		
		return encontrado;
	}
	
	public boolean existeEstadistica(int numJugador){
		boolean encontrado = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select jugador from estadisticas where jugador = '" + numJugador +"'" );
			if(rs.next()==true) {
				encontrado = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error consulta existeEstadistica - BDController");
		}
		
		return encontrado;
	}
	
	public boolean existeEstadisticaPorJugadoryTemporada(int numJugador, String temporada){
		boolean encontrado = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			//System.out.println("Select * from estadisticas where jugador = '" + numJugador +"' and temporada like '" + temporada +"'");
			ResultSet rs = miStatement.executeQuery("Select jugador from estadisticas where jugador = '" + numJugador +"' and temporada like '" + temporada +"'");
			if(rs.next()==true) {
				encontrado = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error consulta existeEstadisticaPorJugadoryTemporada - BDController");
		}
		return encontrado;
	}
	
	public void eliminarEstadistica(int codigo) {
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "delete from estadisticas where jugador = '" + codigo + "'";
			//System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error bdcontroller eliminarEstadistica");
		}
	}
	
	public int buscarUltimoCodigoJugador() {
		int codigo = 0;
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("SELECT MAX(codigo) FROM jugadores");
			if (rs.next()) {
				codigo = rs.getInt("MAX(codigo)");
			}
			rs.close();
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en bdcontroller en metodo buscarUltimoCodigoJugador - BDController");
		}
		return codigo;
	}
	
	public void eliminarJugador(int codigo) {
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "delete from jugadores where codigo = '" + codigo + "'";
			//System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error bdcontroller eliminarJugador");
		}
	}
	
	public int buscarCodigoJugador(String nombre) {
		int codigo = 0;
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select codigo from jugadores where nombre like '" + nombre + "'");
			if (rs.next()) {
				codigo = rs.getInt("codigo");
			}
			rs.close();
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en bdcontroller en metodo buscarCodigoJugador - BDController");
		}
		return codigo;
	}
	
	public String buscarCiudadEquipo(String nombre) {
		String ciudad = "";
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select ciudad from equipos where ciudad like '" + nombre + "'");
			if (rs.next()) {
				ciudad = rs.getString("ciudad");
			}
			rs.close();
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en buscarCiudadEquipo en metodo buscarCodigoJugador - BDController");
		}
		return ciudad;
	}
	
	public void altaJugador(Jugador jugador) {
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "INSERT INTO jugadores (codigo, nombre, procedencia, altura, peso, posicion, nombre_equipo) VALUES (" + jugador.getCodigo() + ", '" + jugador.getNombre() + "','" + jugador.getProcedencia() + "','" + jugador.getAltura() + "'," + jugador.getPeso() + ",'" + jugador.getPosicion() + "','" + jugador.getNombre_equipo() +"')";
			//System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en altaJugador - BDController");
		}
	}
	
	public void altaEstadistica(Estadistica estadistica) {
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "INSERT INTO estadisticas (temporada, jugador, Puntos_por_partido, Asistencias_por_partido, Tapones_por_partido, Rebotes_por_partido) VALUES ('" + estadistica.getTemporada() + "', '" + estadistica.getJugador() + "','" + (float)estadistica.getPuntos_por_partido() + "','" + (float)estadistica.getAsistencias_por_partido() + "'," + (float)estadistica.getTapones_por_partido() + ",'" + (float)estadistica.getRebotes_por_partido() + "')";
			//System.out.println("INSERT INTO estadisticas (temporada, jugador, Puntos_por_partido, Asistencias_por_partido, Tapones_por_partido, Rebotes_por_partido) VALUES (" + estadistica.getTemporada() + ", '" + estadistica.getJugador() + "','" + (float)estadistica.getPuntos_por_partido() + "','" + (float)estadistica.getAsistencias_por_partido() + "'," + (float)estadistica.getTapones_por_partido() + ",'" + (float)estadistica.getRebotes_por_partido() + "')");
			//System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en altaEstadistica - BDController");
		}
	}
	
	public ArrayList<Partidos> partidosEntreEquipos(String Equipo1, String Equipo2) {
		ArrayList<Partidos> partidosjugados = new ArrayList<Partidos>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("SELECT * FROM `partidos` WHERE (equipo_local LIKE '" + Equipo1 + "' and equipo_visitante LIKE '" + Equipo2 + "') or (equipo_local LIKE '" + Equipo2 + "' and equipo_visitante LIKE '" + Equipo1 + "')");
			while (rs.next()) {
				Partidos partido = new Partidos(rs.getInt("codigo"),rs.getString("equipo_local"),rs.getString("equipo_visitante"),rs.getInt("puntos_local"),rs.getInt("puntos_visitante"),rs.getString("temporada"));
				partidosjugados.add(partido);
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error en partidosEntreEquipos - BDController");
		}
		return partidosjugados;
	}
	
	public ArrayList<Partidos> equiposPerdidopartidoDife40() {
		ArrayList<Partidos> partidosBD = new ArrayList<Partidos>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("SELECT DISTINCT * FROM `partidos` WHERE ((puntos_local - puntos_visitante ) > 40) OR ((puntos_visitante - puntos_local ) > 40)");
			while (rs.next()) {
				Partidos partido = new Partidos(rs.getInt("codigo"),rs.getString("equipo_local"),rs.getString("equipo_visitante"),rs.getInt("puntos_local"),rs.getInt("puntos_visitante"),rs.getString("temporada"));
				partidosBD.add(partido);
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error en equiposPerdidopartidoDife40 - BDController");
		}
		return partidosBD;
	}
	
	public ArrayList<Equipo> datosEquipos_Nombre_Conferencia_Division(ArrayList<String> nombre_equipos) {
		ArrayList<Equipo> equiposBD = new ArrayList<Equipo>();
		try {
			Statement miStatement = this.conexion.createStatement();
			for (int i = 0; i < nombre_equipos.size(); i++) {
				ResultSet rs = miStatement.executeQuery("SELECT * from equipos where nombre = '" + nombre_equipos + "'" );
				if(rs.next()) {
					Equipo equipo = new Equipo(rs.getString("Nombre"),rs.getString("Ciudad"),rs.getString("Conferencia"),rs.getString("Division"));
					equiposBD.add(equipo);
				}
				rs.close();
				
			}
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error en datosEquipos_Nombre_Conferencia_Division - BDController");
		}
		return equiposBD;
	}
	
	public ArrayList<Equipo> equiposTotales() {
		ArrayList<Equipo> equiposBD = new ArrayList<Equipo>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("SELECT * from equipos");
			while (rs.next()) {
				Equipo equipo = new Equipo(rs.getString("Nombre"),rs.getString("Ciudad"),rs.getString("Conferencia"),rs.getString("Division"));
				equiposBD.add(equipo);
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error en equiposTotales - BDController");
		}
		return equiposBD;
	}
	
	public ArrayList<Partidos> partidosGanadospPor(String Equipo) {
		ArrayList<Partidos> partidosjugados = new ArrayList<Partidos>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("SELECT * FROM `partidos` WHERE (equipo_local LIKE '" + Equipo + "' and puntos_local > puntos_visitante) or (equipo_visitante LIKE '" + Equipo + "' and puntos_visitante > puntos_local)");
			while (rs.next()) {
				Partidos partido = new Partidos(rs.getInt("codigo"),rs.getString("equipo_local"),rs.getString("equipo_visitante"),rs.getInt("puntos_local"),rs.getInt("puntos_visitante"),rs.getString("temporada"));
				partidosjugados.add(partido);
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error en partidosEntreEquipos - BDController");
		}
		return partidosjugados;
	}
	
	public ArrayList<Equipo> equiposTotalesOrdenadosDivison() {
		ArrayList<Equipo> equiposBD = new ArrayList<Equipo>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("SELECT*FROM equipos order by division");
			while (rs.next()) {
				Equipo equipo = new Equipo(rs.getString("Nombre"),rs.getString("Ciudad"),rs.getString("Conferencia"),rs.getString("Division"));
				equiposBD.add(equipo);
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error en equiposTotalesOrdenadosDivison - BDController");
		}
		return equiposBD;
	}
	
	public ArrayList<Jugador> jugadoresTotales() {
		ArrayList<Jugador> jugadoresBD = new ArrayList<Jugador>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("SELECT * from jugadores");
			while (rs.next()) {
				Jugador jugador = new Jugador(rs.getInt("codigo"),rs.getString("Nombre"),rs.getString("Procedencia"),rs.getString("Altura"),rs.getInt("Peso"),rs.getString("Posicion"),rs.getString("Nombre_equipo"));
				jugadoresBD.add(jugador);
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error en jugadoresTotales - BDController");
		}
		return jugadoresBD;
	}
	
	public ArrayList<Jugador> listadoJugadoresEnConferenciaX(String conferencia) {
		ArrayList<Jugador> jugadoresBD = new ArrayList<Jugador>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("select * from jugadores where Nombre_equipo IN(SELECT Nombre FROM equipos WHERE conferencia = '" + conferencia + "')");
			while (rs.next()) {
				Jugador jugador = new Jugador(rs.getInt("codigo"),rs.getString("Nombre"),rs.getString("Procedencia"),rs.getString("Altura"),rs.getInt("Peso"),rs.getString("Posicion"),rs.getString("Nombre_equipo"));
				jugadoresBD.add(jugador);
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error en listadoJugadoresEnConferenciaX - BDController");
		}
		return jugadoresBD;
	}
	
	public ArrayList<Jugador> listadoJugadoresEnDivisionX(String division) {
		ArrayList<Jugador> jugadoresBD = new ArrayList<Jugador>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("select * from jugadores where Nombre_equipo IN(SELECT Nombre FROM equipos WHERE division = '" + division + "')");
			while (rs.next()) {
				Jugador jugador = new Jugador(rs.getInt("codigo"),rs.getString("Nombre"),rs.getString("Procedencia"),rs.getString("Altura"),rs.getInt("Peso"),rs.getString("Posicion"),rs.getString("Nombre_equipo"));
				jugadoresBD.add(jugador);
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error en listadoJugadoresEnDivisionX - BDController");
		}
		return jugadoresBD;
	}
	
	public ArrayList<Jugador> jugadoresFiltrosos(String procedencia, String conferencia, String division, String ciudad) {
		ArrayList<Jugador> jugadoresBD = new ArrayList<Jugador>();
		try {
			Statement miStatement = this.conexion.createStatement();
			//System.out.println("select * from jugadores where procedencia = '" + procedencia + "' and nombre_equipo in(select nombre from equipos where ciudad!= '" + ciudad + "' and conferencia= '"+ conferencia +"' and division= '" + division + "'");
			ResultSet rs = miStatement.executeQuery("select * from jugadores where procedencia = '" + procedencia + "' and nombre_equipo in(select nombre from equipos where ciudad!= '" + ciudad + "' and conferencia= '"+ conferencia +"' and division= '" + division + "')");
			while (rs.next()) {
				Jugador jugador = new Jugador(rs.getInt("codigo"),rs.getString("Nombre"),rs.getString("Procedencia"),rs.getString("Altura"),rs.getInt("Peso"),rs.getString("Posicion"),rs.getString("Nombre_equipo"));
				jugadoresBD.add(jugador);
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error en jugadoresFiltrosos - BDController");
		}
		return jugadoresBD;
	}
	
	public ArrayList<Estadistica> listadoDeTemporadasJugador(int idJugador) {
		ArrayList<Estadistica> estadisticaBD = new ArrayList<Estadistica>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("SELECT * FROM `estadisticas` WHERE jugador = " + idJugador);
			while (rs.next()) {
				Estadistica estadistica = new Estadistica(rs.getString("temporada"), rs.getInt("jugador"), rs.getDouble("Puntos_por_partido"), rs.getDouble("Asistencias_por_partido"), rs.getDouble("Tapones_por_partido"), rs.getDouble("Rebotes_por_partido"));
				estadisticaBD.add(estadistica);
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error en listadoDeTemporadasJugador - BDController");
		}
		return estadisticaBD;
	}
	
}
