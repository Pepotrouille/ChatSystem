package model;

public class SignalMessage extends Signal{
	
	
	//---------------------------Attributs-------------------------
	
	private String message;
	
	//---------------------------Méthodes-------------------------
	
	//----------Constructeur
	
	public SignalMessage(String message) { 				
		
		this.message = message;
	}

	//----------Getters
	public String GetMessage() {
		return this.message;
	}
	
	//----------Setters
	public void SetMessage(String message) {
		this.message = message;
	}
	
	
	//----------Autres Méthodes

	
	public String ToString() {
		return "M" + this.message;
	}
}