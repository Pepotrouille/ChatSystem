package view;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import exceptions.EchecManipulationBDDException;
import model.Utilisateur;

public class AccountParameterViewAdmin extends AccountParameterView 
{
	
	// Sous-classe de AccounyParameterView, qui sert que pour le compte "admin"
	// Tous les options + un button "Créer un nouveau compte"
	
	private static final long serialVersionUID = 1L;
	
	private JButton buttonCreerCompte;
	private JButton buttonResetBDDUtilisateurs;
	private JButton buttonResetBDDMessages;
	
	public AccountParameterViewAdmin(Utilisateur utilisateur)
	{
		super(utilisateur);
		
		//Bouton permettant de créer un nouveau compte
        buttonCreerCompte = new JButton("Créer un compte");
        AjouterBoutonAvecFormat(buttonChangerPseudo, 350, 300, 250, 20);

		//Bouton permettant de réinitialiser la BDD Utilisateurs
        buttonResetBDDUtilisateurs = new JButton("Réinitialiser Base de Données Utilisateurs");
        AjouterBoutonAvecFormat(buttonResetBDDUtilisateurs, 30, 350, 520, 20);

        //Bouton permettant de réinitialiser la BDD de messages
        buttonResetBDDMessages = new JButton("Réinitialiser Base de Données Messages");
        AjouterBoutonAvecFormat(buttonResetBDDMessages, 30, 400, 520, 20);
	}
	
	// method actionPerformed()
	// to get the action performed
	// by the user and act accordingly
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		if (e.getSource() == buttonCreerCompte) {
			MainView.AfficherCreerCompte();
		}
		if (e.getSource() == buttonResetBDDUtilisateurs) {
			try {
				controller.BDDAuthentificationController.GetInstance().SupprimerBaseAuthentification();
				controller.BDDAuthentificationController.GetInstance().CreerBaseAuthentification();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this,"Problème d'accès à la base de données.");
			} catch (EchecManipulationBDDException e1) {
				JOptionPane.showMessageDialog(this,"Problème lors de la réinitialisation.");
			}
		}
		if (e.getSource() == buttonResetBDDMessages) {
			try {
				controller.BDDMessageController.GetInstance().SupprimerBaseMessage();
				controller.BDDMessageController.GetInstance().CreerBaseMessage();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this,"Problème d'accès à la base de données.");
			}
		}
	}
}