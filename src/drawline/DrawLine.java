
package drawline;

import javax.swing.JFrame;


public class DrawLine {

    public static void main(String[] args) {
        
        JFrame f = new JFrame();
       DrawPanel d1 = new  DrawPanel();
       
        f.setContentPane(d1);
        f.setSize(500,500 );
        f.setTitle("Painter");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
    }
    
}
