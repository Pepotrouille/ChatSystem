package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BDDMessageController;
import exceptions.DateInvalideException;
import exceptions.MessageInvalideException;

public class HistoriqueTest {

	Historique historique;
	
	static Message message1;
	static Message message2;
	static Message message3;
	static Message message4;
	static Message message5;
	
	String ipSrc = "10.11.12.13/";
	String ipDst = "192.138.67.76/";
	
	
	@BeforeAll
	public static void InitialisationMessages()
	{
		try {
			BDDMessageController.GetInstance().SupprimerBaseMessage();
		} catch (SQLException e) {
			System.out.println("Echec Suppression Base, continuer.");
		}
		try {
			BDDMessageController.GetInstance().CreerBaseMessage();
		} catch (SQLException e) {
			fail("Echec Creation Base");
			//e.printStackTrace();
		}

		//-----Test Création du message
		try {
			message1 = new Message("Hey", new MaDate(2023,12,21,9,19,50), true);
			message2 = new Message("Comment ça va ?", new MaDate(2023,12,21,9,25,50), true);
			message3 = new Message("Salut !", new MaDate(2023,12,21,9,20,50), false);
			message4 = new Message("Vieux Message...", new MaDate(2020,12,22,20,50,50), true);
			message5 = new Message("Réponse au ieux Message...", new MaDate(2020,12,22,20,51,59), false);
		} catch (MessageInvalideException e) {
			fail("Problème de création de message");
		} catch (DateInvalideException e) {
			fail("Problème de création des dates");
		}
	}
	

	@BeforeEach
	public void AvantChaqueTest() {
		historique = null;
	}
	
	
	
	@Test
	public void TestHistoriqueDebutVide() {
		
		//-----Test Création de l'historique
		TestCreationHistorique();
		
		assertEquals(0, historique.GetMessages().size());

		//-----Test d'ajout des messages
		TestAjoutMessage(message1);
		TestAjoutMessage(message2);
		TestAjoutMessage(message3);
		TestAjoutMessage(message4);
		TestAjoutMessage(message5);

		assertEquals(5, historique.GetMessages().size());
		
		//Test de l'ajout ordonnancé
		//Ordre attendu des messages : 2, 3, 1 ,5 ,4
		assertEquals(message2.GetContenu(), historique.GetMessages().get(0).GetContenu());
		assertEquals(message3.GetContenu(), historique.GetMessages().get(1).GetContenu());
		assertEquals(message1.GetContenu(), historique.GetMessages().get(2).GetContenu());
		assertEquals(message5.GetContenu(), historique.GetMessages().get(3).GetContenu());
		assertEquals(message4.GetContenu(), historique.GetMessages().get(4).GetContenu());

		//Vérification de l'attribution des ips sources
		assertEquals(true, historique.GetMessages().get(2).EstDeLUtilisateurActuel()); 	//Message1
		assertEquals(true, historique.GetMessages().get(0).EstDeLUtilisateurActuel()); 	//Message2
		assertEquals(false, historique.GetMessages().get(1).EstDeLUtilisateurActuel());	//Message3
		assertEquals(true, historique.GetMessages().get(4).EstDeLUtilisateurActuel());	//Message4
		assertEquals(false, historique.GetMessages().get(3).EstDeLUtilisateurActuel());	//Message5
		
	}
	
	
	public void TestHistoriqueDepuisBDD() {

		//-----Test Création de l'historique. La base de donnée est remplie par le test précédent.
		TestCreationHistorique();

		//Test de la récupération ordonnancée
		//Ordre attendu des messages : 2, 3, 1 ,5 ,4
		assertEquals(message2.GetContenu(), historique.GetMessages().get(0).GetContenu());
		assertEquals(message3.GetContenu(), historique.GetMessages().get(1).GetContenu());
		assertEquals(message1.GetContenu(), historique.GetMessages().get(2).GetContenu());
		assertEquals(message5.GetContenu(), historique.GetMessages().get(3).GetContenu());
		assertEquals(message4.GetContenu(), historique.GetMessages().get(4).GetContenu());
		
		//Vérification de la récupération des ips sources
		assertEquals(true, historique.GetMessages().get(2).EstDeLUtilisateurActuel()); 	//Message1
		assertEquals(true, historique.GetMessages().get(0).EstDeLUtilisateurActuel()); 	//Message2
		assertEquals(false, historique.GetMessages().get(1).EstDeLUtilisateurActuel());	//Message3
		assertEquals(true, historique.GetMessages().get(4).EstDeLUtilisateurActuel());	//Message4
		assertEquals(false, historique.GetMessages().get(3).EstDeLUtilisateurActuel());	//Message5
				
	}
	
	public void TestCreationHistorique()
	{
		try {
			historique = new Historique(ipSrc, ipDst);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Problème d'accès à la base de donnée");
		}catch (MessageInvalideException e) {
			fail("Problème de validité des messages");
		}catch (DateInvalideException e) {
			fail("Problème de validité des dates");
		}
	}
	
	public void TestAjoutMessage(Message message)
	{
		try {
			historique.AjouterMessage(message);
		} catch (DateInvalideException e) {
			fail("Problème de validité des dates");
		} catch (SQLException e) {
			fail("Problème d'accès à la base de donnée");
		}
	}
}
