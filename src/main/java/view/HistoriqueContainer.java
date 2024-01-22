package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Historique;
import model.Message;

public class HistoriqueContainer extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 559078144401178903L;

	BoxLayout boxLayout;
	
	Historique historique;
	
	public HistoriqueContainer(Historique historique) {
		

        JScrollPane scrollPane = new JScrollPane(this);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        this.setBackground(Color.gray);
        int longueurListe = historique.GetMessages().size();
        for(int i = 0; i <longueurListe; i++)
        {
        	
        	AjouterMessage(historique.GetMessages().get(i));
        }
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void AjouterMessage(Message message)
	{
		AfficherMessageAvecFormat(message);
	}
	
	
	private void AfficherMessageAvecFormat(Message message)
	{
		JPanel panel = new JPanel();
		panel.setSize(300, 50);
		
		if(message.EstDeLUtilisateurActuel())	//Message provenant de l'utilisateur actuel (MOI)
		{
			panel.setBackground(Color.lightGray);
		}
		else 									//Message provenant de l'interlocuteur (LUI)
		{
			panel.setBackground(Color.cyan);
		}
		
		
		
		JLabel label = new JLabel(message.GetContenu());
		label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setSize(300, 50);
        panel.add(label);

        panel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(panel);
        
	}
}
