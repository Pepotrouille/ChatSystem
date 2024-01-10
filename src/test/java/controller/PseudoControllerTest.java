package controller;

import org.junit.jupiter.api.Test;
import controller.PseudoController;
import controller.SignalEnvoiBroadcastController;
import model.TableUtilisateurs;
import model.Utilisateur;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class PseudoControllerTest {

	//---TEST DU PSEUDO CONTROLLER---//
	
	public void Instancier()
	{
		
	}
	
	@Test
	public void TestChangePseudo()
	{
		// Test Change Pseudo
		String ip;
		Utilisateur user_test = null;
		try {
			ip = InetAddress.getLocalHost().toString();
			user_test = new Utilisateur(ip, "alpha");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		TableUtilisateurs table_user = TableUtilisateurs.GetInstance();
		PseudoController pseudo_controller = new PseudoController(table_user, user_test, SignalEnvoiBroadcastController.GetInstance());
		
		// Créer quelques utilisateurs pour tester
		Utilisateur user_1 = new Utilisateur("ip_user_1", "charlie");
		Utilisateur user_2 = new Utilisateur("ip_user_2", "delta");
		Utilisateur user_3 = new Utilisateur("ip_user_3", "yankee");
		table_user.AjouterUtilisateur(user_test);
		table_user.AjouterUtilisateur(user_1);
		table_user.AjouterUtilisateur(user_2);
		table_user.AjouterUtilisateur(user_3);
		
		// Cas 1 : Aucun utilisateur utilise ce pseudo
		pseudo_controller.changePseudo("tango");
		pseudo_controller.changePseudo("zulu");
		
		// Cas 2 : Un utilisateur utilise ce pseudo
		pseudo_controller.changePseudo("charlie");
		pseudo_controller.changePseudo("delta");
	}
}
