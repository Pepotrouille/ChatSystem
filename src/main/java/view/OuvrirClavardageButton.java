package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Clavardage;

public class OuvrirClavardageButton extends JButton implements ActionListener{
	
	private static final long serialVersionUID = -7571670355515305962L;
	private Clavardage clavardage;
	
	public OuvrirClavardageButton(String text, Clavardage row)
	{
		super(text);
		this.clavardage = row;
		this.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this)
			{
				MainView.AfficherClavardage(this.clavardage);
			}
	}
}
