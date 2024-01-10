 package view;

import model.TableUtilisateurs;
import model.Utilisateur;

import javax.swing.*;

import controller.BroadcastController;
import controller.SignalEnvoiBroadcastController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountParameterView extends Container implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	protected JButton buttonChangerPseudo;
	
	protected BroadcastController broadcast_controller;
	
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
	    buttonChangerPseudo.setFont(new Font("Arial", Font.PLAIN, 15));
	    buttonChangerPseudo.setSize(250, 20);
	    buttonChangerPseudo.setLocation(30, 300);
	    buttonChangerPseudo.addActionListener(this);
	    this.add(buttonChangerPseudo);
	    
	    //this.pseudo_controller = new PseudoController(TableUtilisateurs.GetInstance(), utilisateur, new SignalEnvoiBroadcastController());
		this.broadcast_controller = new BroadcastController();
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
		    tfield.setText(NouveauPseudo);
		    
		    JOptionPane.showMessageDialog(jFrame, "Votre nouveau pseudo est : " + NouveauPseudo);
			
		}
	}
	
	public JLabel getTfield()
	{
		return this.tfield;
	}
}
