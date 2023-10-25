package controller;

import model.Signal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SignalEnvoiController { // Singleton
		
		//---------------------------Attributs-------------------------
	
		private String ipLocale;
		private int generalPort;
		
		//---------------------------Méthodes-------------------------
		
		//----------Constructeur
		
		public SignalEnvoiController(String ipLocale) { 				
			
			this.ipLocale = ipLocale;
		}

		//----------Getters
		public String GetIPLocale() {
			return this.ipLocale;
		}

		public int GetGeneralPort() {
			return this.generalPort;
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
		}
		
		public void EnvoyerSignalBroadcast(Signal signal) {
			
		}

		//---Envoi Signal Spécifique
		
		public void EnvoyerSignalConnexionBroadcast(String pseudo) {
			EnvoyerSignalBroadcast(new model.SignalConnexion(pseudo));
		}
		
		public void EnvoyerSignalDeconnexionBroadcast() {
			EnvoyerSignalBroadcast(new model.SignalDeconnexion());
		}

		public void EnvoyerSignalChangementPseudo(String pseudo) {
			EnvoyerSignalBroadcast(new model.SignalConnexion(pseudo));
		}

		
}