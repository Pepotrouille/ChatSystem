package controller;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import exceptions.ClavardageNonExistantException;
import model.Clavardage;
import model.Message;
import model.SignalMessage;
import model.SignalMessageRecu;


public class SignalReceptionUnicastController  extends Thread{

	//---------------------------Attributs-------------------------
	
	private DatagramSocket socket;
	
	private boolean running;
	
	private Clavardage clavardage;
	
	private int noSequenceAttendu;
	
	
	//---------------------------Méthodes-------------------------
	
	//----------Constructeur
	
	public SignalReceptionUnicastController(Clavardage clavardage) { 				
		
		this.clavardage = clavardage;
		
		this.running = true;
		
		this.noSequenceAttendu = 10;

	}

	//----------Getters

	
	
	@Override
    public void run() {
        try{
          
        socket = new DatagramSocket(clavardage.GetPortReception());
        boolean running = true;
        byte[] buf = new byte[256];
        System.out.println("Nouvelle réception unicast sur port : " + clavardage.GetPortReception());

        while (running) {
            DatagramPacket inPacket  = new DatagramPacket(buf, buf.length);
            socket.receive(inPacket);
            
            String receivedMessage = new String(inPacket.getData(), 0, inPacket.getLength());
        	String messageRecu = receivedMessage.substring(1);
            System.out.println("Message reçu: " + receivedMessage);
        	
            if (receivedMessage.charAt(0) == 'M') { //Reception d'un message
            	SignalEnvoiUnicastController seuc = SignalEnvoiUnicastController.GetInstance();
            	int noSequenceRecu = Integer.parseInt(messageRecu.substring(0,2));
            	
            	//Verification numero de séquence pour éviter doublons
            	if(noSequenceRecu == noSequenceAttendu)
            	{
                	//Ajout du message à la base de données et à l'historique local
                	Message newMessage = new Message(messageRecu.substring(2), false);
                	BDDMessageController.GetInstance().AjouterMessage(newMessage, clavardage.GetHistorique());
                	clavardage.GetHistorique().AjouterMessage(newMessage);
                	
                	//Incremente noSequence
                	if(noSequenceAttendu >99 || noSequenceAttendu<10)
                	{
                		noSequenceAttendu = 10;
                	}
            	}
            	else
            	{
            		
            	}

            	
            	//Envoi de la validation de réception
            	seuc.EnvoyerSignalUnicast(new SignalMessageRecu(noSequenceRecu, messageRecu.substring(2)), clavardage.GetIPDestination(), clavardage.GetPortEnvoi());
            	
            }
            else if (receivedMessage.charAt(0) == 'W') {//Reception Validation message Recu
            	//Ajout du message à l'historique local
            	if(SignalMessage.GetNumeroSequenceActuel()-1 == Integer.parseInt(messageRecu.substring(0,2)))
            	{
                	Message newMessage = new Message(messageRecu.substring(2), false);
                	clavardage.GetHistorique().AjouterMessage(newMessage);
            	}
            	
            }
            else if (receivedMessage.charAt(0) == 'F') {//Reception Fermer Clavardage
            	ArretReception();
            }
            
            
        }
        CloreReception();
        }
        catch(Exception e)
        {
            System.out.println("Exception raised");
            e.printStackTrace();
        }
    }
	
	private void ArretReception() {

		this.running = false;
	}
	
	private void CloreReception() throws ClavardageNonExistantException {

        socket.close();  
        System.out.println("Fermeture clavardage avec " + clavardage.GetUserPseudo());
        clavardage.CloreClavardage();
	}

	
	
	
}