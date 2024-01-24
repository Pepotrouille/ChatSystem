package controller;

import model.Signal;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SignalEnvoiUnicastController { // Singleton
		
		public static SignalEnvoiUnicastController self;
	
		//---------------------------Attributs-------------------------
	
		private String ipLocale;
		
		//---------------------------Méthodes-------------------------
		public static SignalEnvoiUnicastController GetInstance()
	    {
	    	if(SignalEnvoiUnicastController.self == null)
	    	{
	    		SignalEnvoiUnicastController.self = new SignalEnvoiUnicastController();
	    	}
	    	return SignalEnvoiUnicastController.self;
	    }

		//----------Constructeur
		
		
		private SignalEnvoiUnicastController() { 				
			
			try {
				this.ipLocale = InetAddress.getLocalHost().toString();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//----------Getters
		public String GetIPLocale() {
			return this.ipLocale;
		}

		
		//----------Setters
		public void SetIPLocale(String ipLocale) {
			this.ipLocale = ipLocale;
		}
		
		
		//----------Autres Méthodes
		
		//---Template Envoi Signal
		public void EnvoyerSignalUnicast(Signal signal, String ip, int portReceiver) {
			
			DatagramSocket socket;
			try {
				if(portReceiver != BroadcastController.generalPortReception)
				{
					socket = new DatagramSocket(BroadcastController.generalPortEnvoi);
					byte[] buf = new byte[2048];
					buf = signal.ToString().getBytes();
					System.out.println("Envoie de : " + signal.ToString() + " à l'adresse " + ip + " et au port " + portReceiver);
	            
					InetAddress addressReceiver = InetAddress.getByName(ip);
	            
					DatagramPacket outPacket = new DatagramPacket(buf, buf.length, addressReceiver, portReceiver);
					socket.send(outPacket);
					
					socket.close();
				}
				else  //Port existant, pas d'ouverture ni de fermeture
				{
					socket = SignalReceptionBroadcastController.socket;
					byte[] buf = new byte[2048];
					buf = signal.ToString().getBytes();
					System.out.println("Envoie de : " + signal.ToString() + " à l'adresse " + ip + " et au port " + portReceiver);
	            
					InetAddress addressReceiver = InetAddress.getByName(ip);
	            
					DatagramPacket outPacket = new DatagramPacket(buf, buf.length, addressReceiver, portReceiver);
					socket.send(outPacket);
				}
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
	    }

		//---Envoi Signal Spécifique
		
}
		
		

		
