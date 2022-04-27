import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jugadorController jugaController = new jugadorController();
		EquipoController equipoController = new EquipoController();
		EstadisticaController estadiController = new EstadisticaController();

		Scanner sc = new Scanner(System.in);
		Scanner sn = new Scanner(System.in);
		int opc = 0;
		do {
			System.out.println("******************************");
			System.out.println("******* Menú Principal *******");
			System.out.println("1) Alta Jugador.");
			System.out.println("2) Baja Jugador.");
			System.out.println("3) Listado de Equipos.");
			System.out.println("4) Listado de Jugadores.");
			System.out.println("5) Alta Estadística.");
			System.out.println("6) Baja Estadística.");
			System.out.println("8) Listado partidos entre dos equipos.");
			System.out.println("9) Listado de todos los equipos ordenados por división");
			System.out.println("10) Listado de todos los partidos ganados por un equipo");
			System.out.println("11) Listado de Jugadores de una procedencia X, que jueguen en una conferencia X, en una división X, que no sean de una ciudad X");
			System.out.println("12) Listado de Jugadores de la Conferencia Este.");
			System.out.println("13) Listado de Jugadores de la División Pacifico.");
			System.out.println("14) Inserta un jugador en la base de datos, por fichero.");
			opc = sn.nextInt();
			
			switch (opc) {
				case 1:
					jugaController.darAltaJugador(Main.crearJugador(jugaController.dameUltimoCodigoJugador()));
					break;
				case 2:
					Main.eliminarJugador();
					break;
				case 3:
					Main.listadoEquipos();
					break;
				case 4:
					Main.listadoJugadores();
					break;
				case 5:
					estadiController.anadirEstadisticas(Main.crearEstadistica());
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					Main.listadoEquiposPedidosUsuario();
					break;
				case 9:
					Main.listadoEquiposOrdenadosDivision();
					break;
				case 10:
					Main.listadoPartidosGanadosPor();
					break;
				case 11:
					Main.listadoJugadoresConMuchosFiltros();
					break;
				case 12:
					Main.listadoJugadoresConferenciaEn("East");
					break;
				case 13:
					Main.listadoJugadoresDivisionEn("Pacific");
					break;
				case 14:
					// Es necesario comprobar que el equipo existe, al estár relacionado si intentamos insertar un jugador
					// en un equipo que no existe dará error de SQL.
					Main.insertarJugadorEnBDporFichero();
					break;
				case 15:
					equipoController.equiposPerdidosDiferencia40();
					break;
				default:
					System.out.println("*** Opción no valida ***");
					
			}
		}while(opc!=16);
	}
	
	public static Jugador crearJugador(int lastCode) {
		EquipoController equipoController = new EquipoController();
		Scanner sc = new Scanner(System.in);
		Scanner sn = new Scanner(System.in);
		int cod = lastCode+1;
		String nombre = "";
		String procedencia = "";
		String altura = "";
		int peso = 0;
		String posicion = "";
		String nombre_equipo = "";
		System.out.println("*************************");
		System.out.println("Nombre del jugador: ");
		nombre = sc.nextLine();
		System.out.println("*************************");
		System.out.println("Procedencia del jugador: ");
		procedencia = sc.nextLine();
		System.out.println("*************************");
		System.out.println("Altura del jugador: ");
		altura = sc.nextLine();
		System.out.println("*************************");
		System.out.println("Peso del jugador: ");
		peso = sn.nextInt();
		System.out.println("*************************");
		System.out.println("Posición del jugador: ");
		posicion = sc.nextLine();
		System.out.println("*************************");

		do {
			System.out.println("Nombre del equipo del jugador: ");
			nombre_equipo = sc.nextLine();
			System.out.println("*************************");
			if (equipoController.existeEquipo(nombre_equipo)==false) {
				System.out.println("*******************************");
				System.out.println("El equipo introducido no existe");
				System.out.println("*******************************");
			}
		}while(equipoController.existeEquipo(nombre_equipo)==false);

		Jugador jugadorCreado = new Jugador(cod,nombre,procedencia,altura,peso,posicion,nombre_equipo);
		return jugadorCreado;
	}
	
	public static void listadoJugadoresConMuchosFiltros() {
		jugadorController jugaController = new jugadorController();
		Scanner sc = new Scanner(System.in);
		Scanner sn = new Scanner(System.in);
		
		String procedencia = "";
		String conferencia = "";
		String division = "";
		String ciudad = "";
		
		System.out.println("Dame Procedencia: ");
		procedencia = sc.nextLine();
		
		System.out.println("Dame Conferencia: ");
		conferencia = sc.nextLine();
		
		System.out.println("Dame División: ");
		division = sc.nextLine();
		
		System.out.println("Dame Ciudad: ");
		ciudad = sc.nextLine();
		
		jugaController.listadoJugadoresConMuchosFiltros(procedencia, conferencia, division, ciudad);
	}
	
	public static Jugador crearJugadorFichero() {
		jugadorController jugaController = new jugadorController();
		String ruta = "./altaJugador.txt";
		File fichero = new File(ruta);
		Jugador jugador = new Jugador();
		String linea = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(fichero));
			linea = br.readLine();
			while (linea!=null){
				String[] partes = linea.split("::");
				jugador = new Jugador(jugaController.dameUltimoCodigoJugador()+1,partes[0],partes[1],partes[2],Integer.valueOf(partes[3]),partes[4],partes[5]);
				linea=br.readLine();
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jugador;
	}
	
	public static void insertarJugadorEnBDporFichero() {
		jugadorController jugaController = new jugadorController();
		jugaController.darAltaJugador(crearJugadorFichero());
	}
	
	public static void listadoPartidosGanadosPor() {
		PartidosController partiController = new PartidosController();
		Scanner sc = new Scanner(System.in);
		Scanner sn = new Scanner(System.in);
		
		String equipo = "";
		
		System.out.println("Dame nombre equipo: ");
		equipo = sc.nextLine();
		
		partiController.listadoPartidosGanadosPor(equipo);
	}
	
	public static void listadoEquiposOrdenadosDivision() {
		EquipoController equipoController = new EquipoController();
		ArrayList<Equipo> equiposBD = new ArrayList<Equipo>();
		equiposBD = equipoController.listadoEquiposOrdenadosDivision();
		System.out.println("************************************ Listado de Equipos Por División ************************************");
		for (int i = 0; i < equiposBD.size(); i++) {
			System.out.println("*****************************************************************************************************");
			System.out.println("Nombre: " + equiposBD.get(i).getNombre() + " | Ciudad: " + equiposBD.get(i).getCiudad() + " | Conferencia: " + equiposBD.get(i).getConferencia() + " | División: " + equiposBD.get(i).getDivision());
		}
		System.out.println("********************************************************************************************");
	}
	
	public static void listadoJugadoresConferenciaEn(String conferencia) {
		jugadorController jugaController = new jugadorController();
		jugaController.listadoJugadoresConTemporadasEnConferencias(conferencia);
	}
	
	public static void listadoJugadoresDivisionEn(String division) {
		jugadorController jugaController = new jugadorController();
		jugaController.listadoJugadoresConTemporadasEnDivision(division);
	}
	
	public static void listadoEquiposPedidosUsuario() {
		PartidosController partiController = new PartidosController();
		Scanner sc = new Scanner(System.in);
		Scanner sn = new Scanner(System.in);
		
		String equipo1 = "";
		String equipo2 = "";
		
		System.out.println("Dame el nombre del equipo 1: ");
		equipo1 = sc.nextLine();
		
		System.out.println("Dame el nombre del equipo 2: ");
		equipo2 = sc.nextLine();
		
		partiController.listadoPartidosEntreDosEquipos(equipo1, equipo2);
	}
	
	public static Estadistica crearEstadistica() {
		jugadorController jugaController = new jugadorController();
		Estadistica estadisticaCreada = new Estadistica();
		Scanner sc = new Scanner(System.in);
		Scanner sn = new Scanner(System.in);
		String nombre = "";
		String temporada = "";
		double puntos_por_partido = 0;
		double asistencias_por_partido = 0;
		double tapones_por_partido = 0;
		double rebotes_por_partido = 0;
		
		System.out.println("*************************");
		System.out.println("Temporada: ");
		temporada = sc.nextLine();
		System.out.println("*************************");
		System.out.println("Nombre del jugador: ");
		nombre = sc.nextLine();
		System.out.println("*************************");
		System.out.println("Puntos por partido: ");
		puntos_por_partido = sn.nextDouble();
		System.out.println("*************************");
		System.out.println("Asistencias por partido: ");
		asistencias_por_partido = sn.nextDouble();
		System.out.println("*************************");
		System.out.println("Tapones por partido: ");
		tapones_por_partido = sn.nextDouble();
		System.out.println("*************************");
		System.out.println("Rebotes por partido: ");
		rebotes_por_partido = sn.nextDouble();
		
		do {
			if (jugaController.existeJugador(nombre)==false) {
				System.out.println("*******************************");
				System.out.println("El jugador introducido no existe");
				System.out.println("*******************************");
				System.out.println("Nombre del jugador: ");
				nombre = sc.nextLine();
				System.out.println("*************************");
			}
		}while(jugaController.existeJugador(nombre)==false);
		
		estadisticaCreada = new Estadistica(temporada,jugaController.dameCodigoJugador(nombre),puntos_por_partido,asistencias_por_partido,tapones_por_partido,rebotes_por_partido);
		
		return estadisticaCreada;
	}
	
	public static void eliminarJugador() {
		jugadorController jugaController = new jugadorController();
		Scanner sc = new Scanner(System.in);
		String nombreJugador = "";
		System.out.println("Dame el nombre del jugador: ");
		nombreJugador = sc.nextLine();
		Main.eliminarEstadisticas(jugaController.dameCodigoJugador(nombreJugador));
		jugaController.eliminarJugador(nombreJugador);
	}
	

	
	public static void eliminarEstadisticas(int codJugador) {
		EstadisticaController estadiController = new EstadisticaController();
		estadiController.eliminarEstadisticas(codJugador);
	}
	
	public static void listadoEquipos() {
		EquipoController equipoController = new EquipoController();
		ArrayList<Equipo> equiposBD = new ArrayList<Equipo>();
		equiposBD = equipoController.listadoEquipos();
		System.out.println("************************************ Listado de Equipos ************************************");
		for (int i = 0; i < equiposBD.size(); i++) {
			System.out.println("********************************************************************************************");
			System.out.println("Nombre: " + equiposBD.get(i).getNombre() + " | Ciudad: " + equiposBD.get(i).getCiudad() + " | Conferencia: " + equiposBD.get(i).getConferencia() + " | División: " + equiposBD.get(i).getDivision());
		}
		System.out.println("********************************************************************************************");
	}
	
	public static void listadoJugadores() {
		jugadorController jugaController = new jugadorController();
		ArrayList<Jugador> jugadoresBD = new ArrayList<Jugador>();
		jugadoresBD = jugaController.listadoJugadores();
		System.out.println("************************************ Listado de Jugadores ************************************");
		for (int i = 0; i < jugadoresBD.size(); i++) {
			System.out.println("*******************************************************************************************************************************");
			System.out.println("Código: " + jugadoresBD.get(i).getCodigo() + " | Nombre: " + jugadoresBD.get(i).getNombre() + " | Procedencia: " + jugadoresBD.get(i).getProcedencia() + " | Altura: " + jugadoresBD.get(i).getAltura() + " | Peso: " + jugadoresBD.get(i).getPeso() + " | Posicion: " + jugadoresBD.get(i).getPosicion() + " | Nombre del equipo: " + jugadoresBD.get(i).getNombre_equipo());
		}
		System.out.println("*******************************************************************************************************************************");
	}

}
