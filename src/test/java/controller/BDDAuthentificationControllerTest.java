package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import controller.BDDAuthentificationController;

import java.sql.SQLException;

public class BDDAuthentificationControllerTest {
	
	//---TEST DE BDDAUTHENTIFICATION---// --->OK!!!
	/* Pour tester : créer d'abord la BDD avec TestCreerBaseAuthentification,
	puis tester les autres méthodes. N'oubliez pas de supprimer la BDD! */
	
	BDDAuthentificationController bdd_auth_controller = BDDAuthentificationController.GetInstance();
	
	@Test
	public void TestCreerBaseAuthentification()
	{
		try {
			assertEquals(true, bdd_auth_controller.CreerBaseAuthentification());
		}
		catch (SQLException e) {
			System.out.println("Erreur de la BDD : CreerBaseAuthentification échoué");
			e.printStackTrace();
		}
	}

	@Test
	public void TestAjouterAuthentification()
	{
		try {
			assertEquals(true, bdd_auth_controller.AjouterAuthentification("adam", "adam1234"));
			assertEquals(true, bdd_auth_controller.AjouterAuthentification("anthony", "ant6789"));
		}
		catch (SQLException e) {
			System.out.println("Erreur de la BDD : AjouterAuthentification échoué");
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestSupprimerAuthentification()
	{
		try {
			assertEquals(true, bdd_auth_controller.SupprimerAuthentification("adam"));
			assertEquals(true, bdd_auth_controller.SupprimerAuthentification("anthony"));
		}
		catch (SQLException e) {
			System.out.println("Erreur de la BDD : SupprimerAuthentification échoué");
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestSupprimerBaseAuthentification()
	{
		try {
			assertEquals(true, bdd_auth_controller.SupprimerBaseAuthentification());
		}
		catch (SQLException e) {
			System.out.println("Erreur de la BDD : SupprimerBaseAuthentification échoué");
			e.printStackTrace();
		}
	}
}
