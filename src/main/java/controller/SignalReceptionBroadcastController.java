package controller;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import model.TableUtilisateurs;


public class SignalReceptionBroadcastController  extends Thread{

	//---------------------------Attributs-------------------------

	private int generalPortReception;

	private int generalPortEnvoi;
	
	private TableUtilisateurs tableUtilisateurs;
	
	private SignalEnvoiUnicastController seuc;
	
	//---------------------------Méthodes-------------------------
	
	//----------Constructeur
	
	public SignalReceptionBroadcastController(int generalPortEnvoi, int generalPortReception, TableUtilisateurs tableUtilisateurs) { 				

		this.generalPortEnvoi = generalPortEnvoi;
		
		this.generalPortReception = generalPortReception;
		
		this.tableUtilisateurs = tableUtilisateurs;
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
        try{
          
        DatagramSocket socket = new DatagramSocket(generalPortReception);
        boolean running = true;
        byte[] buf = new byte[256];

        while (running) {
            DatagramPacket inPacket  = new DatagramPacket(buf, buf.length);
            socket.receive(inPacket);
            String receivedMessage = new String(inPacket.getData(), 0, inPacket.getLength());

            //-----------------------------------------Gestion des cas selon signal de réception
            
            if (receivedMessage.charAt(0) == 'C') { //Si connexion de l'utilisateur envoyant le message
                tableUtilisateurs.AjouterUtilisateur(inPacket.getAddress().toString(), receivedMessage.substring(1));
                
                //Renvoi d'un signal pour remplir table d'utilisateurs !!! Si SignakEnvoi en Unicast, supprimer celui-ci
                SignalEnvoiUnicastController seuc = new SignalEnvoiUnicastController();
                seuc.EnvoyerSignalUnicast(new model.SignalReponseConnexion(InetAddress.getLocalHost().toString()), inPacket.getAddress().toString(), generalPortReception);
                
            }
            else if (receivedMessage.charAt(0) == 'P') { //Si changement de pseudo reçu
                //Ajouter cas pseudo est le sien
            	if(!tableUtilisateurs.SetPseudo(inPacket.getAddress().toString(), receivedMessage.substring(1)))
            	{
            		tableUtilisateurs.AjouterUtilisateur(inPacket.getAddress().toString(), receivedMessage.substring(1));
            	}
            }
            else if (receivedMessage.charAt(0) == 'D') { //Si déconnexion de l'utilisateur envoyant le message

                tableUtilisateurs.SupprimerUtilisateur(inPacket.getAddress().toString());
            }
            else if (receivedMessage.charAt(0) == 'R') { //Si réception d'un acquittement de connexion avec pseudo

                tableUtilisateurs.AjouterUtilisateur(inPacket.getAddress().toString(), receivedMessage.substring(1));
            }
            else if (receivedMessage.charAt(0) == 'A') { //Si déconnexion d'un autre utilisateur envoyé

                tableUtilisateurs.SupprimerUtilisateur(receivedMessage.substring(1));
            }
            else{

                System.out.println("Message reçu: " + receivedMessage);
                System.out.println("@IP source = " + inPacket.getAddress().toString());
            }
            
        }
        socket.close();  
        System.out.println("Déconnexion");
        }
        catch(Exception e)
        {
            System.out.println("Exception raised");
        }
    }
	
	
	
}