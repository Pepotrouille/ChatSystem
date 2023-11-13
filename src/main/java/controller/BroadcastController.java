package controller;

import model.TableUtilisateurs;

public class BroadcastController{
	
	private SignalReceptionBroadcastController srbc;
	
	private SignalEnvoiBroadcastController sebc;
	
	private TableUtilisateurs tableUtilisateurs;
	
	//private PseudoController pseudoController;

	private int generalPortEnvoi;
	
	private int generalPortReception;
	
	public BroadcastController() {

		this.generalPortEnvoi = 5000;
		this.generalPortReception = 5001;

		tableUtilisateurs = new TableUtilisateurs();
		
		sebc = new SignalEnvoiBroadcastController(generalPortEnvoi, generalPortReception);

		srbc = new SignalReceptionBroadcastController(generalPortEnvoi, generalPortReception, tableUtilisateurs);
        srbc.start();
        System.out.println("Lancement du Thread de réception");

        //this.pseudoController = pseudoController;
		
	}
	
	public boolean Connexion(String pseudo) // return true si le pseudo est okay
	{
		//Utiliser le pseudoController pour vérifier
		sebc.EnvoyerSignalConnexionBroadcast(pseudo);
		return true;
	}


	public boolean ChangerPseudo(String pseudo) // return true si le pseudo est okay
	{
		//Utiliser le pseudoController pour vérifier
		sebc.EnvoyerSignalChangementPseudo(pseudo);
		return true;
	}
	
	public void Deconnexion()
	{
		sebc.EnvoyerSignalDeconnexionBroadcast();
	}
	
	
	
	
	
	
}