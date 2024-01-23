package controller;

import java.net.DatagramSocket;
import java.net.InetAddress;

import exceptions.PseudoDejaPrisException;
import model.TableUtilisateurs;
import model.Utilisateur;

public class BroadcastController{ //---------Tout passer en statique

	private static BroadcastController self;
	
	//---SINGLETON--//
	
	private SignalReceptionBroadcastController srbc;
	
	private SignalEnvoiBroadcastController sebc;
	
	private TableUtilisateurs tableUtilisateurs;
	
	private PseudoController pseudoController;
	
	//private PseudoController pseudoController;

	public static int generalPortEnvoi;
	
	public static int generalPortReception;
	
	
	
	
	private BroadcastController() {

		generalPortEnvoi = 5080;
		generalPortReception = 5081;

		tableUtilisateurs = TableUtilisateurs.GetInstance();
		
        try (DatagramSocket datagramSocket = new DatagramSocket()) {

        	datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
        	System.out.println(datagramSocket.getLocalAddress().getHostAddress().toString());
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sebc = SignalEnvoiBroadcastController.GetInstance();

		srbc = SignalReceptionBroadcastController.GetInstance();
		
        System.out.println("Lancement du Thread de réception");
        
        
        pseudoController = new PseudoController(Utilisateur.GetUtilisateurActuel());//Utilisateur temporaire

        //this.pseudoController = pseudoController;
		
	}
	
	public static BroadcastController GetInstance()
	{
		if(BroadcastController.self == null)
    	{
			BroadcastController.self = new BroadcastController();
    	}
    	return BroadcastController.self;
	}
	
	
	//----------
	
	public void Connexion(String pseudo) 
	{
		sebc.EnvoyerSignalConnexionBroadcast(pseudo);
	}


	public void ChangerPseudo(String pseudo) throws PseudoDejaPrisException 
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