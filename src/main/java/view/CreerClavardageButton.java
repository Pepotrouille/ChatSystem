package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import controller.ClavardageController;
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
				} catch (SQLException | MessageInvalideException | DateInvalideException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
					//Ajouter Pop up
				}
				MainView.AfficherClavardage(newClavardage);
			}
	}
}
