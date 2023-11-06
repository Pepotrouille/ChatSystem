package controller;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class SignalReceptionBroadcastController  extends Thread{

	//---------------------------Attributs-------------------------

	private int generalPortEnvoi;
	private int generalPortReception;
	
	//---------------------------Méthodes-------------------------
	
	//----------Constructeur
	
	public SignalReceptionBroadcastController() { 				
		
		this.generalPortEnvoi = 5000;
		this.generalPortReception = 5001;
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

            if (receivedMessage.equals("D")) {
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
        }
    }
	
	
	
}