package model;

import java.util.Date;

import exceptions.MessageInvalideException;

public class Message {
	//---------------------------Attributs-------------------------
	
	private String contenu;
	
	private Date date;
	
	private boolean deLUtilisateurActuel;
	
	//---------------------------Méthodes-------------------------
		
	//----------Constructeur
		
	public Message(String contenu, Date date, boolean deLUtilisateurActuel) throws MessageInvalideException{ 		
			if(contenu == null || contenu.equals("") || date == null )
			{
				throw new MessageInvalideException();
			}
			this.contenu=contenu;
			this.date = date;
			this.deLUtilisateurActuel = deLUtilisateurActuel;
	}

	//----------Getters
		
	public String GetContenu()
	{
		return this.contenu;
	}
	
	public Date GetDate()
	{
		return this.date;
	}
	
	public boolean EstDeLUtilisateurActuel()
	{
		return this.deLUtilisateurActuel;
	}
	//----------Setters
		
	//Pas de raison de modifier les informations d'un message après envoi
	
	//----------Autres Méthodes
		

}
