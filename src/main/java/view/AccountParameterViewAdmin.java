package view;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Utilisateur;

public class AccountParameterViewAdmin extends AccountParameterView 
{
	
	// Sous-classe de AccounyParameterView, qui sert que pour le compte "admin"
	// Tous les options + un button "Créer un nouveau compte"
	
	private static final long serialVersionUID = 1L;
	
	private JButton buttonCreerCompte;
	
	public AccountParameterViewAdmin(Utilisateur utilisateur)
	{
		super(utilisateur);
		
		//Button permet de créer un nouveau compte
        buttonCreerCompte = new JButton("Créer un compte");
	    buttonCreerCompte.setFont(new Font("Arial", Font.PLAIN, 15));
	    buttonCreerCompte.setSize(250, 20);
	    buttonCreerCompte.setLocation(350, 300);
	    buttonCreerCompte.addActionListener(this);
	    this.add(buttonCreerCompte);
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
	}
}