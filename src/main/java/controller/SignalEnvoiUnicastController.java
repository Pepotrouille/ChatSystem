package controller;

import model.Signal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SignalEnvoiUnicastController { // Singleton
		
		//---------------------------Attributs-------------------------
	
		private String ipLocale;
		private int specificPort;
		
		//---------------------------Méthodes-------------------------
		
		//----------Constructeur
		
		public SignalEnvoiUnicastController() { 				
			
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

		public int GetSpecificPort() {
			return this.specificPort;
		}
		
		//----------Setters
		public void SetIPLocale(String ipLocale) {
			this.ipLocale = ipLocale;
		}
		
		
		//----------Autres Méthodes
		
		//---Template Envoi Signal
		public void EnvoyerSignalUnicast(Signal signal, String ip,  int port) {
			/*   A FAIRE
			DatagramSocket socket = new DatagramSocket(Integer.parseInt(args[0]));
			byte[] buf = new byte[256];
			buf = args[1].getBytes();
            System.out.println("Envoie de : " + args[1] + " du port " + Integer.parseInt(args[0]));
            int senderPort = 4445;
            
            InetAddress senderAddress = InetAddress.getByName(ip);
            
            DatagramPacket outPacket = new DatagramPacket(buf, buf.length, senderAddress, senderPort);
            socket.send(outPacket);
            socket.close();
            */
	    }

		//---Envoi Signal Spécifique
		
}
		
		

		
