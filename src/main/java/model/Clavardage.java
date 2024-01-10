package model;

import java.sql.SQLException;

import controller.SignalReceptionUnicastController;
import exceptions.DateInvalideException;
import exceptions.MessageInvalideException;

public class Clavardage {

	Utilisateur utilisateur;
	
	int portReception;
	
	int portEnvoi;
	
	Historique historique;
	
	SignalReceptionUnicastController sruc;
	
	
	public Clavardage(Utilisateur utilisateur, int portEnvoi) throws SQLException, MessageInvalideException, DateInvalideException
	{
		this.utilisateur = utilisateur;
		this.portEnvoi = portEnvoi;
		this.sruc = null;
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
	
	public boolean EstValide()
	{
		return sruc != null;
	}
	
	public void ValiderClavardage(int portReception)
	{
		this.portReception = portReception; 
		sruc = new SignalReceptionUnicastController(portReception);
	}
}
