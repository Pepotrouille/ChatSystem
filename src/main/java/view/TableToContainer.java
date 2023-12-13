package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import model.Clavardage;

public class TableToContainer {
	
	public int lengthCell = 190;
	public int heightCell = 50;
	
	public static enum TypeButton {NONE, CREER, OUVRIR};
	
	public Container mainContainer;
	
	
	private TableToContainer tableToContainer;
	
	private TableToContainer()
	{	}
	
	public TableToContainer GetInstance()
	{
		if(tableToContainer == null)
		{
			tableToContainer = new TableToContainer();
		}
		return tableToContainer;
	}
	
	
	public Container DataTableToContainer(String[] nomColonnes, Object[][] contenu, TypeButton typeButton)
	{
		mainContainer = new Container();
		
		GetContainerTitleRow(nomColonnes);
		
		Object[][] rows = contenu;
		for(int i =0; i < rows.length; i++)
		{
			GetContainerRow(rows[i], heightCell*(i+1), typeButton);
		}
		
		return mainContainer;
	}
	
	private void GetContainerRow(Object[] row, int positionY, TypeButton typeButton)
	{
		int tailleTab = row.length-1;

		for(int i = 0; i<tailleTab; i++)
		{
	        JLabel label = MakeFormattedJLabel((String)row[i],lengthCell, heightCell, i*lengthCell, positionY);
			label.setBackground(Color.getHSBColor(0, 0, 0.90f - 0.05f*(i%2)));
	        label.setOpaque(true);
	        mainContainer.add(label);
		}
		
		switch(typeButton)
		{
		case CREER:
			FormatButton(new CreerClavardageButton(" ", (String)row[tailleTab-1]), lengthCell, (int)(heightCell*0.6), lengthCell*(tailleTab + 1), (int)(positionY+heightCell*0.1));
			break;
		case OUVRIR:
			FormatButton(new OuvrirClavardageButton(" ", (Clavardage)row[tailleTab-1]), lengthCell, (int)(heightCell*0.6), lengthCell*(tailleTab + 1), (int)(positionY+heightCell*0.1));
			break;
			
		}
		
		
        
       
        	
	}
	
	private void GetContainerTitleRow(String[] titleRow)
	{
		int tailleTab = titleRow.length;
		
		for(int i = 0; i<tailleTab; i++)
		{
			JLabel label = MakeFormattedJLabel(titleRow[i],lengthCell, heightCell, i*lengthCell, 0);
			label.setBackground(Color.getHSBColor(0, 0, 0.80f - 0.05f*(i%2)));
	        label.setOpaque(true);
	        mainContainer.add(label);
		}
        	
	}
	
	private JLabel MakeFormattedJLabel(String text, int length, int height, int posX, int posY)
	{
		JLabel newJLabel = new JLabel(text);
		newJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		newJLabel.setSize(length, height);
		newJLabel.setLocation(posX, posY);
		newJLabel.setVerticalAlignment(JLabel.CENTER);
		newJLabel.setHorizontalAlignment(JLabel.CENTER);
		
		return newJLabel;
	}
	
	private void FormatButton(JButton button, int length, int height, int posX, int posY)
	{

        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setSize(length, height);
        button.setLocation(posX, posY);
        mainContainer.add(button);
		
	}
}
