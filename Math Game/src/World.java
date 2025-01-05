import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

public class World {
    Panel panel;
    int[][] worldSize = new int[51][50];
    Set<String> plotSet;
    Set<String> waterSet;
    Set<String> roadSet;
    Set<String> fakeWaterSet;
    Set<String> textureSet;
    Set<String> texture2Set;
    public World(Panel panel, int[][] coords, int[][]w) {
        this.panel = panel;
        plotSet = new HashSet<>();
        waterSet = new HashSet<>();
        roadSet = new HashSet<>();
        fakeWaterSet = new HashSet<>();
        textureSet = new HashSet<>();
        texture2Set = new HashSet<>();

        int[][] water = w;
        int[][] road = {
            {5, 25}, {6, 25}, {7, 25}, {8, 25}, {9, 25},
            {0, 26}, {1, 26}, {2, 26}, {3, 26}, {4, 26}, {5, 26},
            {9, 26}, {10, 26}, {11, 26}, {12, 26},
            {12, 27}, {13, 27}, {14, 27}, {15, 27}, {16, 27}, {17, 27},
            {18, 27}, {19, 27}, {20, 27}, {21, 27}, {22, 27}, {23, 27},
            {24, 27}, {25, 27}, {26, 27}, {27, 27}, {28, 27}, {29, 27},
            {30, 27}, {31, 27}, {32, 27}, {33, 27}, {34, 27}, {35, 27},
            {36, 27}, {37, 27}, {38, 27}, {39, 27}, {40, 27}, {41, 27},
            {42, 27}, {43, 27}, {44, 27}, {45, 27}, {46, 27}, {47, 27},
            {48, 27}, {49, 27}, {50,27},
            {25, 0}, {25, 1}, {25, 2}, {25, 3}, {25, 4}, {25, 5}, {25, 6}, {25, 7}, {25, 8},
            {25, 9}, {25, 10}, {25, 11}, {25, 12}, {25, 13}, {25, 14}, {25, 15}, {25, 16}, {25, 17},
            {25, 18}, {25, 19}, {25, 20}, {25, 21}, {25, 22}, {25, 23}, {25, 24}, {25, 25}, {25, 26},
            {25, 27}, {25, 28}, {25, 29}, {25, 30}, {25, 31}, {25, 32}, {25, 33}, {25, 34}, {25, 35},
            {24, 35}, {24, 36},
            {23, 36}, {23, 37},
            {22, 37}, {22, 38}, {22, 39}, {22, 40}, {22, 41},
            {21, 41}, {21, 42}, {21, 43}, {21, 44},
            {20, 44}, {20, 45}, {20, 46}, {20, 47}, {20, 48}, {20, 49}
        };
        int[][]fakeWater = {{9,24},{10,24},{10,25},{8,26}, {8,27}};
        int[][] texture = {
            {3, 2}, {6, 3}, {8, 8}, {9, 9}, {12, 3},
            {18, 5}, {20, 7}, {21, 12}, {23, 8}, {24, 18},
            {26, 4}, {27, 9}, {29, 6}, {30, 3}, {31, 8},
            {33, 11}, {34, 4}, {36, 2}, {37, 7}, {38, 9},
            {39, 13}, {41, 4}, {42, 8}, {43, 12}, {44, 15},
            {46, 2}, {47, 5}, {48, 11}, {49, 3}, {50, 6},
            {3, 40}, {6, 41}, {9, 43}, {11, 45}, {12, 46},
            {14, 38}, {16, 39}, {18, 42}, {19, 44}, {21, 46},
            {22, 37}, {24, 40}, {26, 42}, {28, 45}, {30, 39},
            {31, 38}, {33, 43}, {35, 46}, {36, 37}, {38, 41},
            {40, 42}, {41, 44}, {43, 39}, {45, 45}, {46, 38},
            {48, 43}, {49, 46}, {50, 40}, {2, 18}, {5, 19},
            {7, 14}, {8, 17}, {10, 13}, {13, 10}, {15, 6},
            {17, 12}, {20, 9}, {22, 6}, {25, 12}, {28, 14},
            {30, 10}, {33, 9}, {36, 6}, {39, 11}, {42, 14},
            {0, 1}, {2, 6}, {4, 15}, {7, 20}, {9, 27},
            {11, 3}, {13, 8}, {16, 2}, {18, 10}, {20, 16},
            {23, 20}, {26, 25}, {28, 30}, {31, 4}, {34, 6},
            {36, 12}, {38, 18}, {41, 21}, {43, 26}, {46, 8},
            {48, 13}, {50, 19}, {1, 23}, {3, 28}, {6, 31},
            {8, 35}, {10, 38}, {13, 41}, {15, 45}, {17, 3},
            {19, 7}, {22, 11}, {24, 15}, {27, 18}, {29, 22},
            {32, 25}, {35, 28}, {37, 33}, {39, 37}, {42, 40},
            {44, 43}, {47, 46}, {49, 8}, {2, 10}, {4, 13},
            {6, 18}, {9, 22}, {11, 26}, {14, 30}, {16, 34},
            {18, 39}, {21, 42}, {23, 46}, {25, 49}, {28, 2},
            {30, 7}, {33, 10}, {36, 15}, {38, 19}, {40, 23},
            {43, 27}, {45, 32}, {48, 36}, {50, 41}, {0, 45},
            {3, 47}, {6, 49}, {8, 5}, {12, 11}, {15, 14},
            {18, 21}, {20, 25}, {23, 29}, {26, 33}, {28, 38}
        };
        int[][] texture2 = {
            {0, 0}, {2, 3}, {3, 5}, {4, 7}, {5, 10}, {6, 12}, {7, 15}, {8, 18}, {9, 21}, {10, 24},
            {11, 27}, {12, 30}, {13, 33}, {14, 36}, {15, 39}, {16, 42}, {17, 45}, {18, 48}, {19, 3}, {20, 6},
            {21, 9}, {22, 12}, {23, 15}, {24, 19}, {26, 23}, {27, 26}, {28, 29}, {29, 32}, {30, 35}, {31, 38},
            {32, 41}, {33, 44}, {34, 47}, {35, 2}, {36, 5}, {37, 8}, {38, 11}, {39, 14}, {40, 17}, {41, 20},
            {42, 22}, {43, 25}, {44, 28}, {45, 31}, {46, 34}, {47, 37}, {48, 40}, {49, 43}, {50, 46}, {1, 48},
            {3, 43}, {5, 38}, {7, 33}, {9, 28}, {11, 23}, {13, 18}, {15, 13}, {17, 8}, {19, 5}, {21, 2},
            {23, 4}, {25, 7}, {27, 10}, {29, 13}, {31, 16}, {33, 19}, {35, 22}, {37, 25}, {39, 28}, {41, 31},
            {43, 34}, {45, 37}, {47, 40}, {49, 44}, {2, 47}, {4, 42}, {6, 37}, {8, 32}, {10, 27}, {12, 22},
            {14, 17}, {16, 12}, {18, 7}, {20, 3}
        };
        
        for (int[] coord : water) {
            waterSet.add(coord[0] + "," + coord[1]);
        }
        for (int[] coord : road) {
            roadSet.add(coord[0] + "," + coord[1]);
        }
        for (int[] coord : coords) {
            plotSet.add(coord[0] + "," + coord[1]);
        }
        for(int[] coord : fakeWater) {
            fakeWaterSet.add(coord[0]+","+coord[1]);
        }
        for(int[] coord : texture) {
            textureSet.add(coord[0]+","+coord[1]);
        }
        for(int[] coord : texture2){
            texture2Set.add(coord[0]+","+coord[1]);
        }
    }

