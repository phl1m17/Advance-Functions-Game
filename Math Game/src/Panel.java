import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable{
    // Window Size
    final int screenWidth = 832;
    final int  screenHeight = 600;

    // Player
    private final int playerWidth = 64;
    private final int playerHeight = 128;
    private final int playerX = (screenWidth/2)-(playerWidth/2);
    private final int playerY = (screenHeight/2)+(playerHeight/2);
    private int speed = 8;

    // GameLoop Stuff
    private final int FPS = 60;
    Thread gameThread;

    // World Movement
    int worldX = 0;
    int worldY = 0;

    // Plot Points (HardCode for now cause too lazy to randomize)
    int[][] plotPoints = {
        {2, 0}, {1, 8}, {13, 14}, {29, 22}, {37, 32}, {21, 1}, {30, 8}, {45, 18}, {2, 22},
        {19, 33}, {31, 2}, {45, 9}, {7, 19}, {46, 25}, {27, 34}, {12, 3}, {24, 12}, {20, 20},
        {16, 28}, {45, 36}, {39, 3}, {6, 12}, {39, 22}, {49, 31}, {9, 37}, {17, 39}, {25, 41},
        {40, 42}, {46, 45}, {32, 45}, {22, 47}, {11, 46}, {3, 45}, {32, 15}, {8, 31}, {30, 30},
        {33, 36}, {36, 7}, {19, 6}, {3, 39}, {39, 13}, {40, 8}, {36, 14},
        {6, 1}, {23, 7}, {10, 16}, {22, 24}, {36, 38}, {17, 3}, {33, 10}, {23, 15}, {16, 23},
        {47, 40}, {28, 2}, {47, 12}, {29, 17}, {1, 29}, {30, 39}, {36, 2}, {27, 12}, {41, 17},
        {22, 30}, {38, 46}, {45, 4}, {10, 11}, {34, 20}, {43, 31}, {28, 44}, {20, 8}, {2, 15},
        {37, 25}, {14, 33}, {16, 44}, {5, 8}, {32, 24}, {40, 36}, {9, 42}, {7, 48}
    };
    boolean[] traveledPlots = new boolean[plotPoints.length * plotPoints[0].length];
    boolean movement = false;
    boolean moveAnimation = false;
    int[][] water = {
            {0, 4}, {1, 4}, {2, 4}, {3, 4}, {4, 4}, {5, 4}, {6, 4}, {7, 4}, {8, 4},
            {0, 5}, {1, 5}, {2, 5}, {3, 5}, {4, 5}, {5, 5}, {6, 5}, {7, 5}, {8, 5}, {9, 5}, {10, 5},
            {5, 6}, {6, 6}, {7, 6}, {8, 6}, {9, 6}, {10, 6}, {11, 6}, {12, 6},
            {10, 7}, {11, 7}, {12, 7}, {13, 7}, {14, 7},
            {11, 8}, {12, 8}, {13, 8}, {14, 8}, {15, 8}, {16, 8},
            {13, 9}, {14, 9}, {15, 9}, {16, 9}, {17, 9}, {18, 9},
            {15, 10}, {16, 10}, {17, 10}, {18, 10},
            {16, 11}, {17, 11}, {18, 11}, {19, 11},
            {17, 12}, {18, 12}, {19, 12},
            {17, 13}, {18, 13}, {19, 13},
            {17, 14}, {18, 14}, {19, 14},
            {16, 15}, {17, 15}, {18, 15},
            {16, 16}, {17, 16}, {18, 16},
            {15, 17}, {16, 17}, {17, 17},
            {15, 18}, {16, 18}, {17, 18},
            {14, 19}, {15, 19}, {16, 19}, {17, 19},
            {13, 20}, {14, 20}, {15, 20},
            {12, 21}, {13, 21}, {14, 21},
            {11, 22}, {12, 22}, {13, 22},
            {10, 23}, {11, 23}, {12, 23},
            {11, 24},
            {7, 26},
            {6, 27}, {7, 27},
            {5, 28}, {6, 28}, {7, 28},
            {5, 29}, {6, 29},
            {4, 30}, {5, 30},
            {3, 31}, {4, 31}, {5, 31},
            {2, 32}, {3, 32}, {4, 32},
            {2, 33}, {3, 33},
            {1, 34}, {2, 34},
            {0, 35}, {1, 35}, {2, 35},
            {0, 36}, {1, 36},
            {0, 37}
        };

    // Game Phase
    // 0 = playing
    // 1 = pokemon battle screen
    // 2 = animation
    // 3 = main screen
    int gamePhase = 3;

    KeyHandler keyH = new KeyHandler(this);
    World w = new World(this, plotPoints, water);
    BattleScreen bS = new BattleScreen(this);
    MainScreen mS = new MainScreen(this);

    int currentRandomQ = -1;

    Image player1;
    Image player2;
    Image player3;
    Image player4;
    int playerAnimation = 0;
    int playerDirection = 0;
    
    ArrayList<Image>pokeMath = new ArrayList<>();
    int type = -1;
    int pokeMathX = playerX;
    int pokeMathY = playerY;
    boolean isInventoryAnimation = false;
    int pokeMathAnimStep = 0;
    ArrayList<Image> pokeCollected = new ArrayList<>();

    public Panel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        addKeyListener(keyH);
        setFocusable(true);
        setLayout(null);
        setBackground(Color.BLACK);

        pokeMath.add(new ImageIcon(getClass().getResource("pokeMath/pokeMath0.png")).getImage());
        pokeMath.add(new ImageIcon(getClass().getResource("pokeMath/pokeMath1.png")).getImage());
        pokeMath.add(new ImageIcon(getClass().getResource("pokeMath/pokeMath2.png")).getImage());
        pokeMath.add(new ImageIcon(getClass().getResource("pokeMath/pokeMath3.png")).getImage());
        pokeMath.add(new ImageIcon(getClass().getResource("pokeMath/pokeMath4.png")).getImage());
    }
    public void changePhase(int gamePhase){
        this.gamePhase = gamePhase;
    
        if(gamePhase == 0){
            movement = true;
            currentRandomQ = -1;
            movement = true;
            bS.qCount = 0;
            removeAll();
        }
        if(gamePhase == 1){
            movement = false;
        }
    }
    public void changePhase(int gamePhase, int type, String s){
        if(type == 1) {
            player1 = new ImageIcon(getClass().getResource("player/player0.png")).getImage();
            player2 = new ImageIcon(getClass().getResource("player/player1.png")).getImage();
            player3 = new ImageIcon(getClass().getResource("player/player2.png")).getImage();
            player4 = new ImageIcon(getClass().getResource("player/player3.png")).getImage();
        }
        if(type == 2) {
            player1 = new ImageIcon(getClass().getResource("player/player4.png")).getImage();
            player2 = new ImageIcon(getClass().getResource("player/player5.png")).getImage();
            player3 = new ImageIcon(getClass().getResource("player/player6.png")).getImage();
            player4 = new ImageIcon(getClass().getResource("player/player7.png")).getImage();
        }
        this.gamePhase = gamePhase;
        changePhase(gamePhase);
    }
    public void changePhase(int gamePhase, int type){
        this.gamePhase = gamePhase;
        this.type = type;
        moveAnimation = false;
        isInventoryAnimation = true;
        pokeCollected.add(pokeMath.get(type));
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gamePhase == 0 || gamePhase == 2 || gamePhase == 3) {
            // World
            w.paint(g);

            // Player animation and movement
            if (moveAnimation) {
                playerAnimation++;
                if (playerAnimation >= 10 && playerDirection == 0) {
                    g.drawImage(player3, playerX, playerY, playerWidth, playerHeight, null);
                } else if (playerAnimation <= 10 && playerDirection == 0) {
                    g.drawImage(player4, playerX, playerY, playerWidth, playerHeight, null);
                } else if (playerAnimation >= 10 && playerDirection == 1) {
                    g.drawImage(player1, playerX, playerY, playerWidth, playerHeight, null);
                } else if (playerAnimation <= 10 && playerDirection == 1) {
                    g.drawImage(player2, playerX, playerY, playerWidth, playerHeight, null);
                }
                if (playerAnimation > 20) {
                    playerAnimation = 0;
                }
            } else {
                if (playerDirection == 1) {
                    g.drawImage(player1, playerX, playerY, playerWidth, playerHeight, null);
                }
                if (playerDirection == 0) {
                    g.drawImage(player3, playerX, playerY, playerWidth, playerHeight, null);
                }
            }

            // // Draw hitbox visualization (red box)
            // g.setColor(Color.RED);
            // int playerHitWidth = playerWidth - 20;
            // int playerHitHeight = playerHeight - 64;
            // g.drawRect(playerX + 10, playerY + 64, playerHitWidth, playerHitHeight);

            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.PLAIN, 10));
            g.drawString("Collected PokeMaths", 5, 15);
            for (int i = 0; i < pokeCollected.size(); i++) {
                g.drawImage(pokeCollected.get(i), i * 40 + 8, 20, 32, 32, null);
            }
            if(pokeCollected.contains(pokeMath.get(0)) && pokeCollected.contains(pokeMath.get(1))
                && pokeCollected.contains(pokeMath.get(2)) && pokeCollected.contains(pokeMath.get(3))
                && pokeCollected.contains(pokeMath.get(4))) {
                    Font f = new Font("Arial", Font.PLAIN, 50);
                    FontMetrics fm = g.getFontMetrics(f);
                    g.setColor(Color.BLACK);
                    g.setFont(f);
                    g.drawString("CONGRATULATIONS", (screenWidth - fm.stringWidth("CONGRATULATIONS")) / 2, 100);
            }
        }
        if (gamePhase == 1) {
            bS.paint(g, randomQ());
        }
        if (gamePhase == 2) {
            if (isInventoryAnimation) {
                int inventoryX = playerX + (playerWidth / 4);
                int inventoryY = playerY - 10;
                int size = 120 - pokeMathAnimStep;

                if (pokeMathAnimStep < 120) {
                    pokeMathAnimStep += 1.5;
                    pokeMathX = playerX + (inventoryX - playerX) * pokeMathAnimStep / 64;
                    pokeMathY = playerY + (inventoryY - playerY) * pokeMathAnimStep / 64;
                } else {
                    changePhase(0);
                    pokeMathAnimStep = 0;
                    pokeMathX = playerX;
                    pokeMathY = playerY;
                    isInventoryAnimation = false;
                }
                g.drawImage(pokeMath.get(type), pokeMathX, pokeMathY, size, size, null);
            }
        }
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString("MADE BY: PHIL MANAG", 10, 590);
        if (gamePhase == 3) {
            mS.paint(g);
            mS.addComponent();
        }
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        final double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        while(gameThread.isAlive()){
            long currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            lastTime = currentTime;
            if(delta>=1){
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update() {
        playerMovement();
        checkCollision();
    }
    public void playerMovement(){
        if(movement){
            // Normal movement
            if(keyH.upPressed){
                worldY+=speed;
                moveAnimation = true;
            }
            if(keyH.downPressed){
                worldY-=speed;
                moveAnimation = true;
            }
            if(keyH.rightPressed){
                worldX-=speed;
                playerDirection = 0;
                moveAnimation = true;
            }
            if(keyH.leftPressed){
                worldX+=speed;
                playerDirection = 1;
                moveAnimation = true;
            }
            if(!keyH.upPressed && !keyH.downPressed && !keyH.rightPressed && !keyH.leftPressed){
                moveAnimation = false;
            }
            // Diagonal movement
            speed = (keyH.upPressed && keyH.leftPressed) || (keyH.upPressed && keyH.rightPressed) || (keyH.downPressed && keyH.leftPressed) || (keyH.downPressed && keyH.rightPressed) ? (int) Math.sqrt(32) : 8;
        }
        if(keyH.changePhasePressed){
            changePhase(0);
            bS.reset();
        }
    }
    public void checkCollision() {
        int centerOffsetX = screenWidth / 2 - (w.worldSize.length * 64) / 2;
        int centerOffsetY = screenHeight / 2 - (w.worldSize[0].length * 64) / 2;
        int playerWorldX = -worldX + playerX - centerOffsetX + 10;
        int playerWorldY = -worldY + playerY - centerOffsetY + 64;
        
        int playerHitWidth = playerWidth - 20;
        int playerHitHeight = playerHeight - 64;

        // Check for plot point collisions
        for (int i = 0; i < plotPoints.length; i++) {
            int plotPointX = plotPoints[i][0] * 64;
            int plotPointY = plotPoints[i][1] * 64;
            if (traveledPlots[i]) {
                continue;
            }
            if (playerWorldX < plotPointX + 64 &&
                playerWorldX + playerHitWidth > plotPointX &&
                playerWorldY < plotPointY + 64 &&
                playerWorldY + playerHitHeight > plotPointY) {
                traveledPlots[i] = true;
                movement = false;
                changePhase(1);
                return;
            }
        }
        // Check for water block collisions
        for (int[] water1 : water) {
            int waterBlockX = water1[0] * 64;
            int waterBlockY = water1[1] * 64;
            if (playerWorldX + playerHitWidth > waterBlockX &&
                    playerWorldX < waterBlockX + 64 &&
                    playerWorldY + playerHitHeight > waterBlockY &&
                    playerWorldY < waterBlockY + 64) {
                if (keyH.upPressed) {
                    speed = 0;
                }
                if (keyH.downPressed) {
                    speed = 0;
                }
                if (keyH.leftPressed) {
                    speed = 0;
                }
                if (keyH.rightPressed) {
                    speed = 0;
                }
                return;
            }
        }
    }    
    public int randomQ(){
        if (currentRandomQ == -1) {
            currentRandomQ = (int) (Math.random() * 5 + 1);
        }
        return currentRandomQ;
    }
}