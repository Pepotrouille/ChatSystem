package controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import exceptions.EchecManipulationBDDException;
import exceptions.ErreurConnexionException;

public class AuthentificationControllerTest {

	//---TEST D'AUTHENTIFICATION---//
	
	static AuthentificationController authController = AuthentificationController.GetInstance();
	
	@BeforeAll
	public static void TestCreerCompte()
	{
		// Test CreerCompte
		try {
			authController.GetBDDAuthentificationController().CreerBaseAuthentification();
			authController.CreerCompte("adam", "adam1234");   // login "adam", mdp "adam1234"
			authController.CreerCompte("anthony", "ant6789"); // login "anthony", mdp "ant6789"
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail("Problème lors de la connexion à la base de donnée");
		}
		catch (EchecManipulationBDDException e) {
			e.printStackTrace();
			fail("Problème lors de l'ajout de compte à la base de donnée");
		}
	}
	
	@Test
	public void TestAuthentifier()
	{
		//--------------- Test Authentification réussie ---------------
		//--------------- Test 1
		try {
			authController.Authentifier("adam", "adam1234");   // login "adam", mdp "adam1234"
		}
		catch (SQLException e) {
			fail("Problème lors de la connexion à la base de donnée");
			e.printStackTrace();
		}	
		catch (ErreurConnexionException ex) {
			fail("Validation réussite déclarée comme échouée");
			ex.printStackTrace();
		}

		//--------------- Test 2
		try {
			authController.Authentifier("anthony", "ant6789");   // login "adam", mdp "adam1234"
		}
		catch (SQLException e) {
			fail("Problème lors de la connexion à la base de donnée");
			e.printStackTrace();
		}	
		catch (ErreurConnexionException ex) {
			fail("Validation réussite déclarée comme échouée");
			ex.printStackTrace();
		}
	
		//--------------- Test Authentification échouée ---------------
		//--------------- Test 1
	
		try {
			authController.Authentifier("anthony", "mauvaismotdepasse");   // login "adam", mdp "adam1234"
			fail("Validation échouée déclarée comme réussite");
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail("Problème lors de la connexion à la base de donnée");
		}	
		catch (ErreurConnexionException ex) {
		}
		
	
	
		try {
			authController.Authentifier("mauvaislogin", "ant6789");   // login "adam", mdp "adam1234"
			fail("Validation échouée déclarée comme réussite");
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail("Problème lors de la connexion à la base de donnée");
		}	
		catch (ErreurConnexionException ex) {
		}
	}
	
	@AfterAll
	public static void AfterAllTests()
	{

		try {
			authController.GetBDDAuthentificationController().SupprimerBaseAuthentification();
			authController.GetBDDAuthentificationController().CreerBaseAuthentification();
		} 
		catch (SQLException e) {
			fail("Problème lors de la connexion à la base de donnée");
			e.printStackTrace();
		}
		catch (EchecManipulationBDDException e) {
			e.printStackTrace();
			fail("Problème lors de l'ajout de compte à la base de donnée");
		}
	}
}
