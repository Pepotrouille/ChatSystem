package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.*;

import exceptions.MessageInvalideException;

public class MessageTest {

	@Test
	public void MessageTestValide()
	{
		String contenu = "Message de Test";
		MaDate date = new MaDate();
		boolean deMoi = true;
		Message message = null;
		
		//-----Test Création du message
		try {
			message = new Message(contenu, date, deMoi);
		} catch (MessageInvalideException e) {
			fail("Problème de création de message");
		}

		//-----Test Contenu
		assertEquals(contenu, message.GetContenu());

		//-----Test Date
		assertEquals(date, message.GetDate());

		//-----Test Date
		assertEquals(deMoi, message.EstDeLUtilisateurActuel());
	}
	
	@Test
	public void MessageTestInvalideContenuNull()
	{
		String contenu = null;
		MaDate date = new MaDate();
		boolean deMoi = true;
		
		//-----Test Création du message
		try {
			new Message(contenu, date, deMoi);
			fail("Contenu nul non attrapé");
		} catch (MessageInvalideException e) {
		}
	}
	
	@Test
	public void MessageTestInvalideContenuVide()
	{
		String contenu = "";
		MaDate date = new MaDate();
		boolean deMoi = true;
		
		//-----Test Création du message
		try {
			new Message(contenu, date, deMoi);
			fail("Contenu vide non attrapé");
		} catch (MessageInvalideException e) {
		}
	}
	
	@Test
	public void MessageTestInvalideDateNull()
	{
		String contenu = "Message de Test";
		MaDate date = null;
		boolean deMoi = true;
		
		//-----Test Création du message
		try {
			new Message(contenu, date, deMoi);
			fail("Date nulle non attrapé");
		} catch (MessageInvalideException e) {
		}
	}
	
}
