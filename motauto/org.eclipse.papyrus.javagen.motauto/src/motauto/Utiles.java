// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package motauto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/************************************************************/
/**
 * 
 */
public class Utiles {
	static Scanner lector = new Scanner(System.in);

	public static String demanarString() {
		String nom = lector.nextLine(); // Demano un Strnig i el retorno
		return nom;
	}

	public static int demanarNota() {
		int num = 0;
		boolean correcte;
		do {
			if (lector.hasNextInt()) { // Controlo que sigui un numero el que m'han entrat per teclat
				num = lector.nextInt();
				if (num > -1 && num < 11) { // Controla que sigui un numero entre 0 i 10
					lector.nextLine();
					correcte = true; // Si es aixi les dos coses correcte es true.
				} else {
					System.out.println("Entram un numero del 0 al 10");
					correcte = false; // Si no correcte es fals i torno a entrar al bucle
				}
			} else {
				System.out.println("Entram un numero (no entris caracters)");
				lector.nextLine(); // si entrem caracters tora a demanar el numero
				correcte = false;
			}

		} while (!correcte);
		return num;
	}

	public static int demanarNum() {
		int num = 0;
		boolean correcte;
		do {
			if (lector.hasNextInt()) {
				num = lector.nextInt();
				lector.nextLine();
				correcte = true;
			} else {
				lector.nextLine();
				correcte = false;
			}

		} while (!correcte);
		return num;
	}

	public static float demanarNumFloat() {
		float num = 0;
		boolean correcte;
		do {
			if (lector.hasNextFloat()) {
				num = lector.nextFloat();
				lector.nextLine();
				correcte = true;
			} else {
				lector.nextLine();
				correcte = false;
			}

		} while (!correcte);
		return num;
	}

	public static int menu(int num) { // Creo un menu simple amb 3 opcions apartir de un switch i un while
		boolean correcta = false;
		int opcio = -1;
		while (!correcta) {

			if (lector.hasNextInt()) {
				opcio = lector.nextInt();
				lector.nextLine();
				if (opcio >= 0 && opcio <= num) { // Mira que el numero estigui entre 0 i 3
					correcta = true;
				} else {
					System.out.println("Opci� incorrecta a de ser del 0 al " + num);
					correcta = false; // Si no es un numero entre aquets valors tora a demanar una opcio
				}
			} else {
				System.out.println("Opci� incorrecta ha de ser un numero");
				correcta = false; // Si no es un numero torna a demanar un numer
				lector.nextLine();
			}
		}
		return opcio;

	}

	public static LocalDate demanarData() {
		System.out.println("Introdueix Data Factura: DD-MM-YYYY");
		int dia = demanarNum();
		int mes = demanarNum();
		int any = demanarNum();

		return LocalDate.of(any, mes, dia);

	}
	

	public static LocalTime demanarHora() {
		System.out.println("Introdueix Hora Factura: HH-MM-SS");
		int hora = demanarNum();
		int minut = demanarNum();
		int segon = demanarNum();

		return LocalTime.of(hora,minut,segon);

	}


	public static boolean siNo(String frase) {
		System.out.println(frase);
		String opcion = lector.nextLine();

		boolean crear = false;

		if (opcion.equalsIgnoreCase("si")) {
			crear = true;
		} else if (opcion.equalsIgnoreCase("no")) {
			crear = false;
		} else {
			siNo(frase);
		}

		return crear;

	}


}