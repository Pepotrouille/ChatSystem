package model;

import exceptions.MessageInvalideException;

public class Message implements Comparable<Message>{
	//---------------------------Attributs-------------------------
	
	private String contenu;
	
	private MaDate date;
	
	private boolean deLUtilisateurActuel;
	
	//---------------------------Méthodes-------------------------
		
	//----------Constructeur
		
	public Message(String contenu, MaDate date, boolean deLUtilisateurActuel) throws MessageInvalideException{ 		
			if(contenu == null || contenu.equals("") || date == null || contenu.length() > 240)
			{
				throw new MessageInvalideException();
			}
			this.contenu=contenu;
			this.date = date;
			this.deLUtilisateurActuel = deLUtilisateurActuel;
	}
	
	
	public Message(String contenu, boolean deLUtilisateurActuel) throws MessageInvalideException{ 		
			if(contenu == null || contenu.equals(""))
			{
				throw new MessageInvalideException();
			}
			this.contenu=contenu;
			this.date = new MaDate();
			this.deLUtilisateurActuel = deLUtilisateurActuel;
	}

	//----------Getters
		
	public String GetContenu()
	{
		return this.contenu;
	}
	
	public MaDate GetDate()
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
		
	@Override
	public int compareTo(Message autreMessage) {
		return date.compareTo(autreMessage.GetDate());
	}
	
	
}
