import view.MainView;
import controller.AuthentificationController;
import controller.BDDAuthentificationController;
import controller.BroadcastController;

public class Main {

	//---------------------------Attributs-------------------------

	
	
	
	
	
	//---------------------------Méthodes-------------------------
	
	public static void main(String[] args){
		

		BDDAuthentificationController.GetInstance().SupprimerBaseAuthentification();
		BDDAuthentificationController.GetInstance().CreerBaseAuthentification();
		
		MainView.AfficherAuthentification();


    }
	
	
	
}
