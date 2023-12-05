import view.MainView;
import controller.BroadcastController;

public class Main {

	//---------------------------Attributs-------------------------

	
	
	
	
	
	//---------------------------Méthodes-------------------------
	
	public static void main(String[] args){
		
    	BroadcastController broadcastController = new BroadcastController();

		MainView mainView = new MainView(broadcastController);
    	
    	


    }
	
	
	
}
