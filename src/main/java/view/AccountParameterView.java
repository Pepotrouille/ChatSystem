 package view;

import model.Utilisateur;

import javax.swing.*;

import controller.BroadcastController;
import exceptions.PseudoDejaPrisException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountParameterView extends Container implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	protected JButton buttonChangerPseudo;
	
	
	private JLabel field, tfield;
	

	public AccountParameterView(Utilisateur utilisateur)
	{
		
		//----------Mise en place de l'interface principale

        

        //Pseudo d'utilisateur(variable)
		
		//AddJLabelWithFormat("Pseudo", utilisateur.GetPseudo(), 100);
		
		//Variables locales
        int widthBoxes = 150;
        int leftAlignementBoxes = 180;
        //--------
      
		field = new JLabel("Pseudo : ");
		field.setFont(new Font("Arial", Font.PLAIN, 20));
		field.setSize(150, 20);
		field.setLocation(30, 100);
		this.add(field);
        
        tfield = new JLabel(utilisateur.GetPseudo());
        tfield.setFont(new Font("Arial", Font.PLAIN, 15));
        tfield.setSize(widthBoxes, 250);
        tfield.setLocation(leftAlignementBoxes, 100);
        tfield.setVerticalAlignment(JLabel.TOP);
        this.add(tfield);
		
        //Adresse IP(statique)

        AddJLabelWithFormat("IP", utilisateur.GetIP(), 180);

        
		//Button permet de changer le pseudo
        buttonChangerPseudo = new JButton("Changer mon pseudo");
        AjouterBoutonAvecFormat(buttonChangerPseudo, 30, 300, 250, 20);
	    
	}
	
	private void AddJLabelWithFormat(String FieldName, String FieldString, int positionY)
	{

		//Variables locales
        int widthBoxes = 150;
        int leftAlignementBoxes = 180;
        //--------
		
		JLabel field = new JLabel(FieldName + " : ");
		field.setFont(new Font("Arial", Font.PLAIN, 20));
		field.setSize(150, 20);
		field.setLocation(30, positionY);
		this.add(field);
        
        JLabel tfield = new JLabel(FieldString);
        tfield.setFont(new Font("Arial", Font.PLAIN, 15));
        tfield.setSize(widthBoxes, 250);
        tfield.setLocation(leftAlignementBoxes, positionY);
        tfield.setVerticalAlignment(JLabel.TOP);
        this.add(tfield);	
	}
	
	// method actionPerformed()
	// to get the action performed
	// by the user and act accordingly
	public void actionPerformed(ActionEvent actionEvent) {
		
		if (actionEvent.getSource() == buttonChangerPseudo) {
		
			Utilisateur utilisateur = Utilisateur.GetUtilisateurActuel();
		    String NouveauPseudo = JOptionPane.showInputDialog(this, "Entrez votre nouveau pseudo");
		    
		    // Mise à jour du pseudo 
		    try{
		    	BroadcastController.GetInstance().ChangerPseudo(NouveauPseudo);
			    utilisateur.SetPseudo(NouveauPseudo);
			    
			    // Reset le label
			    tfield.setText(NouveauPseudo);
			    JOptionPane.showMessageDialog(this, "Votre nouveau pseudo est : " + NouveauPseudo);
		    }
		    catch(PseudoDejaPrisException exception)
		    {
		    	JOptionPane.showMessageDialog(this, "Le pseudo " + NouveauPseudo +" est déjà pris. Conservation de l'ancien pseudo : " + utilisateur.GetPseudo());
		    }
		    
		    
		    
			
		}
	}
	
	public JLabel getTfield()
	{
		return this.tfield;
	}
	

	protected void AjouterBoutonAvecFormat(JButton bouton, int posX, int posY, int longueur, int hauteur) {

	    bouton.setFont(new Font("Arial", Font.PLAIN, 15));
	    bouton.setSize(longueur, hauteur);
	    bouton.setLocation(posX, posY);
	    bouton.addActionListener(this);
	    this.add(bouton);
	}
}
