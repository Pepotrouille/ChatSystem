package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*; 
import view.TableUtilisateursView;
import controller.BroadcastController;

public class MainViewTest extends JFrame implements ActionListener{
	
	private BroadcastController bc;
	
	// Composants de l'interface
	private Container c;
	private JLabel title;
	private JTextArea table_users;
	private JLabel id;
	private JLabel pseudo;
	private JButton connect;
	private JButton change_pseudo;
    private JButton disconnect;
    private JButton clavardageSoiMeme;
   
    
    // constructor, to initialize the components
    // with default values.
    public MainViewTest(BroadcastController bc) {
    	
    	this.bc = bc;
    	
    	setTitle("CHAT APPLICATION");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);
        
        title = new JLabel("Bienvenue à ChatApp!");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(350, 30);
        title.setLocation(300, 30);
        c.add(title);
        
        /* TableUtilisateursView table_view = new TableUtilisateursView();
        table_view.createAndShowGUI(); */
        
        table_users = new JTextArea();
        table_users.setFont(new Font("Arial", Font.PLAIN, 15));
        table_users.setSize(500, 200);
        table_users.setLocation(200, 100);
        table_users.setLineWrap(true);
        table_users.setEditable(false);
        c.add(table_users);
        
        id = new JLabel("Votre id est 10.1.25.19");
        id.setFont(new Font("Arial", Font.PLAIN, 20));
        id.setSize(700, 100);
        id.setLocation(240, 300);
        c.add(id);
        
        pseudo = new JLabel("Votre pseudo initial est PseudoZero");
        pseudo.setFont(new Font("Arial", Font.PLAIN, 20));
        pseudo.setSize(700, 100);
        pseudo.setLocation(240, 350);
        c.add(pseudo);
        
        connect = new JButton("Se connecter");
        connect.setFont(new Font("Arial", Font.PLAIN, 15));
        connect.setSize(180, 20);
        connect.setLocation(100, 450);
        connect.addActionListener(this);
        c.add(connect);
        
        change_pseudo = new JButton("Changer mon pseudo");
        change_pseudo.setFont(new Font("Arial", Font.PLAIN, 15));
        change_pseudo.setSize(300, 20);
        change_pseudo.setLocation(300, 450);
        change_pseudo.addActionListener(this);
        c.add(change_pseudo);
        
        disconnect = new JButton("Se déconnecter");
        disconnect.setFont(new Font("Arial", Font.PLAIN, 15));
        disconnect.setSize(180, 20);
        disconnect.setLocation(620, 450);
        disconnect.addActionListener(this);
        c.add(disconnect);
        

        clavardageSoiMeme = new JButton("Changer mon pseudo");
        clavardageSoiMeme.setFont(new Font("Arial", Font.PLAIN, 15));
        clavardageSoiMeme.setSize(300, 20);
        clavardageSoiMeme.setLocation(300, 550);
        clavardageSoiMeme.addActionListener(this);
        c.add(clavardageSoiMeme);
        
        setVisible(true);
        
    }
    
    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e) {
    	
    	if (e.getSource() == connect) {
    		bc.Connexion("Robert");
    	}
    	else if (e.getSource() == change_pseudo) {
    		bc.ChangerPseudo("Jean Michel");
    	}
    	else if (e.getSource() == disconnect) {
    		bc.Deconnexion();
    	}
    	else if(e.getSource() == clavardageSoiMeme)
    	{
    		
    	}
    }
    /*
    public static void main(String[] args) throws Exception {
        MainView f = new MainView(bc);
    }*/
}