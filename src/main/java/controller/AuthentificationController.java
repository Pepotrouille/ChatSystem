package controller;

import controller.BDDAuthentificationController;

public class AuthentificationController {
	
	// Singleton
	// Classe intermédiaire entre l'interface de l'authentification et la BDD de l'authentification
	
	public static AuthentificationController self;
	
	private BDDAuthentificationController bdd_auth_controller;
	
	
	public static AuthentificationController GetInstance()
	{
		if(self == null)
		{
			self = new AuthentificationController();
		}
		return self;
	}
	
	// S'authentifier
	public void Authentifier(String login, String mdp)
	{
		//if (this.bdd_auth_controller.VerifierAuthentification(nom, mdp))
		{
			// A FAIRE!
		}
	}
	
	// Créer un nouveau compte
	public void CreerCompte(String login, String mdp)
	{
		// if (this.bdd_auth_controller.AjouterAuthentification(nom, mdp))
		{
			// A FAIRE!
		}
	}
}