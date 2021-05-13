package motauto;
import java.sql.*;

public class Database {
	
    // Atributs per a la connexió
    private String db;
    private String user;
    private String passwd;
    private String url ;
    private String driver;
    // Connexió
    private Connection connection;
    private Statement st;

    // Constructor
    public Database(String db, String user, String passwd, String url, String driver) {
	this.db= db;
	this.user=user;
	this.passwd=passwd;
	this.url=url;
	this.driver=driver;
        
    }
    public Database(String user, String passwd, String url, String driver) {
    	this.user=user;
    	this.passwd=passwd;
    	this.url=url;
    	this.driver=driver;
    }

    // Setters
    public void SetDB(String sDB) {
        db = sDB;
        url += sDB;
    }

    public void SetUser(String sUser) {
        user = sUser;
    }

    public void SetPasswd(String sPasswd) {
        passwd = sPasswd;
    }

    public void SetURL(String sURL) {
        url = sURL;
    }

    // Getters
    public String GetDB() {
        return db;
    }

    public String GetUser() {
        return user;
    }

    public String GetPasswd() {
        return passwd;
    }

    public String GetURL() {
        return url;
    }

    public Connection GetConnection() {
        return connection;
    }

    // Metodes
    public boolean connectDatabase() {
    	boolean conexValida=false;
        try {
            // Registro el driver de PostgresSQL
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de MySQL: " + ex);
            }
            // Conecta amb la base de dades
            connection = DriverManager.getConnection(url+db, user, passwd);

            boolean valid = connection.isValid(50000);
            conexValida= (valid ? true : false);
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
        return conexValida;
    }
    

    // Metode per executar sentencies SQL que retornen resultats (basicament
    // SELECTS)
    public ResultSet ExecuteQuery(String query) throws SQLException {
        connectDatabase();
        st = connection.createStatement();

        
        return st.executeQuery(query);

    }

    // Metode per executar sentencies SQL que no retornen resultats (INSERTS,
    // UPDATES, DELETES)
    public boolean ExecuteUpdate(String query) throws SQLException {
		boolean ejecucionCorrecta;
        connectDatabase();

        st = connection.createStatement();
        int correcte = st.executeUpdate(query);
		if (correcte ==1){
			ejecucionCorrecta=true;
		}
		else{
			ejecucionCorrecta=false;
		}
		

		return ejecucionCorrecta;
    }
    // Metodo para apartir de una conexion al servidor, i no a una base de datos en concreto, podemos ejecutar comandas a nivel serivodr
    // Por ejemplo la comanda de crear una base de datos solo se puede ejecutar aqui, conectando directamente al servidor i no a la base de daots
    
    public boolean ExecuteCreate(String query) throws SQLException {
		boolean ejecucionCorrecta;
		connection = conectToBDD(this.db);

        st = connection.createStatement();
        int correcte = st.executeUpdate(query);
		if (correcte ==1){
			ejecucionCorrecta=true;
		}
		else{
			ejecucionCorrecta=false;
		}
				
		
		return ejecucionCorrecta;
    }
    // Apartir del driver de la conexion, nos conecta directamente al servidor, este metodo se ejecuta si no le decimos a que base de datos nos quereos conectar
    public Connection conectToBDD() {
        try {

            try {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de MySQL: " + ex);
            }

            connection = DriverManager.getConnection(url, user, passwd);

            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "Conex On" : "Conex off");
            
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
        
        return connection;
    }
    // Apartir del driver de la conexion, nos conecta directamente al servidor, este metodo se ejecuta si le decimos a que base de datos nos quereos conectar explicitamente
    // Es un metodo sobrecargado, asi que si no le pasmos parametro se ejecutara la conexion al servidor directamente i no a la base de datos
    public Connection conectToBDD(String db) {
    	boolean conexValida=false;
        try {
           
            try {
            	Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de MySQL: " + ex);
            }

            connection = DriverManager.getConnection(url+db, user, passwd);

            boolean valid = connection.isValid(50000);
            conexValida= (valid ? true : false);
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
        return connection;
    }
    public void showAll() {
    	System.out.println("Base de dades seleccionada: " + this.db);
    	System.out.println("Usuari: " + this.user);
    	System.out.println("Contraseña: " + this.passwd);
    	System.out.println("Ip Servidor: " + this.url);
    	
    }
}
