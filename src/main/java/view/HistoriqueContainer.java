package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
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

        System.out.println("Y : " + this.getVerticalScrollBar().getY());
        System.out.println("Maximum : " +this.getVerticalScrollBar().getMaximum());
        
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
		panel.setBackground(Color.lightGray);

		JPanel sousPanel = new JPanel();
		
		if(message.EstDeLUtilisateurActuel())	//Message provenant de l'utilisateur actuel (MOI)
		{
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));   
			sousPanel.setBackground(new Color(200, 230, 255));
		}
		else 									//Message provenant de l'interlocuteur (LUI)
		{
			panel.setLayout(new FlowLayout(FlowLayout.RIGHT));   
			sousPanel.setBackground(Color.WHITE);
		}
		

		
		JLabel label = new JLabel(message.GetContenu());
		label.setFont(new Font("Arial", Font.PLAIN, 20));
        sousPanel.add(label);
        panel.add(sousPanel);

        panel.setAlignmentX(CENTER_ALIGNMENT);
        layoutPanel.add(panel);
        
	}
	
	
	
	
	
}

