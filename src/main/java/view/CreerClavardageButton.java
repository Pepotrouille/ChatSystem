package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.ClavardageController;
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
				Clavardage newClavardage = ClavardageController.GetInstance().NouveauClavardage(this.utilisateur);
				MainView.AfficherClavardage(newClavardage);
			}
	}
}
