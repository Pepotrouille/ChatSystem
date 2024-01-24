package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Historique;
import model.Message;

public class HistoriqueContainer extends JScrollPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 559078144401178903L;

	BoxLayout boxLayout;
	
	Historique historique;
	
	JPanel layoutPanel;
	
	public HistoriqueContainer(Historique historique, JPanel panel) {

		super(panel);
		layoutPanel = panel;
		layoutPanel.setBackground(Color.lightGray);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        
        int longueurListe = historique.GetMessages().size();
        for(int i = longueurListe; i >0; )
        {
        	i--;
        	AjouterMessage(historique.GetMessages().get(i));
        }
        layoutPanel.setLayout(new GridLayout(0,1,0,10));
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
        layoutPanel.add(panel);
        this.getVerticalScrollBar().setValue(getVerticalScrollBar().getMaximum());
	}
}
