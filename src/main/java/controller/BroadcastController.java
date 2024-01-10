package controller;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import model.TableUtilisateurs;
import model.Utilisateur;

public class BroadcastController{ //---------Tout passer en statique
	
	//---SINGLETON--//
	
	private SignalReceptionBroadcastController srbc;
	
	private SignalEnvoiBroadcastController sebc;
	
	private TableUtilisateurs tableUtilisateurs;
	
	private PseudoController pseudoController;
	
	//private PseudoController pseudoController;

	public static int generalPortEnvoi;
	
	public static int generalPortReception;
	
	public BroadcastController() {

		generalPortEnvoi = 5080;
		generalPortReception = 5081;

		tableUtilisateurs = new TableUtilisateurs();
		
        try {

        	final DatagramSocket datagramSocket = new DatagramSocket();
        	datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
        	System.out.println(datagramSocket.getLocalAddress().getHostAddress().toString());
        	
			Utilisateur.utilisateurActuel = new Utilisateur (datagramSocket.getLocalAddress().getHostAddress().toString(),"null");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sebc = new SignalEnvoiBroadcastController();

		srbc = SignalReceptionBroadcastController.GetInstance(tableUtilisateurs, Utilisateur.utilisateurActuel.GetIP(), pseudoController);
		
        System.out.println("Lancement du Thread de réception");
        
        
        pseudoController = new PseudoController(tableUtilisateurs, Utilisateur.utilisateurActuel, sebc);//Utilisateur temporaire

        //this.pseudoController = pseudoController;
		
	}
	
	public void Connexion(String pseudo) 
	{
		sebc.EnvoyerSignalConnexionBroadcast(pseudo);
	}


	public void ChangerPseudo(String pseudo) 
	{
		pseudoController.changePseudo(pseudo);
	}
	
	
	public void Deconnexion()
	{
		sebc.EnvoyerSignalDeconnexionBroadcast();
		srbc.CloreReception();
	}
	
	
	
	//-------TEMP
	public void DebugAfficherListe() {
		this.tableUtilisateurs.AfficherListe();
	}
	//---------
	
	
	
	
}