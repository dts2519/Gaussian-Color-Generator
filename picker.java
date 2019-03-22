package gaussmancolor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class picker extends JFrame{
    
    private JButton newColor;
    private double selection = 0.0;
    private JLabel displayGauss = new JLabel("" + selection);
    private JLabel closeToZero = new JLabel();
    private Random pick = new Random();
    private Timer nelly = new Timer();
    
    Color purple = Color.decode("#800080");
    Color maroon = Color.decode("#B22222");
    Color red = Color.decode("#FF0000");
    Color orange = Color.decode("#FF8C00");
    Color gold = Color.decode("#FFD700");
    Color yellow = Color.decode("#FFFF00");
    Color beige = Color.decode("#F5F5DC");
    Color lime = Color.decode("#ADFF2F");
    Color cyan = Color.decode("#66CDAA");
    Color blue = Color.decode("#1E90FF");
    Color indigo = Color.decode("#6A5ACD");
    Color violet = Color.decode("#EE82EE");
    Color pink = Color.decode("#FF69B4");
    
    public picker()
    {
        super("Gaussian Color Picker");
        JPanel p = new JPanel();
        getContentPane().add(p);
        
        newColor = new JButton("New Gaussian");
        
        
        p.setLayout(null);
        newColor.setBounds(70, 50, 250, 100);
        p.add(newColor);
        
        displayGauss.setFont(new Font("Serif", Font.BOLD, 24));
        displayGauss.setBackground(beige);
        displayGauss.setOpaque(true);
        displayGauss.setBounds(70, 10, 250, 40);
        p.add(displayGauss);
        
        //Adding <html> allows the text to wrap based on the size of the window
        closeToZero.setText("<html>" + "Note: A yellow or lime number which has its first digit 1 through 9 are within one one thousandth of zero." + "</html>");
        closeToZero.setBounds(330, 50, 200, 80);
        p.add(closeToZero);
        
        HandlerClass handoraa = new HandlerClass();
        newColor.addActionListener(handoraa);
        
        nelly.schedule(new gaussClass(), 0, 1 * 5000);
    }
    private class HandlerClass implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            nelly.cancel();
            nelly = new Timer();
            nelly.schedule(new gaussClass(), 0, 1 * 5000);
        }
    }
    public void getGauss()
    {
        selection = pick.nextGaussian();
           
        if (selection <= -5)
        {
            displayGauss.setBackground(purple);
        }
        else if (selection > -5 && selection <= -4)
        {
            displayGauss.setBackground(maroon);  
        }
        else if (selection > -4 && selection <= -3)
        {
            displayGauss.setBackground(red);  
        }
        else if (selection > -3 && selection <= -2)
        {
            displayGauss.setBackground(orange);  
        }
        else if (selection > -2 && selection <= -1)
        {
            displayGauss.setBackground(gold);  
        }
        else if (selection > -1 && selection < 0)
        {
            displayGauss.setBackground(yellow);  
        }
        else if (selection == 0.0) //there is absolutely no way this will happen
        {
            displayGauss.setBackground(beige);  
        }
        else if (selection > 0 && selection < 1)
        {
            displayGauss.setBackground(lime);  
        }
        else if (selection >= 1 && selection < 2)
        {
            displayGauss.setBackground(cyan);  
        }
        else if (selection >= 2 && selection < 3)
        {
            displayGauss.setBackground(blue);  
        }
        else if (selection >= 3 && selection < 4)
        {
            displayGauss.setBackground(indigo);  
        }
        else if (selection >= 4 && selection < 5)
        {
            displayGauss.setBackground(violet);  
        }
        else //selection >= 5
        {
            displayGauss.setBackground(pink);  
        }
        displayGauss.setText("" + selection);
        
        if ((selection > -3 && selection <= -1.5) || (selection >= 1.5 && selection < 3 ))
        {
            System.out.println("Uncommon number found: " + selection);
        }
        
        if (Math.abs(selection) < 0.001)
        {
            System.out.println("Rare number found: " + selection); 
            System.out.println("Sleeping for one hour.");
            try 
            {
                TimeUnit.MINUTES.sleep(60);
            }catch (InterruptedException ex) {
                Logger.getLogger(picker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (selection <= -3 || selection >= 3)
        {
            System.out.println("Rare number found: " + selection); 
            System.out.println("Sleeping for one hour.");
            try 
            {
                TimeUnit.MINUTES.sleep(60);
            }catch (InterruptedException ex) {
                Logger.getLogger(picker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private class gaussClass extends TimerTask
    {
        public void run()
        {
           getGauss();
        }
    }
}
