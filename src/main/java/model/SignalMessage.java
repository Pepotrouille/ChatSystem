package model;

public class SignalMessage extends Signal{
	
	
	//---------------------------Attributs-------------------------
	
	private static int noSequence;
	
	private int noSequenceLocal;
	
	private String message;
	
	//---------------------------Méthodes-------------------------
	
	//----------Constructeur
	
	public SignalMessage(String message) { 				
		if(noSequence >99 || noSequence<10)
		{
			noSequence = 10;
		}
		noSequenceLocal = noSequence++;
		this.message = message;
	}

	//----------Getters
	public String GetMessage() {
		return this.message;
	}

	public int GetNumeroSequence() {
		return this.noSequenceLocal;
	}

	public static int GetNumeroSequenceActuel() {
		return noSequence;
	}
	
	//----------Setters
	public void SetMessage(String message) {
		this.message = message;
	}
	
	
	//----------Autres Méthodes

	
	public String ToString() {
		return "M" + noSequenceLocal + this.message;
	}

}