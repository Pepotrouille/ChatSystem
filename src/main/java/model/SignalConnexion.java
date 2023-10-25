package model;

public class SignalConnexion extends Signal{
	
	
	//---------------------------Attributs-------------------------
	
	private String pseudo;
	
	//---------------------------Méthodes-------------------------
	
	//----------Constructeur
	
	public SignalConnexion(String pseudo) { 				
		
		this.pseudo = pseudo;
	}
	

	//----------Getters
	public String GetPseudo() {
		return this.pseudo;
	}
	
	//----------Setters
	public void SetPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	
	//----------Autres Méthodes

	
	public String ToString() {
		return "C" + this.pseudo;
	}
}