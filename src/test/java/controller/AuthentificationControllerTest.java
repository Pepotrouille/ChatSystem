package controller;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import exceptions.ErreurConnexionException;
import controller.AuthentificationController;

public class AuthentificationControllerTest {

	//---TEST D'AUTHENTIFICATION---//
	
	AuthentificationController auth_controller = AuthentificationController.GetInstance();
	
	@Test
	public void TestCreerCompte()
	{
		// Test CreerCompte
		try {
			auth_controller.GetBDDAuthentificationController().CreerBaseAuthentification();
			auth_controller.CreerCompte("adam", "adam1234");   // login "adam", mdp "adam1234"
			auth_controller.CreerCompte("anthony", "ant6789"); // login "anthony", mdp "ant6789"
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestAuthentifier()
	{
		// Test Authentifier
		try {
			auth_controller.Authentifier("admin", "admin");
			auth_controller.Authentifier("adam", "adam1234");   // login "adam", mdp "adam1234"
			// auth_controller.Authentifier("adam", "abc"); 
			auth_controller.Authentifier("anthony", "ant6789"); // login "anthony", mdp "ant6789"
			
			// Supprimer la BDD après les tests
			auth_controller.GetBDDAuthentificationController().SupprimerBaseAuthentification(); 
		}
		catch (SQLException e) {
			System.out.println("Il y a eu une erreur lors de la connexion avec la base de donnée. Veuillez réessayer.");
			e.printStackTrace();
		}	
		catch (ErreurConnexionException ex) {
			System.out.println("Problème de connexion : Le login et/ou le mot de passe est incorrect. Veuillez réeesayer.");
			ex.printStackTrace();
		}
	} 
}
