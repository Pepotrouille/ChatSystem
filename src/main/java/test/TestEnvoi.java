package test;

import controller.SignalEnvoiBroadcastController;
import controller.SignalEnvoiUnicastController;

public class TestEnvoi {
    
    
    public static void main(String[] args){
    	
    	SignalEnvoiBroadcastController sebc = new SignalEnvoiBroadcastController();

    	sebc.EnvoyerSignalConnexionBroadcast("PseudoZero");
    	
    	sebc.EnvoyerSignalChangementPseudo("Robert");
    	sebc.EnvoyerSignalChangementPseudo("Jean Michel");
    	
    	sebc.EnvoyerSignalDeconnexionBroadcast();
    	
    	/*
    	SignalEnvoiUnicastController seuc = new SignalEnvoiUnicastController();

    	seuc.EnvoyerSignalUnicast("Message 1", "127.0.0.1", 6000, 6001);
    	
    	seuc.EnvoyerSignalChangementPseudo("Robert");
    	seuc.EnvoyerSignalChangementPseudo("Jean Michel");
    	*/
    	



    }

}
