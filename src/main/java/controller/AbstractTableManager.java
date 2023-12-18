package controller;


public class AbstractTableManager{
	
	
	static final String DB_URL = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_010";
	static final String USER = "projet_gei_010";
	static final String PASS = "eeP9nuha";
	
	/*
	public static void setDatabase(String newDB_URL, String newUSER, String newPASS)
	{
		DB_URL = newDB_URL;
		USER = newUSER;
		PASS = newPASS;
	}
	*/

	//---------------------------------------INIT TABLE------------------
	protected boolean InitializeTable() {return false;};
	

	//---------------------------------------DROP TABLE------------------
	protected boolean DropTable() {return false;};
	
}