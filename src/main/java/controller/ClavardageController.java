package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.ClavardageDejaCree;
import exceptions.ClavardageNonExistantException;
import exceptions.DateInvalideException;
import exceptions.MessageInvalideException;
import model.Clavardage;
import model.SignalNouveauClavardage;
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
	
	public Clavardage NouveauClavardage(Utilisateur utilisateur, int portEnvoi) throws SQLException, MessageInvalideException, DateInvalideException, ClavardageDejaCree
	{
		Clavardage newClavardage = null;
		try
		{
			newClavardage = GetClavardage(utilisateur.GetIP());
			throw new ClavardageDejaCree();
			
			
		}
		catch(ClavardageNonExistantException e)
		{
			System.out.println("Création d'un clavardage avec l'utilisateur : " + utilisateur.GetPseudo());
			newClavardage = new Clavardage(utilisateur, portEnvoi);
			SignalEnvoiUnicastController.GetInstance().EnvoyerSignalUnicast(new SignalNouveauClavardage(portEnvoi), utilisateur.GetIP(), BroadcastController.generalPortReception);;
			this.clavardagesEnCours.add(newClavardage);
		}
		
		return newClavardage;
	}

	public Clavardage NouveauClavardage(Utilisateur utilisateur) throws SQLException, MessageInvalideException, DateInvalideException, ClavardageDejaCree
	{
		return NouveauClavardage(utilisateur, GetProchainPortValide());
	}

	public Clavardage NouveauClavardageValide(Utilisateur utilisateur, int portEnvoi, int portReception) throws SQLException, MessageInvalideException, DateInvalideException, ClavardageDejaCree {
		Clavardage newClavardage = null;
		try
		{
			newClavardage = GetClavardage(utilisateur.GetIP());
			
			if(utilisateur.GetIP().equals("127.0.0.1"))
			{
				System.out.println("Création d'un clavardage avec moi même.");
				newClavardage = new Clavardage(utilisateur, portEnvoi);
				newClavardage.ValiderClavardage(portReception);
				this.clavardagesEnCours.add(newClavardage);
			}
			else {
				throw new ClavardageDejaCree();
			}
			
			
			
		}
		catch(ClavardageNonExistantException e)
		{
			System.out.println("Création d'un clavardage avec utilisateur : " + utilisateur.GetPseudo());
			newClavardage = new Clavardage(utilisateur, portEnvoi);
			newClavardage.ValiderClavardage(portReception);
			this.clavardagesEnCours.add(newClavardage);
		}
		return newClavardage;
	}
	
	public void FermerClavardage(String ipDestination) throws ClavardageNonExistantException
	{
		
		System.out.println("Fin du clavardage avec " + ipDestination);
		this.clavardagesEnCours.remove(GetClavardage(ipDestination));
		
	}
	
	public void FermerClavardages() throws ClavardageNonExistantException
	{
		for(Clavardage clavardage : clavardagesEnCours)
		{
			this.clavardagesEnCours.remove(clavardage);
		}
		System.out.println("Fin des clavardages" );
		
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
