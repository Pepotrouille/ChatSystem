package view;

import java.util.ArrayList;

import model.Clavardage;
import model.Message;

public class MessageTable {
	
	private Object[] contenu;

	//----------Constructeur
	
	public MessageTable(ArrayList<Object> listeMessages)
	{
		int i = 0;
		for (Object objet : listeMessages)
		{
			contenu[i] = (Message)objet;	
			i++;
		}
	}
	
	//----------Getters
	
	public Object[] GetContenu(){
		return contenu;
	}
}
