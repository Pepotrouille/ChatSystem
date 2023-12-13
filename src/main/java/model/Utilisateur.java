package model;

public class Utilisateur{
	
	
	//---------------------------Attributs-------------------------
	
	public static Utilisateur current_user;
	
	public static void SetCurrentUser(Utilisateur newUser) {current_user = newUser;};
	
	public static Utilisateur GetCurrentUser() {return current_user;};
	
	private String ip;
	
	private String pseudo;
	
	//---------------------------Méthodes-------------------------
	
	//----------Constructeur
	
	public Utilisateur(String ip, String pseudo) { 				
		
		this.ip = ip;
		this.pseudo = pseudo;
	}

	//----------Getters
	public String GetIP() {
		return this.ip;
	}
	
	public String GetPseudo () {
		return this.pseudo;
	}
	
	//----------Setters
	
	public void SetPseudo (String newPseudo) {
		this.pseudo = newPseudo;
	}

	//----------Autres Méthodes
	
}