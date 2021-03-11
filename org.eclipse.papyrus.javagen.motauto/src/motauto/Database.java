package motauto;
import java.sql.*;

public class Database {
	
    // Atributs per a la connexió
    private String db = "motauto";
    private String user = "lab";
    private String passwd = "lab";
    private String url = "jdbc:mysql://26.41.52.124/" + db;
    private String driver = "org.mariadb.jdbc.Driver";
    // Connexió
    private Connection connection;
    private Statement st;

    // Constructor
    public Database(String db, String user, String passwd, String url, String driver) {
	this.db= db;
	this.user=user;
	this.passwd=passwd;
	this.url=url+db;
	this.driver=driver;
        
    }
    public Database() {

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
    public void connectDatabase() {

        try {
            // Registro el driver de PostgresSQL
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de MySQL: " + ex);
            }
            // Conecta amb la base de dades
            connection = DriverManager.getConnection(url, user, passwd);

            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "Conexió a la BBDD " + db + " correcta." : "Conexió fallida.");
            System.out.println();

        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
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
}
