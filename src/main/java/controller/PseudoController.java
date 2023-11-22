package controller;

import model.TableUtilisateurs;
import model.Utilisateur;
import model.SignalChangementPseudo;
import java.util.Scanner;
import controller.SignalEnvoiBroadcastController;
import controller.SignalReceptionBroadcastController;

public class PseudoController {
	
	//---------------------------Attributs-------------------------
	private TableUtilisateurs table;
	private Utilisateur user;
	private SignalEnvoiBroadcastController sebc;
	
	//----------Constructeur
	public PseudoController(TableUtilisateurs table, Utilisateur user, SignalEnvoiBroadcastController sebc) {
		this.table = table;
		this.user = user;
		this.sebc = sebc;
	}
	
	//---------------------------Méthodes-------------------------
	public void changePseudo(String Pseudo) {
		
		boolean exist = true;
		
	    while (exist) {
	    	
	    	//Si aucun utilisateur dans la table initiale n'a ce pseudo, alors c'est OK
	    	if (!this.table.PseudoExiste(Pseudo)) {
	    		System.out.println("Utilisateur id " + user.GetIP() + ": Votre pseudo '" + Pseudo + 
	    				"' est OK! Vous pouvez accéder au site.");
	    		exist = false;
	    	}
	    	
	    	//Sinon envoyer un message au utilisateur pour lui demander de le refaire
	    	else {
	    		System.out.println("Utilisateur id " + user.GetIP() + ": Votre pseudo '" + Pseudo +
	    				"' existe déjà. Veuillez saisir un nouveau pseudo.");
	    		Scanner myObj = new Scanner(System.in); 
	    		System.out.println("Veuillez saisir un pseudo : ");
	    		Pseudo = myObj.nextLine();
	    		//myObj.close();
	    	}
	    }
	    
	    //Changer le pseudo de cet utilisateur si besoin
	    if (!exist) {
	    	this.user.SetPseudo(Pseudo);
	    	//this.table.SetPseudo(user.GetIP(), Pseudo);
	    }
		
		//Envoyer un message broadcast pour notifier les utilisateurs de ce changement
	    sebc.EnvoyerSignalChangementPseudo(Pseudo);
	    
	}
}