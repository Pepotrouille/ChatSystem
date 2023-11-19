package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*; 
import model.TableUtilisateurs;
import model.Utilisateur;
import java.util.HashMap;

public class TableUtilisateursView {

	static String[] columnNames = {"IP",
            "Pseudo"};
	
	/* static Object[][] data = {
		    {"192.168.176.1" ,"Kathy Smith"},
		    {"192.168.176.2" ,"John Doe"},
		    {"192.168.176.5" ,"Sue Black"},
		    {"192.168.176.8" ,"Jane White"},
		    {"192.168.176.45" ,"Joe Brown"}
		}; */

	public static void createAndShowGUI()
	{

		//-------------------Mise en place de la fenêtre principale
        JFrame frame = new JFrame("Table Utilisateurs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //-------------------Créer la table d'utilisateurs
        TableUtilisateurs table_users = new TableUtilisateurs();
        
        table_users.AjouterUtilisateur("192.168.176.1", "Kathy Smith");
        table_users.AjouterUtilisateur("192.168.176.2", "John Doe");
        table_users.AjouterUtilisateur("192.168.176.5", "Sue Black");
        table_users.AjouterUtilisateur("192.168.176.8", "Jane White");
        table_users.AjouterUtilisateur("192.168.176.45", "Joe Brown");
        
        //-------------------Récupérer les données utilisateurs
        /* HashMap<String, String> data = new HashMap<String, String>(); 
        
        for (Utilisateur u : table_users.GetListeUtilisateurs()) {
        	data.put(u.GetIP(), u.GetPseudo());
        }
        
        System.out.println(data); */
        
        
        //-------------------Création de la table à partir des données
        DefaultTableModel dtm = new DefaultTableModel();
		JTable table = new JTable(dtm);
		dtm.addColumn("IP");
		dtm.addColumn("Pseudo");
		
		for (Utilisateur u : table_users.GetListeUtilisateurs()) {
        	Object[] row = {u.GetIP(), u.GetPseudo()};
        	dtm.addRow(row);
        }
		
		
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
