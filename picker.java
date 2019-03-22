/*
When choosing a Gaussian number, the generator utilizes a statistical bell curve; numbers closer to 0 are more common, 
and numbers away from 0 are less common. Therefore, some colors will be more common than others.
*/

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
    private Timer gaussTimer = new Timer();
    
    Color purple = Color.decode("#800080"); //Purple appears for numbers beginning with -5 or below (Extremely rare)
    Color maroon = Color.decode("#B22222"); //Maroon appears for numbers beginning with -4 (Very rare)
    Color red = Color.decode("#FF0000"); //Red appears for numbers beginning with -3 (Rare)
    Color orange = Color.decode("#FF8C00"); //Orange appears for numbers beginning with -2
    Color gold = Color.decode("#FFD700"); //Gold appears for numbers beginning with -1
    Color yellow = Color.decode("#FFFF00"); //Yellow appears for numbers beginning with -0
    Color beige = Color.decode("#F5F5DC"); //Beige appears if the number is exactly 0 (Virtually guaranteed to never happen)
    Color lime = Color.decode("#ADFF2F"); //Lime appears for numbers beginning with 0
    Color cyan = Color.decode("#66CDAA"); //Cyan appears for numbers beginning with 1
    Color blue = Color.decode("#1E90FF"); //Blue appears for numbers beginning with 2
    Color indigo = Color.decode("#6A5ACD"); //Indigo appears for numbers beginning with 3 (Rare)
    Color violet = Color.decode("#EE82EE"); //Violet appears for numbers beginning with 4 (Very rare)
    Color pink = Color.decode("#FF69B4"); //Pink appears for numbers beginning with 5 or above (Extremely rare)
    
    public picker()
    {
        super("Gaussian Color Picker"); //The title of the window
        JPanel p = new JPanel();
        getContentPane().add(p); //Adds the panel to the window to make the contents visible
        
        newColor = new JButton("New Gaussian"); //This button allows the user to get a new color immediately
        
        p.setLayout(null); //This allows the location of each element to be set manually
        newColor.setBounds(70, 50, 250, 100); //X coordinate, Y coordinate, width, and height
        p.add(newColor);
        
        displayGauss.setFont(new Font("Serif", Font.BOLD, 24)); //Shows the number that has been selected
        displayGauss.setBackground(beige);
        displayGauss.setOpaque(true);
        displayGauss.setBounds(70, 10, 250, 40);
        p.add(displayGauss);
        
        //Adding <html> allows the text to wrap based on the size of the window
        closeToZero.setText("<html>" + "Note: A yellow or lime number which has its first digit 1 through 9 are within one one thousandth of zero." + "</html>");
        closeToZero.setBounds(330, 50, 200, 80);
        p.add(closeToZero);
        
        HandlerClass handler = new HandlerClass();
        newColor.addActionListener(handler);
        
        gaussTimer.schedule(new gaussClass(), 0, 1 * 5000); //A new number will be selected every 5000 milliseconds (5 seconds)
    }
    
    //This class implements the newColor button. When clicked, the timer resets. Note that an iteration of gaussClass is immediately run (i.e. a number is selected)
    private class HandlerClass implements ActionListener
    {
        public void actionPerformed(ActionEvent event) //Normally a block such as (if event.getSource() == <button>) would be required, but only one button exists here.
        {
            gaussTimer.cancel();
            gaussTimer = new Timer();
            gaussTimer.schedule(new gaussClass(), 0, 1 * 5000);
        }
    }
    
    //Gets the next number and changes the background to one of the colors declared above
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
        else if (selection == 0.0) //Virtually guaranteed to never happen
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
        
        //System displays numbers which are at least 1.5 units from 0, but continues selecting numbers
        if ((selection > -3 && selection <= -1.5) || (selection >= 1.5 && selection < 3 )) 
        {
            System.out.println("Uncommon number found: " + selection);
        }
        
        if (Math.abs(selection) < 0.001) //Selection stops if the number selected is within one thousandth of 0
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
        
        if (selection <= -3 || selection >= 3) //Selection stops if the ones place of the number is 3 or higher
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
        public void run() //This block occurs at the interval specified by 'gaussTimer.schedule'
        {
           getGauss();
        }
    }
}
