package controller;

import model.Signal;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class SignalEnvoiBroadcastController { // Singleton
		
		private static SignalEnvoiBroadcastController self;
	
		//---------------------------Attributs-------------------------
	
		private String ipLocale;
		private int generalPortEnvoi;
		private int generalPortReception;
		
		//---------------------------Méthodes-------------------------
		
		//----------Constructeur
		
		private SignalEnvoiBroadcastController() { 				
			
			try {
				this.ipLocale = InetAddress.getLocalHost().toString();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			this.generalPortEnvoi = BroadcastController.generalPortEnvoi;
			this.generalPortReception = BroadcastController.generalPortReception;
		}
		
		static SignalEnvoiBroadcastController GetInstance()
		{
			if(SignalEnvoiBroadcastController.self == null)
	    	{
				SignalEnvoiBroadcastController.self = new SignalEnvoiBroadcastController();
	    	}
	    	return SignalEnvoiBroadcastController.self;
		}

		//----------Getters
		public String GetIPLocale() {
			return this.ipLocale;
		}

		public int GetGeneralPortEnvoi() {
			return this.generalPortEnvoi;
		}
		public int GetGeneralPortReception() {
			return this.generalPortReception;
		}
		
		//----------Setters
		public void SetIPLocale(String ipLocale) {
			this.ipLocale = ipLocale;
		}
		
		
		//----------Autres Méthodes
		
		//---Template Envoi Signal
		
		public void EnvoyerSignalBroadcast(Signal signal) {
			try {
				DatagramSocket socket = new DatagramSocket(generalPortEnvoi);
			
				byte[] buf = new byte[256];
				buf = signal.ToString().getBytes();
            	String bytesToString = new String(buf, 0, buf.length);
            	System.out.println("Envoie de broadcast au port " + generalPortReception + " avec le message : " + bytesToString);
            	

            	Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            	while (interfaces.hasMoreElements()) 
            	{
            	    NetworkInterface networkInterface = interfaces.nextElement();
            	    if (networkInterface.isLoopback())
            	        continue;    // Do not want to use the loopback interface.
            	    for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) 
            	    {
            	        InetAddress broadcast = interfaceAddress.getBroadcast();
            	        if (broadcast == null)
            	            continue;

            	        DatagramPacket outPacket = new DatagramPacket(buf, buf.length, broadcast , generalPortReception);
                    	System.out.println(" ");
                    	socket.send(outPacket);
            	    }
            	}
            
                socket.close();
            
			} catch (Exception e) {e.printStackTrace();}
		}

		//---Envoi Signal Spécifique
		
		public void EnvoyerSignalConnexionBroadcast(String pseudo) {
			EnvoyerSignalBroadcast(new model.SignalConnexion(pseudo));
		}
		
		public void EnvoyerSignalDeconnexionBroadcast() {
			EnvoyerSignalBroadcast(new model.SignalDeconnexion());
		}

		public void EnvoyerSignalChangementPseudo(String pseudo) {
			EnvoyerSignalBroadcast(new model.SignalChangementPseudo(pseudo));
		}

		
}