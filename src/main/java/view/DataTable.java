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
			nomsColonnes[1] = "Clavarder";
			

			contenu = new Object[2][listeObjets.size()];
			
			
			for (Object objet : listeObjets)
			{
				contenu[0][i] = ((Clavardage)objet).GetUserPseudo();
				contenu[1][i] = new OuvrirClavardageButton(" ", ((Clavardage)objet));
				
				i++;
			}
			
			break;
		
			
		//------------------Cas utilisateur
		case Utilisateur:
			nomsColonnes = new String[2];
			nomsColonnes[0] = "Utilisateur";
			nomsColonnes[1] = "Clavarder";
			

			contenu = new Object[2][listeObjets.size()];
			
			for (Object objet : listeObjets)
			{
				contenu[0][i] = ((Utilisateur)objet).GetPseudo();
				contenu[1][i] = new CreerClavardageButton(" ", ((Utilisateur)objet));
				
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
