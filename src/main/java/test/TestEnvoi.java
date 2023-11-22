package test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.net.DatagramSocket;

import controller.BroadcastController;
//import controller.SignalEnvoiBroadcastController;
//import controller.SignalEnvoiUnicastController;

public class TestEnvoi {
    
    
    public static void main(String[] args){
    	
    	BroadcastController broadcastController = new BroadcastController();

    	
    	broadcastController.Connexion("PseudoZero");

    	broadcastController.ChangerPseudo("Robert");
    	broadcastController.ChangerPseudo("JeanMichel");

    	broadcastController.DebugAfficherListe();
    	
    	//broadcastController.Deconnexion();
    	
    	
    	/*
    	SignalEnvoiBroadcastController sebc = new SignalEnvoiBroadcastController(5000,5001);

    	sebc.EnvoyerSignalConnexionBroadcast("PseudoZero");
    	
    	sebc.EnvoyerSignalChangementPseudo("Robert");
    	sebc.EnvoyerSignalChangementPseudo("Jean Michel");
    	
    	sebc.EnvoyerSignalDeconnexionBroadcast();
    	*/
    	/*
    	SignalEnvoiUnicastController seuc = new SignalEnvoiUnicastController();

    	seuc.EnvoyerSignalUnicast("Message 1", "127.0.0.1", 6000, 6001);
    	
    	seuc.EnvoyerSignalChangementPseudo("Robert");
    	seuc.EnvoyerSignalChangementPseudo("Jean Michel");
    	*/
    	



    }

}
