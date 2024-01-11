package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exceptions.ClavardageNonExistantException;
import model.Clavardage;

public class ClavardageView extends Container implements ActionListener{
	
	private JButton boutonEnvoyer;
	private JButton boutonClore;
    private JTextField messageAEnvoyer;
    
    Clavardage clavardage;
	
	public ClavardageView(Clavardage clavardage)
	{
		messageAEnvoyer = new JTextField();
		AjouterChampsDeSaisieAvecFormat(messageAEnvoyer, 50 ,450, 600, 20);
        boutonEnvoyer = new JButton("Envoyer");
		AjouterBoutonAvecFormat(boutonEnvoyer, 700,450,180,20);
        boutonClore = new JButton("Clore Clavardage");
		AjouterBoutonAvecFormat(boutonClore, 700,30,180,20);
		this.clavardage = clavardage;
		
		System.out.println(this.clavardage.GetHistorique().GetMessages()); // Pour tester
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == boutonEnvoyer)
		{
			if(clavardage.EstValide())
			{
				clavardage.EnvoyerMessage(messageAEnvoyer.getText());
				messageAEnvoyer.setText("");
			}
			else 
			{
				JOptionPane.showMessageDialog(this,"Conversation invalide. Attente de liaison avec l'interlocuteur");
				
			}
			
		}
		else if(e.getSource() == boutonClore)
		{
			try {
				clavardage.CloreClavardage();
			} catch (ClavardageNonExistantException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this,"Cloture invalide. Clavardage non existant");
			}
			MainView.AfficherUtilisateursEnLigne();
		}
		
	}
	
	private void AjouterBoutonAvecFormat(JButton bouton, int posX, int posY, int longueur, int hauteur) {

        bouton.setFont(new Font("Arial", Font.PLAIN, 15));
        bouton.setSize(longueur, hauteur);
        bouton.setLocation(posX, posY);
        bouton.addActionListener(this);
        add(bouton);
	}
	
	private void AjouterChampsDeSaisieAvecFormat(JTextField champsSaisie, int posX, int posY, int longueur, int hauteur) {

		champsSaisie = new JTextField();
		champsSaisie.setFont(new Font("Arial", Font.PLAIN, 15));
		champsSaisie.setSize(longueur, hauteur);
		champsSaisie.setLocation(posX, posY);
		champsSaisie.addActionListener(this);
        add(champsSaisie);
	}
	
}
