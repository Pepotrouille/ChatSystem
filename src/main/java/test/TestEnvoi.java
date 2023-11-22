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
    	broadcastController.ChangerPseudo("JeanUuuuuMichel");

    	broadcastController.DebugAfficherListe();
    	
    	broadcastController.Deconnexion();
    	
    	



    }

}
