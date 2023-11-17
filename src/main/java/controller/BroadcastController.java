package controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import model.TableUtilisateurs;
import model.Utilisateur;

public class BroadcastController{
	
	private SignalReceptionBroadcastController srbc;
	
	private SignalEnvoiBroadcastController sebc;
	
	private TableUtilisateurs tableUtilisateurs;
	
	private PseudoController pseudoController;
	
	//private PseudoController pseudoController;

	private int generalPortEnvoi;
	
	private int generalPortReception;
	
	private Utilisateur soiMeme; //Utilisateur temporaire
	
	public BroadcastController() {

		this.generalPortEnvoi = 5000;
		this.generalPortReception = 5001;

		tableUtilisateurs = new TableUtilisateurs();
		
		sebc = new SignalEnvoiBroadcastController(generalPortEnvoi, generalPortReception);

		srbc = new SignalReceptionBroadcastController(generalPortEnvoi, generalPortReception, tableUtilisateurs);
        srbc.start();
        System.out.println("Lancement du Thread de réception");
        
        try {
			this.soiMeme = new Utilisateur (InetAddress.getLocalHost().getAddress().toString(),"null");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        pseudoController = new PseudoController(tableUtilisateurs, soiMeme, sebc);//Utilisateur temporaire

        //this.pseudoController = pseudoController;
		
	}
	
	public boolean Connexion(String pseudo) // return true si le pseudo est okay
	{
		//Utiliser le pseudoController pour vérifier
		sebc.EnvoyerSignalConnexionBroadcast(pseudo);
		return true;
	}


	public void ChangerPseudo(String pseudo) // return true si le pseudo est okay
	{
		//Utiliser le pseudoController pour vérifier
		pseudoController.changePseudo(generalPortEnvoi, generalPortReception);
	}
	
	public void Deconnexion()
	{
		sebc.EnvoyerSignalDeconnexionBroadcast();
	}
	
	
	
	
	
	
}