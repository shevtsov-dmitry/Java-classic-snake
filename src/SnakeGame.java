import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//implements ActionListener, KeyListener
public class SnakeGame extends JPanel implements ActionListener, KeyListener{
    private SnakeView game;
    // game elements
    private int ELEMENT_HEIGHT = 25;
    private int ELEMENT_WIDTH = 25;
    // Field sizes
    private final int DIMENSION_MULTIPLIER = 20;
    private final int FIELD_HEIGHT = ELEMENT_HEIGHT * DIMENSION_MULTIPLIER;
    private final int FIELD_WIDTH = ELEMENT_WIDTH * DIMENSION_MULTIPLIER;

    //Timer
    private final double GAME_SPEED = 0.3; // in seconds
    Timer timer = new Timer((int) (GAME_SPEED * 1000),this);

    SnakeGame() {
        // setting field size
        this.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        this.setFocusable(true);
        //this.addKeyListener(this);
        //timer.start();
    }
    @Override
    public void paint(Graphics g){
        Graphics2D gg = (Graphics2D) g;
        // draw game field
        gg.setPaint(Color.darkGray);
        gg.fillRect(0,0,FIELD_WIDTH,FIELD_HEIGHT);
        // draw snake head
        gg.setPaint(Color.red);
        gg.fillRect(FIELD_WIDTH/2 - ELEMENT_WIDTH,FIELD_HEIGHT/2 - ELEMENT_HEIGHT,ELEMENT_WIDTH ,ELEMENT_HEIGHT);
        //
    }

    // key handler methods

    @Override
    public void keyTyped(KeyEvent e) { // key char
    }
    boolean right, left, up, down;
    @Override
    public void keyPressed(KeyEvent e) { // key int
        switch(e.getKeyCode()){
            case 37 -> {
                left = true;
                right = false; down = false; up = false;
            }//left
            case 38 -> {
                up = true;
                right = false; down = false; left = false;
            }//up
            case 40 -> {
                down = true;
                right = false; up = false; left = false;
            }//down
            case 39 -> {
                right = true;
                up = false; down = false; left = false;
            }//right
        }
    }
    @Override
    public void keyReleased(KeyEvent e) { // key when released
//        System.out.println("you typed key int: " + e.getKeyCode());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // change direction
        if(right) ELEMENT_WIDTH += ELEMENT_WIDTH;
        if(left) ELEMENT_WIDTH -= ELEMENT_WIDTH;
        if(up) ELEMENT_HEIGHT -= ELEMENT_HEIGHT;
        if(down) ELEMENT_HEIGHT += ELEMENT_HEIGHT;
        // managing the collision with the walls
        repaint();
    }

}
