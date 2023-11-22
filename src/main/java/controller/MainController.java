package controller;

//import view.MainView;

public class MainController {

	//---------------------------Attributs-------------------------

	
	
	
	
	
	//---------------------------Méthodes-------------------------
	
	public static void main(String[] args){
		
    	BroadcastController broadcastController = new BroadcastController();

		//MainView mainView = new Mainview(broadcastController);
    	
    	broadcastController.Connexion("PseudoZero");

    	broadcastController.ChangerPseudo("Robert");
    	broadcastController.ChangerPseudo("JeanMichel");

    	broadcastController.DebugAfficherListe();
    	
    	broadcastController.Deconnexion();
    	
    	



    }
	
	
	
}
