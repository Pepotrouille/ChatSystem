package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import controller.BDDMessageController;
import exceptions.DateInvalideException;
import exceptions.MessageInvalideException;

public class Historique {

	private String ipsrc;
	private String ipdst;
	private ArrayList<Message> messages;
	
	public Historique(String ipsrc, String ipdst) throws SQLException, MessageInvalideException, DateInvalideException {
		this.ipsrc = ipsrc;
		this.ipdst = ipdst;
		this.messages = BDDMessageController.GetInstance().GetMessageHistorique(ipsrc, ipdst);
		TrierMessages() ;
	}
	
	
	public String GetIPSource()
	{
		return this.ipsrc;
	}

	public String GetIPDestination()
	{
		return this.ipdst;
	}

	public ArrayList<Message> GetMessages()
	{
		return this.messages;
	}

	public void AjouterMessage(Message message) throws DateInvalideException, SQLException
	{
		//Ajout du message dans liste ordonnée
		int i = 0;
		for(Message messageCourant : messages)
		{
			if(message.compareTo(messageCourant)<0)
			{
				i++;
			}
			else {
				break;
			}
		}
		this.messages.add(i, message);
		BDDMessageController.GetInstance().AjouterMessage(message, this);
	}

	private void TrierMessages() 
	{
		Collections.sort(messages);
	}
}
