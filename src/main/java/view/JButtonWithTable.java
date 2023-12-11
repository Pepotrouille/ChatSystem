package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Utilisateur;

public class JButtonWithTable extends JButton {
	
	private static final long serialVersionUID = 3751780773438242651L;
	Utilisateur utilisateur;
	
	public JButtonWithTable(String text, Utilisateur utilisateur)
	{
		super(text);
		this.utilisateur = utilisateur;
		//this.addActionListener(this);
	}
	
	
	/* public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this)
			{
				MainView.ShowSpecificPost(post);
			}
	} */
}