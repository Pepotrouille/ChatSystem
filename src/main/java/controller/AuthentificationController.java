package controller;

import java.sql.SQLException;
import controller.BDDAuthentificationController;
import exceptions.ErreurConnexionException;

public class AuthentificationController {
	
	// Singleton
	// Classe intermédiaire entre l'interface de l'authentification et la BDD de l'authentification
	
	public static AuthentificationController self;
	
	private BDDAuthentificationController bdd_auth_controller = BDDAuthentificationController.GetInstance();
	
	
	public static AuthentificationController GetInstance()
	{
		if(self == null)
		{
			self = new AuthentificationController();
		}
		return self;
	}
	
	public BDDAuthentificationController GetBDDAuthentificationController()
	{
		return this.bdd_auth_controller;
	}
	
	// S'authentifier
	public void Authentifier(String login, String mdp) throws SQLException, ErreurConnexionException
	{
		this.bdd_auth_controller.VerifierAuthentification(login, mdp);
			
		// La connexion a réussi
		System.out.println("Authentification OK - Bienvenue " + login + "!");
			
		
	}
	
	// Créer un nouveau compte - Que pour le compte "admin"!
	public void CreerCompte(String login, String mdp) throws SQLException
	{
		if (this.bdd_auth_controller.AjouterAuthentification(login, mdp))
		{
			// A FAIRE!
			System.out.println("Création du nouveau compte OK - Bienvenue " + login + "!");
		}
	}
}