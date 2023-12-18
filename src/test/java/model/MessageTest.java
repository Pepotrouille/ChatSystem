package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.*;

import exceptions.MessageInvalideException;

import java.util.Date;
public class MessageTest {

	@Test
	public void MessageTestValide()
	{
		String contenu = "Message de Test";
		Date date = new Date();
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
		Date date = new Date();
		boolean deMoi = true;
		Message message = null;
		
		//-----Test Création du message
		try {
			message = new Message(contenu, date, deMoi);
			fail("Contenu nul non attrapé");
		} catch (MessageInvalideException e) {
		}
	}
	
	@Test
	public void MessageTestInvalideContenuVide()
	{
		String contenu = "";
		Date date = new Date();
		boolean deMoi = true;
		Message message = null;
		
		//-----Test Création du message
		try {
			message = new Message(contenu, date, deMoi);
			fail("Contenu vide non attrapé");
		} catch (MessageInvalideException e) {
		}
	}
	
	@Test
	public void MessageTestInvalideDateNull()
	{
		String contenu = "Message de Test";
		Date date = null;
		boolean deMoi = true;
		Message message = null;
		
		//-----Test Création du message
		try {
			message = new Message(contenu, date, deMoi);
			fail("Date nulle non attrapé");
		} catch (MessageInvalideException e) {
		}
	}
	
}
