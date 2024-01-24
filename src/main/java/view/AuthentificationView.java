package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Random;
import java.net.DatagramSocket;
import java.net.InetAddress;

import model.Utilisateur;
import controller.AuthentificationController;
import controller.BroadcastController;
import exceptions.ErreurConnexionException;

public class AuthentificationView extends Container implements ActionListener{
	
	
	private static final long serialVersionUID = 1L;
	
	// Composants de l'interface
    private JTextField tlogin;
    private JTextField tpwd;
    private JTextField tpseudo;
    private JButton buttonConnect;

	public AuthentificationView()
	{
		JLabel title = new JLabel("Chat System");
		AjouterLabelAvecFormat(title, 30, 300, 30, 350, 30);
        
        JLabel login = new JLabel("Login");
		AjouterLabelAvecFormat(login, 20, 200, 100, 150, 40);

        tlogin = new JTextField();
		AjouterChampsDeSaisieAvecFormat(tlogin, 15, 350, 100, 250, 30);
        
        JLabel pwd = new JLabel("Mot de passe");
		AjouterLabelAvecFormat(pwd, 20, 200, 150, 150, 40);

        tpwd = new JTextField();
		AjouterChampsDeSaisieAvecFormat(tpwd, 15, 350, 150, 250, 30);

        JLabel pseudo = new JLabel("Pseudo");
		AjouterLabelAvecFormat(pseudo, 20, 200, 250, 150, 40);
		
        tpseudo = new JTextField();
		AjouterChampsDeSaisieAvecFormat(tpseudo, 15, 350, 250, 250, 30);

        buttonConnect = new JButton("Se connecter");
        AjouterBoutonAvecFormat(buttonConnect, 250, 300, 180, 20);
        
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
    			
    			//Aller aux menu clavardages
    			
    			//Pour l'instant : aller aux paramètres du compte
    			try (final DatagramSocket datagramSocket = new DatagramSocket()) {
    				
                	datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
                	
                	//Sélection du pseudo
                	String pseudoString;
                	if(tpseudo.getText().isEmpty())
                	{

                    	Random random = new Random();
                    	int id = random.nextInt(2147483646);
                    	pseudoString = new String("" + id + "");
                	}
                	else
                	{
                		pseudoString = tpseudo.getText();
                	}
                	
                	// On initialise le nouvel utilisateur avec son @IP et un pseudo random
        			Utilisateur u = new Utilisateur(datagramSocket.getLocalAddress().getHostAddress().toString(), pseudoString);
        			model.Utilisateur.SetUtilisateurActuel(u);
    				System.out.println(Utilisateur.GetUtilisateurActuel().GetPseudo());
        			BroadcastController.GetInstance().Connexion(pseudoString);
        			
        			// Cas 1 : Utilisateur admin
        			if (tlogin.getText().equals("admin")) {
        				model.Utilisateur.GetUtilisateurActuel().SetAdmin(1);
        				System.out.println(Utilisateur.GetUtilisateurActuel().GetPseudo());
        				MainView.AfficherParametresDuCompte(model.Utilisateur.GetUtilisateurActuel());
        			}
        			// Cas 2 : Utilisateur normal
        			else { 
        				MainView.AfficherParametresDuCompte(model.Utilisateur.GetUtilisateurActuel());	
        			}
    			}
    			catch (Exception ex) {
    				ex.printStackTrace();
    			}
    		}
    		catch (ErreurConnexionException exc)
    		{
    			// La connexion a échoué
    			String def = "";
    			tlogin.setText(def);
    			tpwd.setText(def);
    			exc.printStackTrace();
    			JOptionPane.showMessageDialog(this,"Le login et/ou le mot de passe est incorrect. Veuillez réeesayer.");
    		}
    		catch(SQLException exc)
    		{
    			// Erreur de la BDD
    			exc.printStackTrace();
    			JOptionPane.showMessageDialog(this,"Il y a eu une erreur lors de la connexion avec la base de donnée. Veuillez réessayer.");
    		}
    	}
    }
    
    
	
	private void AjouterLabelAvecFormat(JLabel label, int tailleText, int posX, int posY, int longueur, int hauteur) {

        label.setFont(new Font("Arial", Font.PLAIN, tailleText));
        label.setSize(longueur, hauteur);
        label.setLocation(posX, posY);
        add(label);
	}
	
	private void AjouterBoutonAvecFormat(JButton bouton, int posX, int posY, int longueur, int hauteur) {

        bouton.setFont(new Font("Arial", Font.PLAIN, 15));
        bouton.setSize(longueur, hauteur);
        bouton.setLocation(posX, posY);
        bouton.addActionListener(this);
        add(bouton);
	}
	
	private void AjouterChampsDeSaisieAvecFormat(JTextField champsSaisie, int tailleText, int posX, int posY, int longueur, int hauteur) {

		champsSaisie.setFont(new Font("Arial", Font.PLAIN, tailleText));
		champsSaisie.setSize(longueur, hauteur);
		champsSaisie.setLocation(posX, posY);
        add(champsSaisie);
	}
}