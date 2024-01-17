package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;

import exceptions.ClavardageNonExistantException;
import exceptions.DateInvalideException;
import exceptions.MessageInvalideException;
import model.Clavardage;
import model.Message;
import model.TableUtilisateurs;
import model.MaDate;

public class ClavardageView extends Container implements ActionListener{
	
	private JButton boutonEnvoyer;
	private JButton boutonClore;
    private JTextField messageAEnvoyer;
    private JPanel chat_area;
    
    Clavardage clavardage;
	
	public ClavardageView(Clavardage clavardage)
	{
		messageAEnvoyer = new JTextField();
		AjouterChampsDeSaisieAvecFormat(messageAEnvoyer, 50 ,450, 600, 20);
        boutonEnvoyer = new JButton("Envoyer");
		AjouterBoutonAvecFormat(boutonEnvoyer, 700,450,180,20);
        boutonClore = new JButton("Clore Clavardage");
		AjouterBoutonAvecFormat(boutonClore, 700,480,180,20);
		chat_area = new JPanel(new BorderLayout());
		AjouterChatArea(chat_area, 50, 30, 800, 400);
		this.clavardage = clavardage;
		
		System.out.println(this.clavardage.GetHistorique().GetMessages()); // Pour tester
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == boutonEnvoyer)
		{
			if(clavardage.EstValide())
			{
				String msg = messageAEnvoyer.getText();
				
				clavardage.EnvoyerMessage(msg);
				
				try {
					// Ajouter le message dans l'histoire des messages
					clavardage.GetHistorique().AjouterMessage(new Message(msg, new MaDate(), true));
					
					// Afficher les messages dans la fenetre
					// ArrayList<Object> listeMessages = new ArrayList<Object>(clavardage.GetHistorique().GetMessages());
					addMessage(clavardage.GetHistorique().GetIPSource(), msg);
					
				}
				catch (SQLException e1) {
					System.out.println("SQLException");
					e1.printStackTrace();
				}
				catch (MessageInvalideException e2) {
					System.out.println("MessageInvalideException");
					e2.printStackTrace();
				}
				catch (DateInvalideException e3) {
					System.out.println("DateInvalideException");
					e3.printStackTrace();
				}
				
				messageAEnvoyer.setText("");
			}
			else 
			{
				JOptionPane.showMessageDialog(this,"Conversation invalide. Attente de liaison avec l'interlocuteur");
				
			}
			
		}
		else if(e.getSource() == boutonClore)
		{
			try {
				clavardage.CloreClavardage();
			} catch (ClavardageNonExistantException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this,"Cloture invalide. Clavardage non existant");
			}
			MainView.AfficherUtilisateursEnLigne();
		}
		
	}
	
	private void AjouterBoutonAvecFormat(JButton bouton, int posX, int posY, int longueur, int hauteur) {

        bouton.setFont(new Font("Arial", Font.PLAIN, 15));
        bouton.setSize(longueur, hauteur);
        bouton.setLocation(posX, posY);
        bouton.addActionListener(this);
        add(bouton);
	}
	
	private void AjouterChampsDeSaisieAvecFormat(JTextField champsSaisie, int posX, int posY, int longueur, int hauteur) {

		champsSaisie.setFont(new Font("Arial", Font.PLAIN, 15));
		champsSaisie.setSize(longueur, hauteur);
		champsSaisie.setLocation(posX, posY);
		champsSaisie.addActionListener(this);
        add(champsSaisie);
	}
	
	private void AjouterChatArea(JPanel chat, int posX, int posY, int longueur, int hauteur) {
		
		/* chat_area = new JPanel();
        chat_area.setLayout(new BoxLayout(chat_area,BoxLayout.Y_AXIS));
        chat_area.setPreferredSize(new Dimension(100, 50)); */
		
		chat_area.setFont(new Font("Arial", Font.PLAIN, 15));
        chat_area.setSize(longueur, hauteur);
        chat_area.setLocation(posX, posY);
        //chat_area.setAlignmentX(Component.RIGHT_ALIGNMENT);
        //chat_area.setLayout(new BoxLayout(chat_area, BoxLayout.Y_AXIS));
        add(chat_area);
	}
	
	private void addMessage(String sender, String message) {
        JPanel message_panel = new JPanel();
        JLabel senderLabel = new JLabel(sender);
        //sent?senderLabel=new JLabel("You"):new JLabel("Server");
        JTextArea messageArea = new JTextArea(message);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setEditable(false);
        message_panel.add(senderLabel);
        message_panel.add(messageArea);
        chat_area.add(message_panel, BorderLayout.EAST);
        MainView.GetFrame().revalidate(); // Revalidate the frame to reflect the changes
    }
}
