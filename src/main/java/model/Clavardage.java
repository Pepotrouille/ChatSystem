package model;

import java.sql.SQLException;

import exceptions.DateInvalideException;
import exceptions.MessageInvalideException;

public class Clavardage {

	Utilisateur utilisateur;
	
	int portSource;
	
	int portDest;
	
	Historique historique;
	
	boolean estValide;
	
	
	public Clavardage(Utilisateur utilisateur) throws SQLException, MessageInvalideException, DateInvalideException
	{
		this.utilisateur = utilisateur;
		this.estValide = false;
		this.historique = new Historique(Utilisateur.GetUtilisateurActuel().GetIP(), utilisateur.GetIP());
	}
	
	public String GetIPDestination() 
	{
		return "Test";
	}
	
	public String GetUserPseudo() 
	{
		return "Test";
	}
}
