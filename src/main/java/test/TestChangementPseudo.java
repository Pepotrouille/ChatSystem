package test;

import controller.PseudoController;
import model.Utilisateur;
import model.TableUtilisateurs;


public class TestChangementPseudo {
	
	public static void main(String[] args){
		
		TableUtilisateurs test_table = new TableUtilisateurs();
		
		test_table.AjouterUtilisateur("001", "marie");
		test_table.AjouterUtilisateur("002", "bruno");
		test_table.AjouterUtilisateur("003", "tony");
		
		System.out.println("La table initiale : ");
		
		// Test d'affichage du tableau d'utilisateur => OK!
		for (Utilisateur u : test_table.GetListeUtilisateurs()) {
			System.out.println("Id : " + u.GetIP() + ", Pseudo : " + u.GetPseudo());
		}
		
		// Créer un pseudo controller pour la table initiale et un nouveau utilisateur
		
		// Test 1 : Bon pseudo => OK!
		Utilisateur new_user_1 = new Utilisateur("004", "danny");
		PseudoController pseudo_controller_1 = new PseudoController(test_table, new_user_1);
		pseudo_controller_1.changePseudo();
		
		test_table.AjouterUtilisateur(new_user_1);
		for (Utilisateur u : test_table.GetListeUtilisateurs()) {
			System.out.println("Id : " + u.GetIP() + ", Pseudo : " + u.GetPseudo());
		}
		
		// Test 2 : Pseudo exist déjà => OK!
		Utilisateur new_user_2 = new Utilisateur("005", "bruno");
		PseudoController pseudo_controller_2 = new PseudoController(test_table, new_user_2);
		pseudo_controller_2.changePseudo();
		
	}
}