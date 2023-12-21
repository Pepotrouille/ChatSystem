package model;

public class Clavardage {

	Utilisateur utilisateur;
	
	int portSource;
	
	int portDest;
	
	Historique historique;
	
	boolean estValide;
	
	
	public Clavardage(Utilisateur utilisateur, int portSource, int portDest, Historique historique)
	{
		
		this.estValide = false;
	}
	
	public String GetIPDestination() 
	{
		return "Test";
	}
	
	public String GetUserPseudo() 
	{
		return "Test";
	}
}
