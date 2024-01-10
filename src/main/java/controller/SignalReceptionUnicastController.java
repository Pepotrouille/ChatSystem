package controller;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class SignalReceptionUnicastController  extends Thread{

	//---------------------------Attributs-------------------------

	private int specificPortLocal;
	
	//---------------------------Méthodes-------------------------
	
	//----------Constructeur
	
	public SignalReceptionUnicastController(int portReception) { 				
		
		this.specificPortLocal = portReception;
	}

	//----------Getters

	public int GetSpecificPortLocal() {
		return this.specificPortLocal;
	}
	
	
	@Override
    public void run() {
        try{
          
        DatagramSocket socket = new DatagramSocket(specificPortLocal);
        boolean running = true;
        byte[] buf = new byte[256];

        while (running) {
            DatagramPacket inPacket  = new DatagramPacket(buf, buf.length);
            socket.receive(inPacket);
            String receivedMessage = new String(inPacket.getData(), 0, inPacket.getLength());

            if (0!=0 ) {//Reception Bouton quitter
                running = false;
                continue;
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
            e.printStackTrace();
        }
    }
	
	
	
}