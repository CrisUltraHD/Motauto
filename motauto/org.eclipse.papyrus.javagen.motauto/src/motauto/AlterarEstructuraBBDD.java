package motauto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.ibatis.common.jdbc.ScriptRunner;

public class AlterarEstructuraBBDD {
	// Metodo para Crear una nueva base de datos
	// Conectamos directamente al servidor, llamamos la funcion que nos pide datos para conectarnos a la nueva base de datos
	// Ejecutamos la caomanda para crear la base de datos, si funciona Imprimimos en pantalla correcto sino incorrecto
	// Apartir del driver ScriptRunner ejecutamos un script con la nueva conexion
	// Cremos un archivo de texto con los datos de la conexion para que se guarden fisicamente i no tengamos que estar entrandolos
	// Finalmente ejecutamos el script, i cerramos todo
	public static Database createBDD() {
		Database db = null;
		try {
			if (Utiles.siNo("Seguro que quieres crear una nueva BDD?")) {
				db = AlterarEstructuraBBDD.configuracionBDD();
				Connection conex = db.conectToBDD();
				System.out.println(db.ExecuteCreate("CREATE DATABASE " + db.GetDB()) ? "Correcte" : "Incorrecte");
				conex = db.conectToBDD(db.GetDB());
				ScriptRunner runner = new ScriptRunner(conex, false, false);
				InputStreamReader reader;

				Scanner scanner;
				String ruta = "Scripts\\datosConex.txt";
				File carpeta = new File(ruta);
				scanner = new Scanner(carpeta);
				String linea = scanner.nextLine();
				String[] datos = linea.split("#");
				scanner.close();
				db = new Database(datos[0], datos[1], datos[2], datos[3], datos[4]);
				System.out.println(db.connectDatabase() ? "Prueba de Conexion positiva." : "Error en la Conexion");

				reader = new InputStreamReader(new FileInputStream("Scripts\\CreacionBaseDatos.sql"), "UTF8");

				runner.runScript(reader);
				reader.close();
			}

		} catch (Exception e) {
			System.out.println("error: " + e);
		}
		return db;
	}
		// Metodo para modificar una base de datos apartir de un script
		// Nos pide la conexion a la base de daots
		// Luego nos pide que Script queremos ejecutar, puede ser cualquier cosa, desde cargar un juego de datos
		// A un script para borrar toda la base de datos
	public static boolean updateBDD(Database db) {
		boolean correct;
		try {
			String ruta = "Scripts\\";
			File carpeta = new File(ruta);
			String[] nomDelsFichers = carpeta.list();
			Connection conex = db.GetConnection();
			ScriptRunner runner = new ScriptRunner(conex, false, false);
			InputStreamReader reader;
			System.out.println("Posa el nom del Script a Carregar: ");
			for (int i = 0; i < nomDelsFichers.length; i++) {
				System.out.println("- " + nomDelsFichers[i]);
			}
			reader = new InputStreamReader(new FileInputStream("Scripts\\" + Utiles.demanarString() + ".sql"), "UTF8");
			runner.runScript(reader);
			reader.close();
			correct = true;
		} catch (Exception e) {
			correct = false;
		}
		return correct;
	}
	// Metodo para configurar desde 0 la conexion a la base de datos, todo lo configuarado en este metodo
	// Se guardara fisicamente en un archivo el cual podremos usar para conectarlos apartir de ese momento
	public static Database configuracionBDD() {
		String ruta = "Scripts\\datosConex.txt";
		File archivo = new File(ruta);
		int opcio;
		BufferedWriter bw;
		String driver = "org.mariadb.jdbc.Driver";
		System.out.println("Diges la base de dades on treballaras: ");
		String db = Utiles.demanarString();
		System.out.println("Diges l'usuari de la base de dades: ");
		String user = Utiles.demanarString();
		System.out.println("Diges la contrasenya de la base de dades: ");
		String passwd = Utiles.demanarString();
		System.out.println("Diges la IP de la base de dades: (nomes la Ip)");
		String ip = Utiles.demanarString();
		String url = "jdbc:mysql://" + ip + "/";
		System.out.println("Quin es la base de dades utilitzada? ");
		System.out.println("0- MariaDB ");
		System.out.println("1- PostGress ");
		System.out.println("2- MySQL ");

		opcio = Utiles.menu(3);
		switch (opcio) {
		case 0:
			driver = "org.mariadb.jdbc.Driver";
			break;
		case 1:
			driver = "org.postgresql.Driver";
			break;
		case 2:
			driver = "com.mysql.jdbc.Driver";
			break;

		}
		try {
			bw = new BufferedWriter(new FileWriter(archivo));
			bw.write(db + "#" + user + "#" + passwd + "#" + url + "#" + driver);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Database conexion = new Database(db, user, passwd, url, driver);
		System.out.println(conexion.connectDatabase() ? "Conexio Correcte" : "Conexio Incorrecte");
		return conexion;

	}
		// Metodo para Mostar todas las tablas que hay en un servidor de BDD
		// Simpelemnte ejecuta un Show tables, i te muestra todas las tablas, de manera que asi es mas facil esojer una de ellas

	public static void showTables(Database db) {
		ArrayList<String> tables = new ArrayList<String>();

		try {
			ResultSet rs = db.ExecuteQuery("SHOW TABLES");
			while (rs.next()) {
				tables.add(rs.getString(1));
			}
			for (int i = 0; i < tables.size(); i++) {
				rs = db.ExecuteQuery("SHOW COLUMNS FROM " + tables.get(i));
				System.out.println("- " + tables.get(i));
				while (rs.next()) {
					System.out.print(rs.getString(1) + " / ");
				}
				System.out.println("\n");
			}

		} catch (Exception e) {

		}
	}
		// Metodo para ejecutar codigo secuencial en una base de datos
		// Le pide al usuario que codigo quiere introducir, una tra sla otra, i cuando acaba ejecuta todas secuencialmente
		// Es muy util para no tene rque ir al servidor de Bdd fisicamente, puedes ejecutar cualquier cosa desde aqui
	public static void sqlInyector(Database db) {
		ArrayList<String> commands = new ArrayList<String>();
		String text = "";
		System.out.println("Escribe las comandas para inyectar en la BDD seguidas de ';' i enter.\nexecute para ejecutarlas");
		while (!text.equalsIgnoreCase("Execute")) {
			text = Utiles.demanarString();
			if (!text.equalsIgnoreCase("Execute"))
				commands.add(text);
		}
		for (int i = 0; i < commands.size(); i++) {
			try {
				System.out.println(db.ExecuteUpdate(commands.get(i)) ? "Correcte" : "Correcte");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		// Este metodo se ejecuta si el archivo de coneexion no existe, asi si desplegamos el programa por primera vez, nos pedira que hagamos una primera conexion a la base de datos
		// De la misma manera se creara el archivo i no tendremos que crearlo mas
	public static Database establecerPrimeraConexion() throws FileNotFoundException {
		String ruta = "Scripts\\datosConex.txt";
		File archivo = new File(ruta);
		Database db = null;
		if (archivo.exists()) {
			Scanner scanner;
			scanner = new Scanner(archivo);
			String linea = scanner.nextLine();
			String[] datos = linea.split("#");
			scanner.close();
			db = new Database(datos[0], datos[1], datos[2], datos[3], datos[4]);
			System.out.println(db.connectDatabase() ? "Prueba de Conexion positiva." : "Error en la Conexion");
		} else {
			System.out.println("No hemos detectado un archivo de conexion existente, vamos a crearlo.");
			db = AlterarEstructuraBBDD.configuracionBDD();
			System.out.println(db.connectDatabase() ? "Prueba de Conexion positiva." : "Error en la Conexion");
		}
		return db;
	}

}
