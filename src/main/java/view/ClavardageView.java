package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JFrame;

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
    private static JFrame frame;
    
    Clavardage clavardage;
	
	public ClavardageView(Clavardage clavardage)
	{
		messageAEnvoyer = new JTextField();
		AjouterChampsDeSaisieAvecFormat(messageAEnvoyer, 50 ,450, 600, 20);
        boutonEnvoyer = new JButton("Envoyer");
		AjouterBoutonAvecFormat(boutonEnvoyer, 700,450,180,20);
        boutonClore = new JButton("Clore Clavardage");
		AjouterBoutonAvecFormat(boutonClore, 700,480,180,20);
		this.clavardage = clavardage;
		
		System.out.println(this.clavardage.GetHistorique().GetMessages()); // Pour tester
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == boutonEnvoyer)
		{
			if(clavardage.EstValide())
			{
				clavardage.EnvoyerMessage(messageAEnvoyer.getText());
				
				try {
					// Ajouter le message dans l'histoire des messages
					clavardage.GetHistorique().AjouterMessage(new Message(messageAEnvoyer.getText(), new MaDate(), true));
					
					// Afficher les messages dans la fenetre
					ArrayList<Object> listeMessages = new ArrayList<Object>(clavardage.GetHistorique().GetMessages());
					UpdateGUIMessage(new MessageTable(listeMessages), true);
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

	private void UpdateGUIMessage(MessageTable messageTable, boolean withMenuBar)
	{
				
		//Creation of the table 
		Container containerTable = TableToContainer.GetInstance().MessageTableToContainer(messageTable);
		JScrollPane scrollPane;
		scrollPane = new JScrollPane(containerTable);
				
		//Frame Layout
		frame.getContentPane().removeAll();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setSize(1000,600);
		frame.setVisible(true);//making the frame visible  
		
	}
}
