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
		this.SetAnnee(date.getYear() + 1900);
		this.SetMois(date.getMonth() + 1);
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
		
		//Adapter les int pour faire en sortent qu'ils prennent exactement deux caractères.
		String mois = Integer.toString(date.GetMois());
		mois = (mois.length() == 1) ? "0"+mois:mois;
		
		String jour = Integer.toString(date.GetJour());
		jour = (jour.length() == 1) ? "0"+jour:jour;

		String heure = Integer.toString(date.GetHeure());
		heure = (heure.length() == 1) ? "0"+heure:heure;

		String minute = Integer.toString(date.GetMinute());
		minute = (minute.length() == 1) ? "0"+minute:minute;

		String seconde = Integer.toString(date.GetSeconde());
		seconde = (seconde.length() == 1) ? "0"+seconde:seconde;
		
		return (date.GetAnnee() + "-" + mois + "-" + jour + " " + heure + ":" + minute + ":" + seconde) ;
	}
	
	
	
	public static MaDate StringToMaDate(String dateString) throws DateInvalideException
	{
		//Format : "yyyy-MM-dd hh:mm:ss"

		int annee = Integer.parseInt(dateString.substring(0, 4));
		int mois = Integer.parseInt(dateString.substring(5, 7));
		int jour = Integer.parseInt(dateString.substring(8, 10));
		int heure = Integer.parseInt(dateString.substring(11, 13));
		int minute = Integer.parseInt(dateString.substring(14, 16));
		int seconde = Integer.parseInt(dateString.substring(17, 19));
		
		
		return new MaDate(annee, mois, jour, heure, minute, seconde);
	}
	
	public static MaDate DateToMaDate(Date date) throws DateInvalideException
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
