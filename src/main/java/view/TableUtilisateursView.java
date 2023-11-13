package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 

public class TableUtilisateursView {

	static String[] columnNames = {"IP",
            "Pseudo"};
	
	static Object[][] data = {
		    {"192.168.176.1" ,"Kathy Smith"},
		    {"192.168.176.2" ,"John Doe"},
		    {"192.168.176.5" ,"Sue Black"},
		    {"192.168.176.8" ,"Jane White"},
		    {"192.168.176.45" ,"Joe Brown"}
		};

	public static void createAndShowGUI()
	{

		//-------------------Mise en place de la fenêtre principale
        JFrame frame = new JFrame("Table Utilisateurs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //-------------------Création de la table à partir des données
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		//-------------------Ajout de la table à la fenêtre
		Container contentPane = frame.getContentPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        //-------------------Derniers réglages de la fenêtre principale
        // make window's dimension fit its content
        frame.pack();
        // Display the window.
        frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();

            }
        });
		
		
		
        
    }
	
}
