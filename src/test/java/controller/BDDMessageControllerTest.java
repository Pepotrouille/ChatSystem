package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import exceptions.DateInvalideException;
import exceptions.MessageInvalideException;
import model.Historique;
import model.MaDate;
import model.Message;

import java.sql.SQLException;

public class BDDMessageControllerTest {
	
	private BDDMessageController bdd_message_controller = BDDMessageController.GetInstance();
	
	@Test
	public void TestCreerBaseMessage()
	{
		try {
			assertEquals(true, bdd_message_controller.CreerBaseMessage());
		}
		catch (SQLException e) {
			System.out.println("Erreur de la BDD : CreerBaseMessage échoué");
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestAjouterMessage()
	{
		try {
			Historique hist = new Historique("10.1.12.14", "10.1.12.18");
			Message m1 = new Message("Hello", new MaDate(), true);
			Message m2 = new Message("Nice to meet you!", new MaDate(), true);
			bdd_message_controller.AjouterMessage(m1, hist);
			bdd_message_controller.AjouterMessage(m2, hist);
			
			assertEquals(2, bdd_message_controller.GetMessageHistorique("10.1.12.14", "10.1.12.18").size());
			assertEquals(m1.GetContenu(), bdd_message_controller.GetMessageHistorique("10.1.12.14", "10.1.12.18").get(0).GetContenu());
		}
		catch (SQLException e1) {
			System.out.println("SQLException");
			e1.printStackTrace();
		}
		catch (MessageInvalideException e2) {
			System.out.println("MessageInvalideException");
			e2.printStackTrace();
		}
		catch (DateInvalideException e3) {
			System.out.println("DateInvalideException");
			e3.printStackTrace();
		}
	}
	
	@Test
	public void TestSupprimerBaseMessage()
	{
		try {
			assertEquals(true, bdd_message_controller.SupprimerBaseMessage());
		}
		catch (SQLException e) {
			System.out.println("Erreur de la BDD : SupprimerBaseMessage échoué");
			e.printStackTrace();
		}
	}
}