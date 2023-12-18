package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exceptions.MessageInvalideException;

public class HistoriqueTest {

	Message message1;
	Message message2;
	Message message3;
	@BeforeAll
	public void InitialisationMessages()
	{
		
		
		//-----Test Création du message
		try {
			message1 = new Message("Hey", new Date(111111111111L), true);
			message2 = new Message("Comment ça va ?", new Date(111111119919L), true);
			message3 = new Message("Salut !", new Date(111111111919L), false);
		} catch (MessageInvalideException e) {
			fail("Problème de création de message");
		}
	}
	
	
	@Test
	public void TestHistorique() {
		
	}
	
	
	
}
