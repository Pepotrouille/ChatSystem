package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.ClavardageController;
import model.Clavardage;

public class CreerClavardageButton extends JButton implements ActionListener{
	
	private static final long serialVersionUID = 7977849812532563837L;
	
	private String ipDestination;
	
	public CreerClavardageButton(String text, String ipDestination)
	{
		super(text);
		this.ipDestination = ipDestination;
		this.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this)
			{
				Clavardage newClavardage = ClavardageController.GetInstance().NouveauClavardage(this.ipDestination);
				MainView.AfficherClavardage(newClavardage);
			}
	}
}
