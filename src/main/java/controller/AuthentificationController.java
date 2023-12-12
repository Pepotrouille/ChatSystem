package controller;

import controller.BDDAuthentificationController;

public class AuthentificationController {
	
	// Singleton
	// Classe intermédiaire entre l'interface de l'authentification et la BDD de l'authentification
	
	public static AuthentificationController self;
	
	private BDDAuthentificationController bdd_auth_controller;
	
	private String pseudo_conv;
	
	private String ip;
	
	private int port;
	
	public static AuthentificationController GetInstance()
	{
		if(self == null)
		{
			self = new AuthentificationController();
		}
		return self;
	}
	
	// Constructeur
	public AuthentificationController(String pseudo_conv, String ip, int port) {
		this.bdd_auth_controller = BDDAuthentificationController.GetInstance();
		this.pseudo_conv = pseudo_conv;
		this.ip = ip;
		this.port = port;
	}
	
	// S'authentifier
	public void Authentifier(String nom, String mdp)
	{
		if (this.bdd_auth_controller.VerifierAuthentification(nom, mdp))
		{
			// A FAIRE!
		}
	}
	
	// Créer un nouveau compte
	public void CreerCompte(String nom, String mdp)
	{
		if (this.bdd_auth_controller.AjouterAuthentification(nom, mdp))
		{
			// A FAIRE!
		}
	}
}