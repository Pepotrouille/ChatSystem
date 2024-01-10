package model;

public class SignalMessageRecu  extends Signal{

	//---------------------------Attributs-------------------------
	
	private int noSequenceLocal;
	
	private String message;
	
	//---------------------------Méthodes-------------------------
	
	//----------Constructeur
	
	public SignalMessageRecu(int noSequence, String message) {
		noSequenceLocal = noSequence;
		this.message = message;
	}

	//----------Getters

	public int GetNumeroSequence() {
		return this.noSequenceLocal;
	}
	
	//----------Setters
	public void SetMessage(String message) {
		this.message = message;
	}
	
	
	//----------Autres Méthodes

	
	public String ToString() {
		return "W" + noSequenceLocal + message;
	}
}
