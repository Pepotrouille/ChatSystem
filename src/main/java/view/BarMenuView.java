package view;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class BarMenuView extends JMenuBar implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	
	private JButton optionDisconnect;
	private JButton optionAccountParameters;
	private JButton optionCreateDiscussion;
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
		optionCreateDiscussion = new JButton("Créer un clavardage");
		optionCreateDiscussion.setFont(new Font("Arial", Font.PLAIN, fontSize));
		optionCreateDiscussion.setSize(buttonLength,buttonHeight);
		optionCreateDiscussion.addActionListener(this);
		menuPostes.add(optionCreateDiscussion);
		//--
		optionConsultDiscussions = new JButton("Utilisateurs en ligne");
		optionConsultDiscussions.setFont(new Font("Arial", Font.PLAIN, fontSize));
		optionConsultDiscussions.setSize(buttonLength,buttonHeight);
		optionConsultDiscussions.addActionListener(this);
		menuPostes.add(optionConsultDiscussions);
		
		this.add(menuPostes);
		
		
		
		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == optionDisconnect) {
            //MainView.ShowAnthentification();
            /* Réinitialiser l'utilisateur */
            // model.User.SetCurrentUser(null);
        }
        else if (e.getSource() == optionAccountParameters) {
        	/* Afficher les paramètres de compte de l'utilisateur */
            // MainView.ShowAccountParameter(model.User.GetCurrentUser());
            
        }
        else if (e.getSource() == optionCreateDiscussion) {
        	/* Céer une discussion */
            // MainView.ShowCreateDiscussion();
        	MainView.AfficherUtilisateursEnLigne())
        }
        else if (e.getSource() == optionConsultDiscussions) {
        	/* Afficher la liste des utilisateurs en ligne */
            // MainView.ShowSeeAllUsers();
        	MainView.AfficherClavardagesEnCours()
        }
    }
	
}
