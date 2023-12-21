package model;

import java.util.Date;

import exceptions.DateInvalideException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MaDate implements Comparable<MaDate> {

	//---------------------------Attributs-------------------------
	
	private int annee;
	private int mois;
	private int jour;
	private int heure;
	private int minute;
	private int seconde;
	
	//---------------------------Méthodes-------------------------
		
	//----------Constructeur
		
	public MaDate(int annee, int mois, int jour, int heure, int minute, int seconde) throws DateInvalideException { 				
		if(mois < 1 || mois > 12 )
		{
			throw new DateInvalideException("Le mois " + mois + " n'est pas compris entre 1 et 12");
		}
		else if(jour < 1 || jour > 31)
		{
			throw new DateInvalideException("Le jour " + mois + " n'est pas compris entre 1 et 31");
		}
		else if(heure < 0 || heure > 23)
		{
			throw new DateInvalideException("L'heure " + heure + " n'est pas comprise entre 0 et 23");
		}
		else if(minute < 0 || minute > 59)
		{
			throw new DateInvalideException("La minute " + minute + " n'est pas comprise entre 0 et 59");
		}
		else if(seconde < 0 || seconde > 59)
		{
			throw new DateInvalideException("La seconde " + seconde + " n'est pas comprise entre 0 et 59");
		}
		this.SetAnnee(annee);
		this.SetMois(mois);
		this.SetJour(jour);
		this.SetHeure(heure);
		this.SetMinute(minute);
		this.SetSeconde(seconde);
	}
	
	
	public MaDate() { 				
		Date date = new Date();;
		this.SetAnnee(date.getYear());
		this.SetMois(date.getMonth());
		this.SetJour(date.getDay());
		this.SetHeure(date.getHours());
		this.SetMinute(date.getMinutes());
		this.SetSeconde(date.getSeconds());
	}

	//----------Getters
	
	public int GetAnnee() {
		return this.annee;
	}

	public int GetMois() {
		return this.mois;
	}

	public int GetJour() {
		return this.jour;
	}

	public int GetHeure() {
		return this.heure;
	}

	public int GetMinute() {
		return this.minute;
	}

	public int GetSeconde() {
		return this.seconde;
	}
		
	//----------Setters


	public void SetAnnee(int annee) {
		this.annee = annee;
	}

	public void SetMois(int mois) {
		this.mois = mois;
	}

	public void SetJour(int jour) {
		this.jour = jour;
	}

	public void SetHeure(int heure) {
		this.heure = heure;
	}

	public void SetMinute(int minute) {
		this.minute = minute;
	}

	public void SetSeconde(int seconde) {
		this.seconde = seconde;
	}
	
	//----------Méthodes Statiques
	
	public static String MaDateToString(MaDate date) throws DateInvalideException
	{
		
		return (date.GetJour() + "-" + date.GetMois() + "-" + date.GetAnnee() + " " + date.GetHeure() + ":" + date.GetMinute() + ":" + date.GetSeconde()) ;
	}
	
	
	
	public static MaDate StringToMaDate(String dateString) throws DateInvalideException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		MaDate maDate= null;
		try {
			maDate = DateToMaDate(sdf.parse(dateString));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return maDate;
	}
	
	private static MaDate DateToMaDate(Date date) throws DateInvalideException
	{
		return new MaDate(date.getYear(), date.getMonth(), date.getDay(), date.getHours(), date.getMinutes(), date.getSeconds());
	}


	@Override
	public int compareTo(MaDate autreDate) {
		int valeurComparaison;
		valeurComparaison = Integer.compare(this.annee, autreDate.GetAnnee());
		if (valeurComparaison == 0)
		{
			valeurComparaison = Integer.compare(this.mois, autreDate.GetMois());
			if (valeurComparaison == 0)
			{
				valeurComparaison = Integer.compare(this.jour, autreDate.GetJour());
				if (valeurComparaison == 0)
				{
					valeurComparaison = Integer.compare(this.heure, autreDate.GetHeure());
					if (valeurComparaison == 0)
					{
						valeurComparaison = Integer.compare(this.minute, autreDate.GetMinute());
						if (valeurComparaison == 0)
						{
							valeurComparaison = Integer.compare(this.seconde, autreDate.GetSeconde());
						}
					}
				}
			}
		}
		return valeurComparaison;
	}

	
	
}
