package controller;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import model.TableUtilisateurs;
import model.Utilisateur;

public class BroadcastController{ //---------Tout passer en statique
	
	private SignalReceptionBroadcastController srbc;
	
	private SignalEnvoiBroadcastController sebc;
	
	private TableUtilisateurs tableUtilisateurs;
	
	private PseudoController pseudoController;
	
	//private PseudoController pseudoController;

	public static int generalPortEnvoi;
	
	public static int generalPortReception;
	
	public static Utilisateur soiMeme; //Utilisateur temporaire
	
	public BroadcastController() {

		generalPortEnvoi = 5000;
		generalPortReception = 5001;

		tableUtilisateurs = new TableUtilisateurs();
		
        try {

        	final DatagramSocket datagramSocket = new DatagramSocket();
        	datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
        	System.out.println(datagramSocket.getLocalAddress().getHostAddress().toString());
        	
			this.soiMeme = new Utilisateur (datagramSocket.getLocalAddress().getHostAddress().toString(),"null");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sebc = new SignalEnvoiBroadcastController();

		srbc = new SignalReceptionBroadcastController(tableUtilisateurs, soiMeme.GetIP());
        srbc.start();
        System.out.println("Lancement du Thread de réception");
        
        
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
		pseudoController.changePseudo(generalPortEnvoi, generalPortReception, pseudo);
	}
	
	public void Deconnexion()
	{
		sebc.EnvoyerSignalDeconnexionBroadcast();
	}
	
	
	
	
	
	
}