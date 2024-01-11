package model;

public class Utilisateur{
	
	
	//---------------------------Attributs-------------------------
	
	public static Utilisateur utilisateurActuel;
	
	public static void SetUtilisateurActuel(Utilisateur newUser) 
	{
		if(utilisateurActuel != null)
		{
			utilisateurActuel = newUser;
		}
		else
		{
			utilisateurActuel = new Utilisateur(newUser);
		}
	}
	
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
	public Utilisateur(Utilisateur utilisateur) { 				
		
		this.ip = utilisateur.GetIP();
		this.pseudo = utilisateur.GetPseudo();
		System.out.println("Ajout du pseudo à l'utilisateur : " + this.pseudo);
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