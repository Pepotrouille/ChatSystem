package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.AuthentificationController;



public class CreerCompteView extends Container implements ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7460612861856895083L;
	//------------------------------ATTRIBUTES-------------------------------

	private JTextField inputLogin;
	private JTextField inputPassword;
	private JLabel informationBox;
	private JButton buttonCreate, buttonReturn;

	//Local Constantes
    int widthBoxes = 150;
    int leftAlignementLabels = 150;
    int leftAlignementBoxes = 350;
    //--------
	//-------------------------------METHODS--------------------------------
	
	//---Constructor
    public CreerCompteView() {
		JLabel labelTitle = new JLabel("CREER UN NOUVEAU COMPTE(admin)");
		labelTitle.setFont(new Font("Arial", Font.PLAIN, 30));
		labelTitle.setSize(700, 30);
		labelTitle.setLocation(200, 30);
	    this.add(labelTitle);
	
	    
	    //---Login
	    AddJLabelWithFormat("Login", 100);
		inputLogin = new JTextField();
		SetJTextFieldWithFormat(inputLogin, 100);
	    

	    //---Password
	    AddJLabelWithFormat("Mot de Passe", 140);
		inputPassword = new JTextField();
		SetJTextFieldWithFormat(inputPassword,140);
	    
	
	    buttonCreate = new JButton("Creer");
	    buttonCreate.setFont(new Font("Arial", Font.PLAIN, 15));
	    buttonCreate.setSize(120, 20);
	    buttonCreate.setLocation(300, 450);
	    buttonCreate.addActionListener(this);
	    this.add(buttonCreate);
	
	    informationBox = new JLabel("");
	    informationBox.setFont(new Font("Arial", Font.PLAIN, 20));
	    informationBox.setSize(900, 500);
	    informationBox.setLocation(100, 50);
	    this.add(informationBox); 
	    
	    buttonReturn = new JButton("Retourner");
	    buttonReturn.setFont(new Font("Arial", Font.PLAIN, 15));
	    buttonReturn.setSize(120, 20);
	    buttonReturn.setLocation(500, 450);
	    buttonReturn.addActionListener(this);
	    this.add(buttonReturn);

    }

// method actionPerformed()
// to get the action performed
// by the user and act accordingly
	public void actionPerformed(ActionEvent e) {
		
		// Button créer un noveau compte
	    if (e.getSource() == buttonCreate) {
	        	if(inputLogin.getText() != "" && inputPassword.getText() != "")
	        	{
	        		// Ecrire la nouvelle authentification dans la BDD
	        		try
	        		{
	        			AuthentificationController.GetInstance().CreerCompte(inputLogin.getText(), inputPassword.getText());
	        			informationBox.setText("Le nouveau compte a bien été créé ! L'utilisateur peut désormais s'authentifier");
	        			ResetFields();
	        		}
	        		catch(Exception exc)
	        		{
	        			exc.printStackTrace();
	        			informationBox.setText("Il y a eu une erreur lors de la connexion avec la base de donnée. Veuillez réessayer.");
	        		}
	        	}
	        	else
	        	{
	        		informationBox.setText("Veuillez au moins remplir les cases login et mot de passe.");
	        	}
	   }
	    
	   // Button retourner aux paramètres du compte
	   else if (e.getSource() == buttonReturn) {
	    	MainView.AfficherParametresDuCompteAdmin(model.Utilisateur.GetUtilisateurActuel());
	   }
	    
	}

	private void ResetFields()
	{
		String nullString = "";
	    inputLogin.setText(nullString);
	    inputPassword.setText(nullString);
	}

	private void AddJLabelWithFormat(String FieldName, int positionY)
	{
	    JLabel label = new JLabel(FieldName);
	    label.setFont(new Font("Arial", Font.PLAIN, 20));
	    label.setSize(150, 30);
	    label.setLocation(leftAlignementLabels, positionY);
	    this.add(label);
	}
	
	private void SetJTextFieldWithFormat(JTextField textField, int positionY)
	{		
		textField.setFont(new Font("Arial", Font.PLAIN, 15));
		textField.setSize(250, 30);
		textField.setLocation(leftAlignementBoxes, positionY);
	    this.add(textField);	
	}
}
