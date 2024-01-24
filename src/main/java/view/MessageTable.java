package view;

import java.util.ArrayList;

import model.Message;

public class MessageTable {
	
	private Message[] contenu;

	//----------Constructeur
	
	public MessageTable(ArrayList<Message> listeMessages)
	{
		int i = 0;
		for (Message msg : listeMessages)
		{
			contenu[i] = msg;	
			i++;
		}
	}
	
	//----------Getters
	
	public Message[] GetContenu(){
		return contenu;
	}
}
