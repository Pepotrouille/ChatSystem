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
		
		public SignalEnvoiUnicastController(int specificPort ) { 				
			
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
		public void EnvoyerSignalUnicast(Signal signal, String ip,  int portLocal, int portReceiver) {
			
			DatagramSocket socket;
			try {
				socket = new DatagramSocket(portReceiver);
			byte[] buf = new byte[2048];
			buf = signal.ToString().getBytes();
            System.out.println("Envoie de : " + signal.toString() + " à l'adresse " + ip + " et au port " + portReceiver);
            
            InetAddress addressReceiver = InetAddress.getByName(ip);
            
            DatagramPacket outPacket = new DatagramPacket(buf, buf.length, addressReceiver, portReceiver);
            socket.send(outPacket);
            socket.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
	    }

		//---Envoi Signal Spécifique
		
}
		
		

		
