package model;

import java.sql.SQLException;

import controller.ClavardageController;
import controller.SignalEnvoiUnicastController;
import controller.SignalReceptionUnicastController;
import exceptions.ClavardageNonExistantException;
import exceptions.DateInvalideException;
import exceptions.MessageInvalideException;

public class Clavardage {

	Utilisateur utilisateur;
	
	int portReceptionMoi;
	
	int portReceptionAutre;
	
	Historique historique;
	
	SignalReceptionUnicastController sruc;
	
	
	public Clavardage(Utilisateur utilisateur, int portReception) throws SQLException, MessageInvalideException, DateInvalideException
	{
		this.utilisateur = utilisateur;
		this.portReceptionMoi = portReception;
		this.portReceptionAutre = -1;
		this.historique = new Historique(Utilisateur.GetUtilisateurActuel().GetIP(), utilisateur.GetIP());
		System.out.println("AAAA");
		this.sruc = new SignalReceptionUnicastController(this);
		this.sruc.start();
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
		return portReceptionMoi;
	}

	public int GetPortEnvoi() 
	{
		return portReceptionAutre;
	}
	
	public Historique GetHistorique()
	{
		return historique;
	}

	public SignalReceptionUnicastController GetSignalReceptionUnicastController()
	{
		return sruc;
	}
	
	public boolean EstValide()
	{
		return portReceptionAutre != -1;
	}
	
	public void ValiderClavardage(int portReceptionAutre)
	{
		this.portReceptionAutre = portReceptionAutre; 
		System.out.println("Validation du clavardage. Port réception interlocuteur : " + portReceptionAutre + ", et mon port de réception : " + portReceptionMoi);
	}
	
	public void CloreClavardage() throws ClavardageNonExistantException
	{
		ClavardageController.GetInstance().FermerClavardage(GetIPDestination());
	}
	
	public void EnvoyerMessage(String message)
	{
		SignalEnvoiUnicastController.GetInstance().EnvoyerSignalUnicast(new SignalMessage(message), utilisateur.GetIP(), portReceptionAutre);
	}
}
