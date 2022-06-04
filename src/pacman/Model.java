package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Model extends JPanel implements ActionListener {

    private Dimension d;
    private Font smallFont = new Font("Arial", Font.BOLD, 14);
    private boolean inGame = false;
    private boolean alive = true;

    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = N_BLOCKS*BLOCK_SIZE;
    private final int MAX_GHOSTS = 12;

    private int PACMAN_SPEED = 6;
    private int N_GHOSTS = 4;
    private int lives, score;
    private int[] dx, dy;
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;
    private int[] pc_x, pc_y, pc_dx, pc_dy, pcSpeed;
    private int req_dx, req_dy;
    private final int[] validSpeeds = {1, 2, 3, 4, 6, 8};
    private final int maxSpeed = 6;
    private short[] screenData;
    private int currentSpeed = 6;
    private Timer timer;

    private Image heartImg, ghostImg;
    private Image upImg, downImg, leftImg, rightImg;

    private final int board_width = 15;
    private final int board_height = 15;
    private final short levelData[] = new short[getBoardWidth()*getBoardHeight()];

    private final int wall = 0;
    private final int point = 1;
    private final int rightBorder = 2;
    private final int leftBorder = 3;
    private final int topBorder = 4;
    private final int bottomBorder = 5;

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public int getBoardWidth() {
        return board_width;
    }

    public int getBoardHeight() {
        return board_height;
    }

    public short[] getLevelData(){
        return levelData;
    }

    private void reFillBoardWithEggs(){
        Arrays.fill(getLevelData(), (short) 1);
    }

    private void buildObstacles(){

    }

    private void initGameBoard(){
        for (int i = 0; i < getBoardWidth(); ++i) {
            for (int j = 0; j < getBoardHeight(); ++j){
                getLevelData()[i*j] = 1;
            }
        }
    }

    private void createVerticalLine(int heightStart, int heightEnd, int atWidth){
        for (int j = heightStart; j < heightEnd; ++j){
            getLevelData()[(atWidth+getBoardWidth())*j] = 0;
        }
    }

    private void createHorizontalLine(int widthStart, int widthEnd, int atHeight){
        for (int j = widthStart; j < widthEnd; ++j){
            getLevelData()[atHeight*getBoardWidth()+j] = 0;
        }
    }

    public Model(){
        loadImages();
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();
    }

    public void loadImages(){

    }

    public void initVariables(){
        screenData = new short[N_BLOCKS*N_BLOCKS];
        d = new Dimension(400, 400);
        ghost_x = new int[MAX_GHOSTS];
        ghost_dx = new int[MAX_GHOSTS];
        ghost_y = new int[MAX_GHOSTS];
        ghost_dy = new int[MAX_GHOSTS];
        ghostSpeed = new int[MAX_GHOSTS];

        dx = new int[4];
        dy = new int[4];

        timer = new Timer(40, this);
        timer.restart();
    }

    class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (inGame){
                if (key == KeyEvent.VK_LEFT){
                    req_dx = -1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_RIGHT){
                    req_dx = 1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_UP){
                    req_dx = 0;
                    req_dy = 1;
                } else if (key == KeyEvent.VK_DOWN){
                    req_dx = 0;
                    req_dy = -1;
                }
            }
        }
    }
}
