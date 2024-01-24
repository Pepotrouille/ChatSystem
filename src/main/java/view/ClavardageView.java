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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.SignalReceptionBroadcastController;
import controller.SignalReceptionUnicastController;

import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;

import exceptions.ClavardageNonExistantException;
import exceptions.DateInvalideException;
import exceptions.MessageInvalideException;
import model.Clavardage;
import model.Message;
import model.TableUtilisateurs;
import view.MainView.AffichageActuel;
import model.MaDate;

public class ClavardageView extends Container implements ActionListener{
	
	private JButton boutonEnvoyer;
	private JButton boutonClore;
    private JTextField messageAEnvoyer;
    private JPanel chat_area;
    
    Clavardage clavardage;
    
    HistoriqueContainer historiqueContainer;
	
	public ClavardageView(Clavardage clavardage)
	{
		//Ajout de la barre pour écrire message à envoyer 
		messageAEnvoyer = new JTextField();
		AjouterChampsDeSaisieAvecFormat(messageAEnvoyer, 50 ,450, 600, 20);
		//Ajout du bouton pour envoyer message 
        boutonEnvoyer = new JButton("Envoyer");
		AjouterBoutonAvecFormat(boutonEnvoyer, 700,450,180,20);
		//Ajout du bouton pour clore clavardage
        boutonClore = new JButton("Clore Clavardage");
		AjouterBoutonAvecFormat(boutonClore, 700,480,180,20);
		
		//Ajout de l'affichage des messages
		
		/*chat_area = new JPanel(new BorderLayout());
		AjouterChatArea(chat_area, 50, 30, 800, 400);*/
		historiqueContainer = new HistoriqueContainer(clavardage.GetHistorique(), new JPanel());
		
		historiqueContainer.setSize(800, 400);
		historiqueContainer.setLocation(50, 30);
		this.add(historiqueContainer, BorderLayout.CENTER);
		
		this.clavardage = clavardage;
		
		clavardage.GetHistorique().PrintHistorique();

		System.out.println("-------Ajout de l'observeur ");
		clavardage.GetSignalReceptionUnicastController().AddMessageObserver(
				new SignalReceptionUnicastController.MessageObserver() {

					//En cas de réception de changement dans table d'adresse, refresh
					@Override
					public void handle(Message message) {
						historiqueContainer.AjouterMessage(message);
						System.out.println("-X-X-X-Actualisation avec Message : " + message.GetContenu());
					}
				}
		);
		
		
	}

	//---------Action Listener pour les boutons
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == boutonEnvoyer)
		{
			if(clavardage.EstValide())
			{
				String msg = messageAEnvoyer.getText();
				
				clavardage.EnvoyerMessage(msg);
				
				try {
					Message newMessage = new Message(msg, new MaDate(), true);
					// Ajouter le message dans l'histoire des messages
					clavardage.GetHistorique().AjouterMessage(newMessage);
					
					// Afficher les messages dans la fenetre
					historiqueContainer.AjouterMessage(newMessage);
					
					JFrame mainFrame = MainView.GetFrame();
					mainFrame.revalidate();
					mainFrame.repaint();
					
				}
				catch (SQLException e1) {
					JOptionPane.showMessageDialog(this,"Problème de connexion à la base de donnée");
					e1.printStackTrace();
				}
				catch (MessageInvalideException e2) {
					JOptionPane.showMessageDialog(this,"Message invalide. Veillez à ce que le champs de saisie ne soit pas vide");
					e2.printStackTrace();
				}
				catch (DateInvalideException e3) {
					JOptionPane.showMessageDialog(this,"Date invalide");
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
	
	
}
