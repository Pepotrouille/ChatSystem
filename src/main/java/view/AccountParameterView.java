 package view;

import java.awt.Container;
import model.Utilisateur;

import javax.swing.*;
import java.awt.*;

public class AccountParameterView extends Container{

	private static final long serialVersionUID = 1L;
	

	public AccountParameterView(Utilisateur utilisateur)
	{
		
		//----------Mise en place de l'interface principale

        

        //Nom User
		
		AddJLabelWithFormat("Pseudo", utilisateur.GetPseudo(), 100);
		
        //Description Telephone

        AddJLabelWithFormat("IP", utilisateur.GetIP(), 180);

        
		
		          
		 
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
	
	
}
