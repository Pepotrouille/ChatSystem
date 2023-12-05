package controller;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

import model.TableUtilisateurs;


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
            String receivedMessage = new String(inPacket.getData(), 0, inPacket.getLength());

            //-----------------------------------------Gestion des cas selon signal de réception
            if(!inPacket.getAddress().toString().contains(this.adresseLocale))
            {
            	String adresseSource = inPacket.getAddress().toString().substring(1);
            	
            	if (receivedMessage.charAt(0) == 'C') { //Si connexion de l'utilisateur envoyant le message
                

            		tableUtilisateurs.AjouterUtilisateur(adresseSource, receivedMessage.substring(1));
            		
            		System.out.println("Reception de connexion de " + adresseSource + " en " + receivedMessage.substring(1));
                    
                    //Renvoi d'un signal pour remplir table d'utilisateurs !!! Si SignakEnvoi en Unicast, supprimer celui-ci
                    SignalEnvoiUnicastController seuc = SignalEnvoiUnicastController.GetInstance();
                    seuc.EnvoyerSignalUnicast(new model.SignalReponseConnexion(BroadcastController.soiMeme.GetPseudo()), inPacket.getAddress().toString(), generalPortReception);

            		System.out.println("Reponse de connexion envoyée");
            	
            	}
            	else if (receivedMessage.charAt(0) == 'P') { //Si changement de pseudo reçu
                	//Ajouter cas pseudo est le sien
            		if(receivedMessage.substring(1).equals(BroadcastController.soiMeme.GetPseudo()))
            		{
            			//Faire la gestion de conflit de pseudo
            			//SignalEnvoiUnicastController seuc = new SignalEnvoiUnicastController();
                        //seuc.EnvoyerSignalUnicast(new model.SignalReponseConnexion(this.adresseLocale), inPacket.getAddress().toString(), generalPortReception);
            			
            			System.out.println("Pseudo " + receivedMessage.substring(1) + " déja utilisé, notification envoyée.");
            		}
            		else if(!tableUtilisateurs.SetPseudo(adresseSource, receivedMessage.substring(1)))
            		{
            			tableUtilisateurs.AjouterUtilisateur(adresseSource, receivedMessage.substring(1));
            			System.out.println("Ajout de " + adresseSource + " ; " + receivedMessage.substring(1) + " à la table utilisateur");
            		}
                	System.out.println("Reception du changement de pseudo de " + adresseSource + " en " + receivedMessage.substring(1));
            	}
            	else if (receivedMessage.charAt(0) == 'D') { //Si déconnexion de l'utilisateur envoyant le message

                	tableUtilisateurs.SupprimerUtilisateur(adresseSource);
                	System.out.println("Reception de la déconnexion de " + adresseSource + " sur le réseau");
            	}
            	else if (receivedMessage.charAt(0) == 'R') { //Si réception d'un acquittement de connexion avec pseudo

                	tableUtilisateurs.AjouterUtilisateur(adresseSource, receivedMessage.substring(1));
                	System.out.println("Reception de la présence de " + receivedMessage.substring(1) + " sur le réseau");
            	}
            	else if (receivedMessage.charAt(0) == 'A') { //Si déconnexion d'un autre utilisateur envoyé

                	tableUtilisateurs.SupprimerUtilisateur(receivedMessage.substring(1));
                	System.out.println("Reception de la déconnexion de " + receivedMessage.substring(1) + " sur le réseau");
            	}
            	else if (receivedMessage.charAt(0) == 'O') { //Si conflit de pseudo détecté
            		Random rand = new Random();
            		String newPseudo = "Utilisateur" + rand.nextInt(5000);
            		System.out.println("Conflit sur le pseudo " + BroadcastController.soiMeme.GetPseudo() + ". Nouveau pseudo réassigné : " + newPseudo + ". Veuillez changer de pseudo.");
            		pseudoController.changePseudo(newPseudo);
            		if(!tableUtilisateurs.SetPseudo(adresseSource, receivedMessage.substring(1)))
            		{
            			tableUtilisateurs.AjouterUtilisateur(adresseSource, receivedMessage.substring(1));
            			System.out.println("Ajout de " + adresseSource + " ; " + receivedMessage.substring(1) + " à la table utilisateur");
            		}
                	
            	}
            	else if (receivedMessage.charAt(0) == 'N') { //Si clavardage créé
            		SignalEnvoiUnicastController seuc = SignalEnvoiUnicastController.GetInstance();
                    seuc.EnvoyerSignalUnicast(new model.SignalValiderNouveauClavardage(2048), inPacket.getAddress().toString(), generalPortReception);//Gestion Port
            		//Créer boîte clavardage avec adresseSource

                	
            	}
            	else if (receivedMessage.charAt(0) == 'V') { //Si confirmation de clavardage créé
            		//Créer boîte clavardage avec adresseSource
                	
            	}
            	else{

                	System.out.println("Message reçu: " + receivedMessage);
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