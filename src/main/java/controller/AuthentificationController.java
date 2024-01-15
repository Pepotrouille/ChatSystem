package controller;

import java.sql.SQLException;

import exceptions.EchecManipulationBDDException;
import exceptions.ErreurConnexionException;

public class AuthentificationController { // Singleton

	//---------------------------Attributs-------------------------
	
	public static AuthentificationController self;
	
	private BDDAuthentificationController bddAuthController = BDDAuthentificationController.GetInstance();

	//---------------------------Méthodes-------------------------

	//----------Constructeur
	
	public static AuthentificationController GetInstance()
	{
		if(self == null)
		{
			self = new AuthentificationController();
		}
		return self;
	}
	
	//----------Getters
	
	public BDDAuthentificationController GetBDDAuthentificationController() {
		return bddAuthController;
	}
	
	//----------Setters
	
	//----------Autres Méthodes
	
	// S'authentifier
	public void Authentifier(String login, String mdp) throws SQLException, ErreurConnexionException
	{
		this.bddAuthController.VerifierAuthentification(login, mdp);
			
		System.out.println("Authentification OK - Bienvenue " + login + "!"); // La connexion a réussi
	}
	
	
	// Créer un nouveau compte - Que pour le compte "admin"!
	public void CreerCompte(String login, String mdp) throws SQLException, EchecManipulationBDDException
	{
		this.bddAuthController.AjouterAuthentification(login, mdp);
		System.out.println("Création du nouveau compte OK - Bienvenue " + login + "!");
		
	}

}