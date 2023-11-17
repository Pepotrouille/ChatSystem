package controller;

import model.Signal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SignalEnvoiUnicastController { // Singleton
		
		//---------------------------Attributs-------------------------
	
		private String ipLocale;
		
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
					socket = new DatagramSocket(portReceiver);
					byte[] buf = new byte[2048];
					buf = signal.ToString().getBytes();
					System.out.println("Envoie de : " + signal.ToString() + " à l'adresse " + ip.substring(1) + " et au port " + portReceiver);
	            
					InetAddress addressReceiver = InetAddress.getByName(ip.substring(1));
	            
					DatagramPacket outPacket = new DatagramPacket(buf, buf.length, addressReceiver, portReceiver);
					socket.send(outPacket);
					
					socket.close();
				}
				else  //Port existant, pas d'ouverture ni de fermeture
				{
					socket = SignalReceptionBroadcastController.socket;
					byte[] buf = new byte[2048];
					buf = signal.ToString().getBytes();
					System.out.println("Envoie de : " + signal.ToString() + " à l'adresse " + ip.substring(1) + " et au port " + portReceiver);
	            
					InetAddress addressReceiver = InetAddress.getByName(ip.substring(1));
	            
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
		
		

		
