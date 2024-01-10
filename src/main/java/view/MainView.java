package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*; 
import controller.BroadcastController;
import controller.ClavardageController;
import model.Clavardage;
import model.Utilisateur;
import view.AccountParameterViewAdmin;
import model.TableUtilisateurs;
import java.util.ArrayList;

public class MainView {

	private static JFrame frame;
	
	public static void AfficherClavardage(Clavardage clavardage)
	{
		UpdateGUI(new ClavardageView(clavardage), true);
	}

	public static void AfficherParametresDuCompte(Utilisateur utilisateur)
	{
		if (Utilisateur.GetUtilisateurActuel().GetAdmin().equals(1))
		{
			UpdateGUI(new AccountParameterViewAdmin(utilisateur), true);
			
			// Mettre à jour les valeurs pour éviter le problème de référence
			Utilisateur.GetUtilisateurActuel().SetAdmin(1); 
			Utilisateur.GetUtilisateurActuel().SetPseudo(utilisateur.GetPseudo());
		}
		else 
		{
			UpdateGUI(new AccountParameterView(utilisateur), true);
			Utilisateur.GetUtilisateurActuel().SetPseudo(utilisateur.GetPseudo());
		}
	}

	public static void AfficherClavardagesEnCours()
	{
		ArrayList<Object> listeGenerique = new ArrayList<Object>(ClavardageController.GetInstance().GetClavardageEnCours());
		UpdateGUITable(new DataTable(listeGenerique, DataTable.TypeDataTable.Clavardage), true);
	}
	
	public static void AfficherUtilisateursEnLigne()
	{
		ArrayList<Object> listeGenerique = new ArrayList<Object>(TableUtilisateurs.GetInstance().GetListeUtilisateurs());
		System.out.println("Liste des utilisateurs en ligne : " + TableUtilisateurs.GetInstance().GetListeUtilisateurs().toString());
		UpdateGUITable(new DataTable(listeGenerique, DataTable.TypeDataTable.Utilisateur), true);
	}

	public static void AfficherCreerCompte()
	{
		
		UpdateGUI(new CreerCompteView(), true);
	}
	
	
	public static void AfficherAuthentification()
	{
		UpdateGUI(new AuthentificationView(), false);
	}
	
	
	private static void UpdateGUI(Container currentContainer, boolean withMenuBar)
	{
		CreateGUI(withMenuBar);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(currentContainer);
	        
		frame.setSize(1000,600);//400 width and 500 height  
		frame.setVisible(true);//making the frame visible  
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private static void UpdateGUITable(DataTable dataTable, boolean withMenuBar)
	{
		
		CreateGUI(withMenuBar);
				
		//Creation of the table 
		Container containerTable = TableToContainer.GetInstance().DataTableToContainer(dataTable);
		JScrollPane scrollPane;
		scrollPane = new JScrollPane(containerTable);
				
		//Frame Layout
		frame.getContentPane().removeAll();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setSize(1000,600);
		frame.setVisible(true);//making the frame visible  
		
	}
	
	private static void CreateGUI(boolean withMenuBar)
	{

		if(frame == null)
		{
			//----------Création de la fenêtre
		
			frame=new JFrame("Clavardages");//creating instance of JFrame  
			frame.setBounds(300, 90, 900, 600);
		
		
		}
		
		if(withMenuBar && frame.getJMenuBar() == null)
		{
			
			frame.setJMenuBar(new BarMenuView());
		}
		else if(!withMenuBar && frame.getJMenuBar() != null)
		{
			frame.setJMenuBar(null);
		}
		
	}
}
