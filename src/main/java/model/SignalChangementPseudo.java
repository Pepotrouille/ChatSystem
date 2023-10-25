package model;

public class SignalChangementPseudo extends Signal{
	
	
	//---------------------------Attributs-------------------------
	
	private String pseudo;
	
	//---------------------------Méthodes-------------------------
	
	//----------Constructeur
	
	public SignalChangementPseudo(String pseudo) { 				
		
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
		return "P"+this.pseudo;
	}
}