package view;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.sql.SQLException;
import controller.AuthentificationController;
import exceptions.ErreurConnexionException;

public class AuthentificationView extends Container implements ActionListener{
	
	
	private static final long serialVersionUID = 1L;
	
	// Composants de l'interface
    private JTextField tlogin;
    private JTextField tpwd;
    private JTextArea annonce;
    private JButton buttonConnect;

	public AuthentificationView()
	{
		JLabel title = new JLabel("Chat System");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(350, 30);
        title.setLocation(300, 30);
        add(title);
        
        JLabel login = new JLabel("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 20));
        login.setSize(150, 40);
        login.setLocation(200, 100);
        add(login);

        tlogin = new JTextField();
        tlogin.setFont(new Font("Arial", Font.PLAIN, 15));
        tlogin.setSize(250, 30);
        tlogin.setLocation(350, 100);
        add(tlogin);
        
        JLabel pwd = new JLabel("Mot de passe");
        pwd.setFont(new Font("Arial", Font.PLAIN, 20));
        pwd.setSize(150, 40);
        pwd.setLocation(200, 150);
        add(pwd);

        tpwd = new JTextField();
        tpwd.setFont(new Font("Arial", Font.PLAIN, 15));
        tpwd.setSize(250, 30);
        tpwd.setLocation(350, 150);
        add(tpwd);
        
        buttonConnect = new JButton("Se connecter");
        buttonConnect.setFont(new Font("Arial", Font.PLAIN, 15));
        buttonConnect.setSize(180, 20);
        buttonConnect.setLocation(250, 250);
        buttonConnect.addActionListener(this);
        add(buttonConnect);
        
        annonce = new JTextArea();
        annonce.setFont(new Font("Arial", Font.PLAIN, 15));
        annonce.setSize(500, 100);
        annonce.setLocation(200, 300);
        annonce.setLineWrap(true);
        annonce.setEditable(false);
        add(annonce);
        
        setVisible(true);
	}
	
	// method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e) {
    	
    	AuthentificationController auth_controller = AuthentificationController.GetInstance();
    	
    	// Verifier si l'authentification existe
    	if (e.getSource() == buttonConnect) {
    		try 
    		{
    			// La connexion a réussi
    			auth_controller.Authentifier(tlogin.getText(), tpwd.getText());
    			annonce.setEditable(false);
    		}
    		catch (ErreurConnexionException exc)
    		{
    			// La connexion a échoué
    			String def = "";
    			tlogin.setText(def);
    			tpwd.setText(def);
    			annonce.setText("Le login et/ou le mot de passe est incorrect. Veuillez réeesayer.");
    			annonce.setEditable(false);
    		}
    		catch(SQLException exc)
    		{
    			// Erreur de la BDD
    			exc.printStackTrace();
    			annonce.setText("Il y a eu une erreur lors de la connexion avec la base de donnée. Veuillez réessayer.");
    		}
    	}
    }
}