
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MainScreen implements ActionListener{
    Panel panel;
    Image background;
    JButton[]button = new JButton[2];
    String[]option = {"Option 1", "Option 2"};
    Image[]c = {new ImageIcon(getClass().getResource("player/player0.png")).getImage(),new ImageIcon(getClass().getResource("player/player4.png")).getImage()};
    Font f;
    public MainScreen(Panel panel) {
        this.panel = panel;
        background = new ImageIcon(getClass().getResource("/bSBackground.png")).getImage();
        
        for(int i = 0; i<button.length; i++){
            int x = (i % 2) * 228 + 238;
            button[i] = new JButton(option[i]);
            button[i].setBounds(x, 500, 128,20);
            button[i].addActionListener(this);
        }
        try {
            File file = new File("Math Game/src/toxigenesis bd.otf");
            f = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(80f);
        } catch (FontFormatException | IOException e) {
            f = new Font("Arial", Font.BOLD, 60);
        }
    }
    public void paint(Graphics g){
        g.drawImage(background, 0, 0, panel.screenWidth, 600, null);
        for(int i = 0; i<c.length; i++){
            int x = (i % 2) * 228 + 238;
            g.drawImage(c[i], x, 210,128,256, null);
        }
        FontMetrics fm = g.getFontMetrics(f);
        g.setColor(Color.BLACK);
        g.setFont(f);
        String t = "PokeMath";
        g.drawString(t, (panel.screenWidth - fm.stringWidth(t)) / 2,120);
    }
    public void addComponent(){
        for(JButton b : button){
            panel.add(b);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i<button.length; i++){
            if(e.getSource() == button[i]){
                panel.changePhase(0, i+1, "");
                panel.requestFocusInWindow();
            }
        }
    }
}
