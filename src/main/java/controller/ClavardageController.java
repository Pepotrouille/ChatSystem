package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.ClavardageNonExistantException;
import exceptions.DateInvalideException;
import exceptions.MessageInvalideException;
import model.Clavardage;
import model.TableUtilisateurs;
import model.Utilisateur;

public class ClavardageController {

	public static ClavardageController self;
	
	//---------------------------Attributs-------------------------

	private ArrayList<Clavardage> clavardagesEnCours;
	
	private int prochainPortValide = 60000;
	
	
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
		clavardagesEnCours = new ArrayList<Clavardage>();
	}

	//----------Getters
	
	public ArrayList<Clavardage> GetClavardageEnCours()
	{
		return this.clavardagesEnCours;
	}
	
	//----------Setters
	
	//----------Autres Méthodes
	
	public Clavardage NouveauClavardage(Utilisateur utilisateur, int portEnvoi) throws SQLException, MessageInvalideException, DateInvalideException
	{
		System.out.println("Création d'un clavardage avec " + utilisateur.GetPseudo());
		Clavardage newClavardage = new Clavardage(utilisateur, portEnvoi);
		//clavardagesEnCours.add(newClavardage);
		//return newClavardage;
		return null;
	}
	
	public void FermerClavardage(String ipDestination) throws ClavardageNonExistantException
	{
		
		System.out.println("Fin du clavardage avec " + ipDestination);
		this.clavardagesEnCours.remove(GetClavardage(ipDestination));
		
	}
	
	public Clavardage GetClavardage(String ipDestination) throws ClavardageNonExistantException 
	{
		Clavardage clavardageARecuperer = null;
		
		for(Clavardage clavardage : this.clavardagesEnCours)
		{
			if(clavardage.GetIPDestination().equals(ipDestination))
			{
				clavardageARecuperer = clavardage;
			}
			
		}
		if(clavardageARecuperer == null)
		{
			throw new ClavardageNonExistantException();
		}
		
		return clavardageARecuperer;
	}
	
	public void ValiderClavardage(String ipDestination, int portReception) throws ClavardageNonExistantException
	{
		GetClavardage(ipDestination).ValiderClavardage(portReception);
	}
	
	
	public int GetProchainPortValide()
	{
		return prochainPortValide++;
	}
	
	
}
