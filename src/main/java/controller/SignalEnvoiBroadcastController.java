package controller;

import model.Signal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;

public class SignalEnvoiBroadcastController { // Singleton
		
		//---------------------------Attributs-------------------------
	
		private String ipLocale;
		private int generalPortEnvoi;
		private int generalPortReception;
		
		//---------------------------Méthodes-------------------------
		
		//----------Constructeur
		
		public SignalEnvoiBroadcastController() { 				
			
			try {
				this.ipLocale = InetAddress.getLocalHost().toString();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.generalPortEnvoi = 5000;
			this.generalPortReception = 5001;
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
            	
            	/*NetworkInterface nif = NetworkInterface.getByIndex(1);   
            	List<java.net.InterfaceAddress> list = nif.getInterfaceAddresses();   
        
            	System.out.println("OKAY");
            	
            	for (java.net.InterfaceAddress iaddr : list)    
            	{   
                   
                	DatagramPacket outPacket = new DatagramPacket(buf, buf.length, iaddr.getBroadcast() , generalPortReception);
                	System.out.println("AAA");
                	socket.send(outPacket);
            	}*/

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
                    	System.out.println("AAA");
                    	socket.send(outPacket);
            	    }
            	}
            	
            	
                //InetAddress senderAddress = InetAddress.getByName("127.0.0.1");
                //DatagramPacket outPacket = new DatagramPacket(buf, buf.length, senderAddress , generalPortReception);
            	//socket.send(outPacket);
            	
                socket.close();
            
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
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