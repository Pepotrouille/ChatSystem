package test;

import controller.SignalReceptionBroadcastController;

public class TestReception {
    
    
    public static void main(String[] args){

        Thread t = new SignalReceptionBroadcastController();
        t.start();
        System.out.println("Lancement du Thread");




    }

}