    public void paint(Graphics g) {
        int tileSize = 64;
        int halfWidth = panel.screenWidth / 2;
        int halfHeight = panel.screenHeight / 2;
        int offsetX = halfWidth - (worldSize.length * tileSize) / 2 + panel.worldX;
        int offsetY = halfHeight - (worldSize[0].length * tileSize) / 2 + panel.worldY;

        for (int i = 0; i < worldSize.length; i++) {
            for (int j = 0; j < worldSize[i].length; j++) {
                String coord = i + "," + j;

                if (plotSet.contains(coord)) {
                    g.setColor(new Color(102, 255, 51));
                } else if (waterSet.contains(coord)) {
                    g.setColor(Color.BLUE); 
                } else if (roadSet.contains(coord)) {
                    g.setColor(Color.LIGHT_GRAY);
                } else if (fakeWaterSet.contains(coord)) {
                    g.setColor(Color.BLUE);
                } else if (textureSet.contains(coord)) {
                    g.setColor(new Color(0, 204, 0));
                } else if (texture2Set.contains(coord)) {
                    g.setColor(new Color(0, 153, 51));
                } else {
                    g.setColor(new Color(102, 255, 51)); 
                }
                g.fillRect(i * tileSize + offsetX, j * tileSize + offsetY, tileSize, tileSize);
                g.setColor(Color.BLACK);
                g.drawRect(i * tileSize + offsetX, j * tileSize + offsetY, tileSize, tileSize);
            }
        }
    }
}