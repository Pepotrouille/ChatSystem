package model;

public class SignalNouveauClavardage extends Signal{
	
	
	//---------------------------Attributs-------------------------
	
	private int port;
	
	//---------------------------Méthodes-------------------------
	
	//----------Constructeur
	
	public SignalNouveauClavardage(int port) { 				
		
	}


	//----------Getters
	public int GetPort() {
		return this.port;
	}
		
	//----------Setters
	public void SetPort(int port) {
		this.port = port;
	}
	//----------Autres Méthodes
	
	public String ToString() {
		return "N"+this.port;
	}
	
}