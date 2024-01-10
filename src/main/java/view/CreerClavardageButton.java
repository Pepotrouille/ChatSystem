package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import controller.ClavardageController;
import exceptions.ClavardageDejaCree;
import exceptions.ClavardageNonExistantException;
import exceptions.DateInvalideException;
import exceptions.MessageInvalideException;
import model.Clavardage;
import model.Utilisateur;

public class CreerClavardageButton extends JButton implements ActionListener{
	
	private static final long serialVersionUID = 7977849812532563837L;
	
	private Utilisateur utilisateur;
	
	public CreerClavardageButton(String text, Utilisateur utilisateur)
	{
		super(text);
		this.utilisateur = utilisateur;
		this.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this)
			{
				Clavardage newClavardage = null;
				try {
					newClavardage = ClavardageController.GetInstance().NouveauClavardage(this.utilisateur);
				} 
				//
				catch (SQLException | MessageInvalideException | DateInvalideException e1) {
					JOptionPane.showMessageDialog(this,"Problème lors de la création de l'historique. Veuillez réessayer.");
					e1.printStackTrace();
				}
				//Cas où le clavardage est déjà existant
				catch(ClavardageDejaCree e4)
				{
					Clavardage clavardageExistant;
					try {
						clavardageExistant = ClavardageController.GetInstance().GetClavardage(utilisateur.GetIP());
						MainView.AfficherClavardage(clavardageExistant);
					} catch (ClavardageNonExistantException e41) {
						JOptionPane.showMessageDialog(this,"Problème lors de l'accès au clavardage. Veuillez réessayer.");
						e41.printStackTrace();
					}
				}
				MainView.AfficherClavardage(newClavardage);
			}
	}
}
