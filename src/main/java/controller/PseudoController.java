package controller;

import model.TableUtilisateurs;
import model.Utilisateur;
import exceptions.PseudoDejaPrisException;

public class PseudoController {
	
	//---SINGLETON--//
	
	//---------------------------Attributs-------------------------
	private Utilisateur user;
	
	//----------Constructeur
	public PseudoController(Utilisateur user) {
		this.user = user;
	}
	
	//---------------------------Méthodes-------------------------
	public Utilisateur getUtilisateur()
	{
		return this.user;
	}
	
	public void changePseudo(String Pseudo) throws PseudoDejaPrisException {
		
		
	    if (!TableUtilisateurs.GetInstance().PseudoExiste(Pseudo)) {
	    	this.user.SetPseudo(Pseudo);
			//Envoyer un message broadcast pour notifier les utilisateurs de ce changement
	    	SignalEnvoiBroadcastController.GetInstance().EnvoyerSignalChangementPseudo(Pseudo);
	    }
	    else {
	    	throw new PseudoDejaPrisException();
	    }
	    
	}
}