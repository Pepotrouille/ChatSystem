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
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == buttonChangerPseudo) {
			
			//Utilisateur utilisateur = pseudo_controller.getUtilisateur();
			Utilisateur utilisateur = Utilisateur.utilisateurActuel;
			
			JFrame jFrame = new JFrame();
		    String NouveauPseudo = JOptionPane.showInputDialog(jFrame, "Entrez votre nouveau pseudo");
		    
		    
		    // Mettre à jour le pseudo 
		    //this.pseudo_controller.changePseudo(NouveauPseudo);
		    this.broadcast_controller.ChangerPseudo(NouveauPseudo);
		    utilisateur.SetPseudo(NouveauPseudo);
		    
		    // Reset le label
		    this.getTfield().setText(NouveauPseudo);
		    
		    JOptionPane.showMessageDialog(jFrame, "Votre nouveau pseudo est : " + NouveauPseudo);
		    
		}
		
		else if (e.getSource() == buttonCreerCompte) {
			MainView.AfficherCreerCompte();
		}
	}
}