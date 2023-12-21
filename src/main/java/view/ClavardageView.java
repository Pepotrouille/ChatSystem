package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import model.Clavardage;

public class ClavardageView extends Container implements ActionListener{
	
	private JButton boutonEnvoyer;
    private JTextField messageAEnvoyer;
	
	public ClavardageView(Clavardage clavardage)
	{
		AjouterChampsDeSaisieAvecFormat(messageAEnvoyer, 50 ,450, 600, 20);
		AjouterBoutonAvecFormat(boutonEnvoyer,700,450,180,20);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void AjouterBoutonAvecFormat(JButton bouton, int posX, int posY, int longueur, int hauteur) {

        bouton = new JButton("Envoyer");
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
