//The purpose of this file is simply to open the window and set the parameters for the frame.

package gaussmancolor;

import javax.swing.JFrame;

public class GaussmanColor {

    public static void main(String[] args) {
        picker colors = new picker();
        colors.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        colors.setSize(600, 200);
        colors.setVisible(true);
    }
    
}
