package model;

public class SignalDeconnexionAutreUtilisateur extends Signal{
	
	
	//---------------------------Attributs-------------------------
	
	String ipAutre;
	
	//---------------------------Méthodes-------------------------
	
	//----------Constructeur
	
	public SignalDeconnexionAutreUtilisateur(String ipAutre) { 				
		this.ipAutre = ipAutre;
	}

	//----------Getters
	
	public String GetIPAutre() {
		return this.ipAutre;
	}
	
	//----------Setters
	
	public void SetIPAutre(String newIPAutre) {
		this.ipAutre = newIPAutre;
	}
	
	//----------Autres Méthodes

	
	public String ToString() {
		return "A" + ipAutre;
	}
}