package controller;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

import model.Clavardage;
import model.TableUtilisateurs;
import model.Utilisateur;


public class SignalReceptionBroadcastController  extends Thread{

	public static SignalReceptionBroadcastController self;
	
	//---------------------------Attributs-------------------------

	private int generalPortReception;

	private int generalPortEnvoi;
	
	private PseudoController pseudoController;
	
	private TableUtilisateurs tableUtilisateurs;
	
	private SignalEnvoiUnicastController seuc;
	
	private String adresseLocale;
	
	public static DatagramSocket socket ;
	
    private boolean running;
	
	//---------------------------Méthodes-------------------------
	
    public static SignalReceptionBroadcastController GetInstance(TableUtilisateurs tableUtilisateurs, String adresseLocale, PseudoController pseudoController)
    {
    	if(SignalReceptionBroadcastController.self == null)
    	{
        	SignalReceptionBroadcastController.self = new SignalReceptionBroadcastController(tableUtilisateurs, adresseLocale, pseudoController);
        	SignalReceptionBroadcastController.self.start();
    	}
    	return SignalReceptionBroadcastController.self;
    }
    
    
	//----------Constructeur
	
	private SignalReceptionBroadcastController(TableUtilisateurs tableUtilisateurs, String adresseLocale, PseudoController pseudoController) { 				

		this.generalPortEnvoi = BroadcastController.generalPortEnvoi;
		
		this.generalPortReception = BroadcastController.generalPortReception;
		
		this.tableUtilisateurs = tableUtilisateurs;
		
		this.adresseLocale = adresseLocale;
		
		this.pseudoController = pseudoController;
		
		this.running = true;
		
		this.seuc = SignalEnvoiUnicastController.GetInstance();
	}

	//----------Getters

	public int GetGeneralPortEnvoi() {
		return this.generalPortEnvoi;
	}
	
	public int GetGeneralPortReception() {
		return this.generalPortReception;
	}
	
