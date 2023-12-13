package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*; 
import view.TableUtilisateursView;
import controller.BroadcastController;
import model.Clavardage;
import model.Utilisateur;

public class MainView {

	private static JFrame frame;
	
	public static void AfficherClavardage(Clavardage clavardage)
	{
		
		//UpdateGUI(new ClavardageView(), true);
	}

	


	public static void AfficherParametresDuCompte(Utilisateur utilisateur)
	{
		UpdateGUI(new AccountParameterView(utilisateur), true);
	}

	public static void AfficherClavardagesEnCours()
	{
		//UpdateGUITable(Container currentContainer, true)
	}
	
	public static void AfficherUtilisateursEnLigne()
	{
		//UpdateGUITable(Container currentContainer, true)
	}

	public static void AfficherCreerCompte()
	{
		
		//UpdateGUI(new CreateAccountView(), true);
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
		
		
	}
	
	private static void UpdateGUITable(Container currentContainer, boolean withMenuBar)
	{
		/*
		CreateGUI(withMenuBar);
				
		//Creation of the table 
		//Container containerTable = TableModelToContainer.JTableModelToContainer(new JTableModel());
		JScrollPane scrollPane;
		//scrollPane = new JScrollPane(containerTable);
				
		//Frame Layout
		frame.getContentPane().removeAll();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setSize(1000,600);
		frame.setVisible(true);//making the frame visible  */
		
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
