package model;

public class SignalValiderNouveauClavardage extends Signal{
	
	
	//---------------------------Attributs-------------------------
	
	private int port;
	
	//---------------------------Méthodes-------------------------
	
	//----------Constructeur
	
	public SignalValiderNouveauClavardage(int port) { 				
		this.port = port;
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
		return "V"+this.port;
	}
	
}