package controller;

import java.util.ArrayList;

import exceptions.ClavardageNonExistantException;
import model.TableUtilisateurs;

public class ClavardageController {

	public static ClavardageController self;
	
	//---------------------------Attributs-------------------------

	ArrayList<String> clavardagesEnCours;
	
	
	
	
	//---------------------------Méthodes-------------------------
	public static ClavardageController GetInstance()
    {
    	if(ClavardageController.self == null)
    	{
    		ClavardageController.self = new ClavardageController();
    	}
    	return ClavardageController.self;
    }

	//----------Constructeur
	
	
	private ClavardageController()
	{
		clavardagesEnCours = new ArrayList<String>();
	}

	//----------Getters
	
	public ArrayList<String> GetClavardageEnCours()
	{
		return this.clavardagesEnCours;
	}
	
	//----------Setters
	
	//----------Autres Méthodes
	
	public void NouveauClavardage(String ipDestination)
	{
		System.out.println("Création d'un clavardage avec " + ipDestination);
		clavardagesEnCours.add(ipDestination);
	}
	
	public void FermerClavardage(String ipDestination) throws ClavardageNonExistantException
	{
		
		System.out.println("Fin du clavardage avec " + ipDestination);
		if(!clavardagesEnCours.remove(ipDestination))
		{
			throw new ClavardageNonExistantException();
		}
		
	}
	
	
}
