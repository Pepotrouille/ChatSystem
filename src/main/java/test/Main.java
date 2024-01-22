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

        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        JButton bouton = new JButton("Réinitialiser la Base de Données Utilisateurs");
        bouton.setFont(new Font("Arial", Font.PLAIN, 15));
	    bouton.setSize(500,80);
	    //bouton.setLocation(posX, posY);
	    frame.add(bouton);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        frame.getContentPane().add(scrollPane);
        frame.setVisible(true);
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