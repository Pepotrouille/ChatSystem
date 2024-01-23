package test;

import java.awt.BorderLayout;
import java.awt.GridLayout;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
 
public class TestInternet extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8013085135109022314L;
	private final int TOTAL = 15;
     
    public static void main(String[] args) {
    	TestInternet w = new TestInternet();
        w.setVisible(true);
    }
 
    public TestInternet() {
        super();
         
        init();
    }
     
    private void init() {
        this.setTitle("Test Window"); 
        this.setResizable(true); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(buildContentPane());
        this.pack();
        this.setLocationRelativeTo(null);
    }
     
    private JPanel buildContentPane() {
    	JPanel panel = new JPanel( new BorderLayout() );
         
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(TOTAL, 1));
        for (int i=0; i<TOTAL; i++) {
            subPanel.add(buttonPanel(i));
        }
         
        JScrollPane scroller = new JScrollPane(subPanel);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroller, BorderLayout.CENTER);
         
        return panel;
    }
     
    private JPanel buttonPanel(int i) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
         
        panel.add(new JLabel("Test "+i, JLabel.CENTER));
        panel.add(new JButton("TEST "+i));
         
        return panel;
         
    }
}