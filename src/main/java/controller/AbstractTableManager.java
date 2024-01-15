package controller;


public class AbstractTableManager{

	//---------------------------Attributs-------------------------
	
	static final String DB_URL = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_010";
	static final String USER = "projet_gei_010";
	static final String PASS = "eeP9nuha";
	

	//---------------------------Méthodes-------------------------

	//----------Constructeur
	
	protected AbstractTableManager()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//----------Getters
	
	//----------Setters
	
	//----------Autres Méthodes

	//---------------------------------------INIT TABLE------------------
	protected boolean InitializeTable() {return false;};
	

	//---------------------------------------DROP TABLE------------------
	protected boolean DropTable() {return false;};
	
}