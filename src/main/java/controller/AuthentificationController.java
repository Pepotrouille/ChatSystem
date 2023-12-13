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
	
	// S'authentifier
	public void Authentifier(String login, String mdp) throws SQLException, ErreurConnexionException
	{
		try  {
			boolean res = this.bdd_auth_controller.VerifierAuthentification(login, mdp);
			if (res)
			{
				// La connexion a réussi
				System.out.println("Authentification OK - Bienvenue " + login + "!");
			}
			else {
				// La connexion a échoué
				System.out.println("Le login et/ou le mot de passe est incorrect. Veuillez réeesayer.");
			}
		}
		catch (Exception e) {
			throw e;
		}
		
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