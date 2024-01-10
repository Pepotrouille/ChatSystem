package model;

public class Utilisateur{
	
	
	//---------------------------Attributs-------------------------
	
	public static Utilisateur utilisateurActuel;
	
	public static void SetUtilisateurActuel(Utilisateur newUser) {utilisateurActuel = newUser;};
	
	public static Utilisateur GetUtilisateurActuel() {return utilisateurActuel;};
	
	private String ip;
	
	private String pseudo;
	
	private Integer admin; // Si utilisateur "admin" alors "true", sinon "false"
	
	//---------------------------Méthodes-------------------------
	
	//----------Constructeur
	
	public Utilisateur(String ip, String pseudo) { 				
		
		this.ip = ip;
		this.pseudo = pseudo;
		this.admin = 0;
	}

	//----------Getters
	public String GetIP() {
		return this.ip;
	}
	
	public String GetPseudo () {
		return this.pseudo;
	}
	
	public Integer GetAdmin() {
		return this.admin;
	}
	
	//----------Setters
	
	public void SetPseudo (String newPseudo) {
		this.pseudo = newPseudo;
	}
	
	public void SetAdmin(Integer status) { 
		this.admin = status;
	}

	//----------Autres Méthodes
	
}