package test;

import controller.SignalEnvoiBroadcastController;

public class TestEnvoi {
    
    
    public static void main(String[] args){

    	SignalEnvoiBroadcastController sebc = new SignalEnvoiBroadcastController();

    	sebc.EnvoyerSignalConnexionBroadcast("PseudoZero");
    	
    	sebc.EnvoyerSignalChangementPseudo("Robert");
    	sebc.EnvoyerSignalChangementPseudo("Jean Michel");
    	
    	sebc.EnvoyerSignalDeconnexionBroadcast();
    	




    }

}
