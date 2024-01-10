package model;

import java.sql.SQLException;

import controller.ClavardageController;
import controller.SignalReceptionUnicastController;
import exceptions.ClavardageNonExistantException;
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
		return utilisateur.GetIP();
	}
	
	public String GetUserPseudo() 
	{
		return utilisateur.GetPseudo();
	}

	public int GetPortReception() 
	{
		return portReception;
	}

	public int GetPortEnvoi() 
	{
		return portEnvoi;
	}
	
	public Historique GetHistorique()
	{
		return historique;
	}
	
	public boolean EstValide()
	{
		return sruc != null;
	}
	
	public void ValiderClavardage(int portReception)
	{
		this.portReception = portReception; 
		sruc = new SignalReceptionUnicastController(this);
	}
	
	public void CloreClavardage() throws ClavardageNonExistantException
	{
		ClavardageController.GetInstance().FermerClavardage(GetIPDestination());
	}
}
