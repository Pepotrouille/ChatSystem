package view;

import java.awt.*;
import javax.swing.*;
import controller.ClavardageController;
import controller.SignalReceptionBroadcastController;
import model.Clavardage;
import model.Utilisateur;
import model.TableUtilisateurs;
import java.util.ArrayList;

public class MainView {

	private static JFrame frame;
	
	enum AffichageActuel {CLAVARDAGE, PARAMETRES_COMPTE, CLAVARDAGES_EN_COURS, UTILISATEURS_EN_LIGNE, CREER_COMPTE, AUTHENTIFICATION};
	
	private static AffichageActuel affichageActuel;
	
	
	
	
	public MainView() {
		affichageActuel = AffichageActuel.AUTHENTIFICATION;
		SignalReceptionBroadcastController.GetInstance().AddUtilisateurObserver(
				new SignalReceptionBroadcastController.UtilisateurObserver() {

					//En cas de réception de changement dans table d'adresse, refresh
					@Override
					public void handle(String ip, String nouveauPseudo) {
						if (affichageActuel == AffichageActuel.CLAVARDAGES_EN_COURS)
						{
							AfficherClavardagesEnCours();
						}
						else if (affichageActuel == AffichageActuel.UTILISATEURS_EN_LIGNE)
						{
							AfficherUtilisateursEnLigne();
						}
						
					}
				}
		);
	}
	
	
	
	
	
	public static void AfficherClavardage(Clavardage clavardage)
	{
		UpdateGUI(new ClavardageView(clavardage), true);
		affichageActuel = AffichageActuel.CLAVARDAGE;
	}

	public static void AfficherParametresDuCompte(Utilisateur utilisateur)
	{
		if (Utilisateur.GetUtilisateurActuel().GetAdmin().equals(1))
		{
			UpdateGUI(new AccountParameterViewAdmin(utilisateur), true);
		}
		else 
		{
			UpdateGUI(new AccountParameterView(utilisateur), true);
		}
		affichageActuel = AffichageActuel.PARAMETRES_COMPTE;
	}

	public static void AfficherClavardagesEnCours()
	{
		ArrayList<Object> listeGenerique = new ArrayList<Object>(ClavardageController.GetInstance().GetClavardageEnCours());
		UpdateGUITable(new DataTable(listeGenerique, DataTable.TypeDataTable.Clavardage), true);
		affichageActuel = AffichageActuel.CLAVARDAGES_EN_COURS;
		
	}
	
	public static void AfficherUtilisateursEnLigne()
	{
		ArrayList<Object> listeGenerique = new ArrayList<Object>(TableUtilisateurs.GetInstance().GetListeUtilisateurs());
		TableUtilisateurs.GetInstance().AfficherListe();
		UpdateGUITable(new DataTable(listeGenerique, DataTable.TypeDataTable.Utilisateur), true);
		affichageActuel = AffichageActuel.UTILISATEURS_EN_LIGNE;
	}

	public static void AfficherCreerCompte()
	{
		
		UpdateGUI(new CreerCompteView(), true);

		affichageActuel = AffichageActuel.CREER_COMPTE;
	}
	
	
	public static void AfficherAuthentification()
	{
		UpdateGUI(new AuthentificationView(), false);
		affichageActuel = AffichageActuel.AUTHENTIFICATION;
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
	
	public static JFrame GetFrame() //MainView étant toujours actif, pas de problème où frame non définie.
	{
		return frame;
	}
}
