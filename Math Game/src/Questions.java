
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Questions implements ActionListener{
    int answer;
    Panel panel;
    BattleScreen battleScreen;
    String word;
    Font f;
    Image background;
    ArrayList<Image> pokeMath = new ArrayList<>();
    int type = 0;
    Image cloud;
    Image[] heart;
    JButton[]buttons = new JButton[4];
    public Questions(Panel panel, BattleScreen battleScreen, String word, String[] options, int answer, int type) {
        this.panel = panel;
        this.battleScreen = battleScreen;
        this.answer = answer;
        this.word = word;
        this.type = type;
        f = new Font("Arial", Font.PLAIN, 30);

        background = new ImageIcon(getClass().getResource("/bSBackground.png")).getImage();
        cloud = new ImageIcon(getClass().getResource("/cloud.png")).getImage();
        this.pokeMath = panel.pokeMath;
        heart = new Image[battleScreen.tries];
        for(int i = 0; i < heart.length; i++){
            heart[i] = new ImageIcon(getClass().getResource("/heart0.png")).getImage();
        }

        for(int i = 0; i<buttons.length;i++){
            buttons[i] = new JButton(options[i]);
            int x = (i % 2) * 370 + 56;
            int y = (i / 2) * 100 + 400;
            buttons[i].setFont(f.deriveFont(30f));
            buttons[i].setBounds(x, y, 350, 80);
            buttons[i].setForeground(Color.black);
            buttons[i].setOpaque(true);
            buttons[i].addActionListener(this);
        }
    }
    public void paint(Graphics g) {
        // Draw background
        g.drawImage(background, 0, 0, panel.screenWidth, 600, null);

        // Draw question cloud
        g.drawImage(cloud, panel.screenWidth / 2 - 250, -15, 500, 225, null);

        // Draw question text
        g.setColor(Color.black);
        g.setFont(f);
        FontMetrics metrics = g.getFontMetrics(f);
        int wordX = (panel.screenWidth - metrics.stringWidth(word)) / 2;
        int wordY = 110;
        g.drawString(word, wordX, wordY);

        // Drawing pokeMath
        g.drawImage(pokeMath.get(type), panel.screenWidth / 2 - 100, 600 / 2 - 110, 200, 200, null);
    
        // Drawing hearts
        for (int i = 0; i < battleScreen.tries; i++) {
            g.drawImage(heart[i], i * 56 + 10, 10, 50, 50, null);
        }
    }
    public void addComponents(){
        for(JButton b : buttons){
            panel.add(b);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                if (i != answer) {
                    if (battleScreen.tries >= 0) {
                        battleScreen.tries--;
                    }
                }
                if (battleScreen.tries == 0) {
                    panel.keyH.changePhasePressed = true;
                }
                if (battleScreen.qCount == battleScreen.q.size() - 2 && battleScreen.tries > 0) {
                    panel.changePhase(2, type);
                    battleScreen.reset();
                } else {
                    panel.removeAll();
                    battleScreen.qCount++;
                    battleScreen.addComponents();
                }
            }
        }
    }
}
