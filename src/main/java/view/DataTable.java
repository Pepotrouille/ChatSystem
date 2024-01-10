package view;

import java.util.ArrayList;
import model.Clavardage;
import model.Utilisateur;

public class DataTable {


	//---------------------------Attributs-------------------------
	
	
	
	private String[] nomsColonnes;
	private Object[][] contenu;
	
	public enum TypeDataTable {Utilisateur, Clavardage};

	//---------------------------Méthodes-------------------------
		
	//----------Constructeur
	
	
	
	public DataTable(ArrayList<Object> listeObjets, TypeDataTable typeDataTable)
	{
		
		int i = 0;
	
		switch(typeDataTable)
		{
		//------------------Cas Clavardage
		case Clavardage:
			nomsColonnes = new String[2];
			nomsColonnes[0] = "Utilisateur";
			nomsColonnes[1] = "Accéder Clavardage";
			

			contenu = new Object[listeObjets.size()][2];
			
			
			for (Object objet : listeObjets)
			{
				contenu[i][0] = ((Clavardage)objet).GetUserPseudo();
				contenu[i][1] = new OuvrirClavardageButton("Accéder au Clavardage", ((Clavardage)objet));
				
				i++;
			}
			
			break;
		
			
		//------------------Cas utilisateur
		case Utilisateur:
			nomsColonnes = new String[2];
			nomsColonnes[0] = "Utilisateur";
			nomsColonnes[1] = "Créer Clavardage";
			

			contenu = new Object[listeObjets.size()+1][2];
			
			contenu[i][0] = "Moi même";
			contenu[i][1] = new CreerClavardageButton("Clavarder avec moi-même", (Utilisateur.GetUtilisateurActuel()));
			i++;
			
			for (Object objet : listeObjets)
			{
				contenu[i][0] = ((Utilisateur)objet).GetPseudo();
				contenu[i][1] = new CreerClavardageButton("Nouveau Clavardage", ((Utilisateur)objet));
				
				i++;
			}
			break;
			
			
		}
		//------------------Fin Switch
	}
	
	
	
	

	//----------Getters
		
	public Object[][] GetContenu(){
		return contenu;
	}
	
	public String[] GetNomsColonnes(){
		return nomsColonnes;
	}
	//----------Setters
		
	//----------Autres Méthodes
}
