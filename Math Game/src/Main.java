
import javax.swing.JFrame;

public class Main{
    public static void main(String[] args) {
        JFrame window = new JFrame("PokeMath");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Panel p = new Panel();
        p.startGameThread();
        
        window.add(p);
        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}