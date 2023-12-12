package view;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.sql.SQLException;

public class AuthentificationView extends Container implements ActionListener{
	
	
	private static final long serialVersionUID = 1L;
	
	// Composants de l'interface
    private JTextField tlogin;
    private JTextField tpwd;
    private JTextArea annonce;

    private JButton buttonConnect;
    private JButton buttonCreateAccount;
    private JButton buttonResetTables;
    private JButton buttonResetTablesEmpty;

	
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
        
        buttonCreateAccount = new JButton("Creer un compte");
        buttonCreateAccount.setFont(new Font("Arial", Font.PLAIN, 15));
        buttonCreateAccount.setSize(180, 20);
        buttonCreateAccount.setLocation(450, 250);
        buttonCreateAccount.addActionListener(this);
        add(buttonCreateAccount);
        
        annonce = new JTextArea();
        annonce.setFont(new Font("Arial", Font.PLAIN, 15));
        annonce.setSize(500, 100);
        annonce.setLocation(200, 300);
        annonce.setLineWrap(true);
        annonce.setEditable(false);
        add(annonce);
        

        buttonResetTables = new JButton("Tables de test");
        buttonResetTables.setFont(new Font("Arial", Font.PLAIN, 15));
        buttonResetTables.setSize(250, 20);
        buttonResetTables.setLocation(600, 600);
        buttonResetTables.addActionListener(this);
        add(buttonResetTables);

        buttonResetTablesEmpty = new JButton("Reinitialiser les Tables");
        buttonResetTablesEmpty.setFont(new Font("Arial", Font.PLAIN, 15));
        buttonResetTablesEmpty.setSize(250, 20);
        buttonResetTablesEmpty.setLocation(200, 600);
        buttonResetTablesEmpty.addActionListener(this);
        add(buttonResetTablesEmpty);
        
        setVisible(true);
	}
	
	// method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e) {
    	
    }
}