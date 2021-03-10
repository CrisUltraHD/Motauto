package motauto;

import java.sql.SQLException;

public class AlterarEstructuraBBDD {
	
	Database database = new Database("motauto","motauto");
	
	public void update(String liniaComanda) 
	{
		database.connectDatabase();
		try 
		{
			database.ExecuteUpdate(liniaComanda);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