	@Override
    public void run() {
		try {
			socket = new DatagramSocket(generalPortReception);
		}
		catch(java.net.BindException e)
		{
			System.out.println("Port déjà ouvert");
			
            e.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("Autre Type d'erreur");
            e.printStackTrace();
		}
		
		
        try{
        byte[] buf = new byte[256];

        while (running) {
            DatagramPacket inPacket  = new DatagramPacket(buf, buf.length);
            socket.receive(inPacket);
            String signalRecu = new String(inPacket.getData(), 0, inPacket.getLength());

            //-----------------------------------------Gestion des cas selon signal de réception
            if(!inPacket.getAddress().toString().contains(this.adresseLocale))
            {
            	String adresseSource = inPacket.getAddress().toString().substring(1);
            	String messageRecu = signalRecu.substring(1);
            	switch(signalRecu.charAt(0))
            	{
            	case 'C' : //Si connexion de l'utilisateur envoyant le message
            		tableUtilisateurs.AjouterUtilisateur(adresseSource, messageRecu);
            		
            		System.out.println("Reception de connexion de " + adresseSource + " en " + messageRecu);
                    
                    //Renvoi d'un signal pour remplir table d'utilisateurs !!! Si SignalEnvoi en Unicast, supprimer celui-ci
                    seuc.EnvoyerSignalUnicast(new model.SignalReponseConnexion(Utilisateur.GetUtilisateurActuel().GetPseudo()), adresseSource, generalPortReception);

            		System.out.println("Reponse de connexion envoyée");
            		break;
            	
            		
            	case 'P': //Si changement de pseudo reçu
                	//Ajouter cas pseudo est le sien
            		if(messageRecu.equals(Utilisateur.GetUtilisateurActuel().GetPseudo()))
            		{
            			//Faire la gestion de conflit de pseudo
            			//SignalEnvoiUnicastController seuc = new SignalEnvoiUnicastController();
                        //seuc.EnvoyerSignalUnicast(new model.SignalReponseConnexion(this.adresseLocale), inPacket.getAddress().toString(), generalPortReception);
            			
            			System.out.println("Pseudo " + messageRecu + " déja utilisé, notification envoyée.");
            		}
            		else if(!tableUtilisateurs.SetPseudo(adresseSource, messageRecu))
            		{
            			tableUtilisateurs.AjouterUtilisateur(adresseSource, messageRecu);
            			System.out.println("Ajout de " + adresseSource + " ; " + messageRecu + " à la table utilisateur");
            		}
                	System.out.println("Reception du changement de pseudo de " + adresseSource + " en " + messageRecu);
                	break;
                	
                	
            	case 'D': //Si déconnexion de l'utilisateur envoyant le message
                	tableUtilisateurs.SupprimerUtilisateur(adresseSource);
                	System.out.println("Reception de la déconnexion de " + adresseSource + " sur le réseau");
                	break;
                	
                	
            	case 'R': //Si réception d'un acquittement de connexion avec pseudo

                	tableUtilisateurs.AjouterUtilisateur(adresseSource, messageRecu);
                	System.out.println("Reception de la présence de " + messageRecu + " sur le réseau");
                	break;
            	
                	
            	case 'A':  //Si déconnexion d'un autre utilisateur envoyé

                	tableUtilisateurs.SupprimerUtilisateur(messageRecu);
                	System.out.println("Reception de la déconnexion de " + messageRecu + " sur le réseau");
                	break;
                	
                	
            	case 'O': //Si conflit de pseudo détecté
            		Random rand = new Random();
            		String newPseudo = "Utilisateur" + rand.nextInt(5000);
            		System.out.println("Conflit sur le pseudo " + Utilisateur.GetUtilisateurActuel().GetPseudo() + ". Nouveau pseudo réassigné : " + newPseudo + ". Veuillez changer de pseudo.");
            		pseudoController.changePseudo(newPseudo);
            		if(!tableUtilisateurs.SetPseudo(adresseSource, messageRecu))
            		{
            			tableUtilisateurs.AjouterUtilisateur(adresseSource, messageRecu);
            			System.out.println("Ajout de " + adresseSource + " ; " + messageRecu + " à la table utilisateur");
            		}
            		break;
            		
            		
            	case 'N': //Si clavardage créé
            		//Recuperation d'un port d'envoi valide
            		ClavardageController clavardageController = ClavardageController.GetInstance();
            		int newPortReception = clavardageController.GetProchainPortValide();

                    System.out.println("Reception du nouveau clavardage de " + adresseSource);
                    
            		//Envoi d'un message de création de clavardage
                    seuc.EnvoyerSignalUnicast(new model.SignalValiderNouveauClavardage(newPortReception), adresseSource, Integer.parseInt(messageRecu));//Gestion Port

                    System.out.println("Envoi du message d'acquittement à " + adresseSource +" au port : " + Integer.parseInt(messageRecu) + " avec signal : V" + newPortReception);
                    
            		//Créer boîte clavardage avec adresseSource
                    Utilisateur utilisateurInterlocuteur;
                    if(adresseSource.equals("127.0.0.1"))
                    {
                    	utilisateurInterlocuteur = new Utilisateur(adresseSource, "Soi-même");
                    }
                    else
                    {
                    	utilisateurInterlocuteur = tableUtilisateurs.GetUtilisateur(adresseSource);
                    }
                    
                    Clavardage newClavardage = clavardageController.NouveauClavardageValide(utilisateurInterlocuteur, newPortReception, Integer.parseInt(messageRecu));
                    

                    System.out.println("Création de la boîte de clavardage avec " + adresseSource);
                	break;
                	
                	
                default:
                	System.out.println("Message reçu: " + signalRecu);
                    System.out.println("@IP source = " + adresseSource);
            	}
            	//--DEBUG
            	this.tableUtilisateurs.AfficherListe();
            	//--
            	
            }
            
        }
        socket.close();  
        System.out.println("Déconnexion");
        }
        catch(Exception e)
        {
            System.out.println("Exception raised here");
            e.printStackTrace();
        }
    }
	
	public void CloreReception()
	{
		this.running = false;
	}
	
	
	
}