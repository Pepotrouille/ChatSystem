package model;
import java.util.ArrayList;

public class TableUtilisateurs{
	
	
	//---------------------------Attributs-------------------------
	
	private ArrayList<Utilisateur> laListeUtilisateur;
	
	public static TableUtilisateurs self;
	
	//---------------------------Méthodes-------------------------
	
	public static TableUtilisateurs GetInstance()
	{
		if(self == null)
		{
			self = new TableUtilisateurs();
		}
		return self;
	}
	
	//----------Constructeur
	
	public TableUtilisateurs() { 				
		
		this.laListeUtilisateur = new ArrayList<Utilisateur>();
	}

	//----------Getters
	
	public ArrayList<Utilisateur> GetListeUtilisateurs() { 				
		
		return this.laListeUtilisateur;
	}
	
	//----------Setters

	//----------Autres Méthodes
	
	
	//---Ajouter Utilisateur
	
	public boolean AjouterUtilisateur(Utilisateur newUtilisateur) { 			// return false si déjà dans la liste	
		
		boolean utilisateurExiste = UtilisateurExiste(newUtilisateur.GetIP());
		if(!utilisateurExiste)
		{
			this.laListeUtilisateur.add(newUtilisateur);
		}
		return utilisateurExiste;
	}
	
	public boolean AjouterUtilisateur(String ip, String pseudo) { 			// return false si déjà dans la liste	
		
		boolean utilisateurExiste = UtilisateurExiste(ip);
		if(!utilisateurExiste)
		{
			this.laListeUtilisateur.add(new Utilisateur(ip, pseudo));
		}
		return utilisateurExiste;
	}

	//---Utilisateur ou Pseudo Existe
	
	public boolean UtilisateurExiste(Utilisateur newUtilisateur) { 		
		
		return this.laListeUtilisateur.contains(newUtilisateur);
		
	}
	
	public boolean UtilisateurExiste(String ip) { 		
		
		boolean utilisateurExiste = false;
		for (Utilisateur utilisateur : this.laListeUtilisateur) {
			if(utilisateur.GetIP().equals(ip)) {
				utilisateurExiste = true;
				break;
			}
		}
		return utilisateurExiste;
	}

	public boolean PseudoExiste(String pseudo) { 		
		
		boolean utilisateurExiste = false;
		for (Utilisateur utilisateur : this.laListeUtilisateur) {
			if(utilisateur.GetPseudo().equals(pseudo)) {
				utilisateurExiste = true;
				break;
			}
		}
		return utilisateurExiste;
	}
	
	//---Récupérer Utilisateur
	
	public Utilisateur GetUtilisateur(String ip) {

		
		Utilisateur utilisateurRecupere = null;
		for (Utilisateur utilisateur : this.laListeUtilisateur) {
			if(utilisateur.GetIP().equals(ip)) {
				utilisateurRecupere = utilisateur;
				break;
			}
		}
		return utilisateurRecupere;
	}
	
	//---Supprimer Utilisateur
	
	public boolean SupprimerUtilisateur(Utilisateur newUtilisateur) { 			// return false si absent dans la liste avant suppression
		
		boolean utilisateurExiste = UtilisateurExiste(newUtilisateur.GetIP());
		if(utilisateurExiste)
		{
			this.laListeUtilisateur.remove(newUtilisateur);
		}
		return utilisateurExiste;
	}
	
	public boolean SupprimerUtilisateur(String ip) { 							// return false si absent dans la liste avant suppression
																				
		Utilisateur utilisateur = GetUtilisateur(ip);
		boolean utilisateurExiste = (utilisateur != null);
		if (utilisateurExiste)
		{
			SupprimerUtilisateur(utilisateur);
		}
			
		
		return utilisateurExiste;
	}
	
	//---Manipuler Pseudo
	
	public boolean SetPseudo(String ip, String pseudo) { 		
		Utilisateur utilisateur = GetUtilisateur(ip);
		boolean utilisateurExiste = (utilisateur != null);
		if (utilisateurExiste)
		{
			utilisateur.SetPseudo(pseudo);
		}
			
		
		return utilisateurExiste;
	}
	
	public String GetPseudo(String ip) { 		
		
		Utilisateur utilisateur = GetUtilisateur(ip);
		
		return utilisateur.GetPseudo();
	}
	
	//---ToString
	
	public void AfficherListe()
	{
		System.out.println("------LISTE ACTUELLE------");
		int i = 1;
		for(Utilisateur user : this.laListeUtilisateur)
		{
			System.out.println("Utilisateur " + i++ + " : " + user.GetIP() + " ; " + user.GetPseudo());
			
		}
		System.out.println("--------------------------");
		
	}
	
}