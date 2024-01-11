package test;


import controller.BroadcastController;
//import controller.SignalEnvoiBroadcastController;
//import controller.SignalEnvoiUnicastController;


public class TestEnvoi {
	
    public static void main(String[] args){
    	
    	BroadcastController broadcastController = BroadcastController.GetInstance();

    	
    	broadcastController.Connexion("PseudoZero");

    	broadcastController.ChangerPseudo("Robert");
    	broadcastController.ChangerPseudo("JeanUuuuuMichel");

    	broadcastController.DebugAfficherListe();
    	
    	broadcastController.Deconnexion();
    	
    	



    }

}
