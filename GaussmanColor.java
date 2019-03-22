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
