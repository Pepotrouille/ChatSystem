package view;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import controller.BroadcastController;

public class BarMenuView extends JMenuBar implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	
	private JButton optionDisconnect;
	private JButton optionAccountParameters;
	private JButton optionOnlineUsers;
	private JButton optionConsultDiscussions;
	
	public BarMenuView() {
		
		int buttonLength = 70;
		int buttonHeight = 20;
		int fontSize = 12;
		
		//----------Mise en place de la barre d'options
		
		//Premier Menu : Utilisateur
		JMenu menuUtilisateur = new JMenu("Utilisateur");
		optionDisconnect = new JButton("Se déconnecter");
		optionDisconnect.setFont(new Font("Arial", Font.PLAIN, fontSize));
		optionDisconnect.setSize(buttonLength,buttonHeight);
		optionDisconnect.addActionListener(this);
		menuUtilisateur.add(optionDisconnect);
		//---
		optionAccountParameters = new JButton("Paramètres du compte");
		optionAccountParameters.setFont(new Font("Arial", Font.PLAIN, fontSize));
		optionAccountParameters.setSize(buttonLength,buttonHeight);
		optionAccountParameters.addActionListener(this);
		menuUtilisateur.add(optionAccountParameters);
		
		this.add(menuUtilisateur);

		//Second Menu : Clavardage
		JMenu menuPostes = new JMenu("Clavardage");
		optionOnlineUsers = new JButton("Utilisateurs en ligne");
		optionOnlineUsers.setFont(new Font("Arial", Font.PLAIN, fontSize));
		optionOnlineUsers.setSize(buttonLength,buttonHeight);
		optionOnlineUsers.addActionListener(this);
		menuPostes.add(optionOnlineUsers);
		//--
		optionConsultDiscussions = new JButton("Afficher clavardages en cours");
		optionConsultDiscussions.setFont(new Font("Arial", Font.PLAIN, fontSize));
		optionConsultDiscussions.setSize(buttonLength,buttonHeight);
		optionConsultDiscussions.addActionListener(this);
		menuPostes.add(optionConsultDiscussions);
		
		this.add(menuPostes);
		
		
		
		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == optionDisconnect) {
            /* Déconnexion de l'utilisateur */
        	System.out.println("Déconnexion");
        	model.Utilisateur.SetUtilisateurActuel(null);
        	BroadcastController.GetInstance().Deconnexion();
        	MainView.AfficherAuthentification();
        }
        else if (e.getSource() == optionAccountParameters) {
        	/* Afficher les paramètres de compte de l'utilisateur */
        	MainView.AfficherParametresDuCompte(model.Utilisateur.GetUtilisateurActuel());
        }
        else if (e.getSource() == optionOnlineUsers) {
        	/* Afficher la liste des utilisateurs en ligne */
            // MainView.ShowCreateDiscussion();
        	MainView.AfficherUtilisateursEnLigne();
        }
        else if (e.getSource() == optionConsultDiscussions) {
        	/* Affiche les discussions en cours */
            // MainView.ShowSeeAllUsers();
        	MainView.AfficherClavardagesEnCours();
        }
    }
	
}
