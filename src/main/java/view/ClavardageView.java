package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Clavardage;

public class ClavardageView extends Container implements ActionListener{
	
	private JButton boutonEnvoyer;
    private JTextField messageAEnvoyer;
    
    Clavardage clavardage;
	
	public ClavardageView(Clavardage clavardage)
	{
		messageAEnvoyer = new JTextField();
		AjouterChampsDeSaisieAvecFormat(messageAEnvoyer, 50 ,450, 600, 20);
		AjouterBoutonAvecFormat(boutonEnvoyer,700,450,180,20);
		this.clavardage = clavardage;
		
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
		
	}
	
	private void AjouterBoutonAvecFormat(JButton bouton, int posX, int posY, int longueur, int hauteur) {

        boutonEnvoyer = new JButton("Envoyer");
        boutonEnvoyer.setFont(new Font("Arial", Font.PLAIN, 15));
        boutonEnvoyer.setSize(longueur, hauteur);
        boutonEnvoyer.setLocation(posX, posY);
        boutonEnvoyer.addActionListener(this);
        add(boutonEnvoyer);
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
