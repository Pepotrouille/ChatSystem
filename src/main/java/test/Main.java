package test;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;
/*
public class Main {
    public static void main(String[] args) {
        JLabel label = new JLabel();
        JFrame frame = new JFrame("Scroll Bars");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JScrollBar horizontal = new JScrollBar(JScrollBar.HORIZONTAL, 0, 100, 0, 500);
        horizontal.addAdjustmentListener(new MyAdjustmentListener(label, frame));
        frame.getContentPane().add(horizontal, BorderLayout.SOUTH);
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
*/

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Scroll Bars");
        JTextArea textArea = new JTextArea(5, 5);


		JPanel panel = new JPanel();
        JScrollPane scrollPane = new PaneTemp(panel);
        

        //JButton bouton = new JButton("Réinitialiser la Base de Données Utilisateurs");
        //bouton.setFont(new Font("Arial", Font.PLAIN, 15));
	    //bouton.setSize(500,80);
	    //bouton.setLocation(posX, posY);
        frame.getContentPane().add(scrollPane);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        frame.setVisible(true);
    }
}


class PaneTemp extends JScrollPane{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2272624422378978304L;

	public PaneTemp(JPanel panel) {

		super(panel);
		panel.setBackground(Color.lightGray);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        JPanel sp1 = new JPanel();
        sp1.setBackground(Color.BLUE);
        sp1.setSize(20,20);
        JPanel sp2 = new JPanel();
        sp2.setBackground(Color.GREEN);
        sp2.setSize(20,20);
        JPanel sp3 = new JPanel();
        sp3.setBackground(Color.MAGENTA);
        sp3.setSize(20,20);
        
        
        JLabel label1 = new JLabel("Message1");
        label1.setSize(25,25);
		label1.setFont(new Font("Arial", Font.PLAIN, 25));
        label1.setForeground(Color.BLUE);
        //label1.setBackground(Color.GREEN);
        panel.add(label1);
        JLabel label2 = new JLabel("Message2");
        panel.add(label2);
        JLabel label3 = new JLabel("Message3");
        label3.setBackground(Color.BLUE);
        panel.add(label3);
        
        
        panel.add(sp1);
        panel.add(sp2);
        panel.add(sp3);
        
        
        
        panel.setLayout(new GridLayout(0,1,0,10));
	}
	
	
}





class MyAdjustmentListener implements AdjustmentListener {
    private final JLabel label;
    private final JFrame frame;

    public MyAdjustmentListener(JLabel label, JFrame frame) {
        this.label = label;
        this.frame = frame;
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        label.setText("Slider's position is " + e.getValue());
        frame.repaint();
    }
}