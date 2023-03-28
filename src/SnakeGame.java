import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//implements ActionListener, KeyListener
public class SnakeGame extends JPanel implements ActionListener, KeyListener{
    // game elements
    protected final int ELEMENT_HEIGHT = 25;
    protected final int ELEMENT_WIDTH = 25;

    // Field sizes
    protected final int DIMENSION_MULTIPLIER = 20;
    protected final int FIELD_HEIGHT = ELEMENT_HEIGHT * DIMENSION_MULTIPLIER;
    protected final int FIELD_WIDTH = ELEMENT_WIDTH * DIMENSION_MULTIPLIER;

    // snake mutable parameters
    protected int snake_height = ELEMENT_HEIGHT;
    protected int snake_width = ELEMENT_WIDTH;
    protected int x_snake_pos = FIELD_WIDTH/2 - ELEMENT_WIDTH;
    protected int y_snake_pos = FIELD_HEIGHT/2 - ELEMENT_HEIGHT;
    // apple

    //Timer
    protected final double GAME_SPEED = 0.75; // in seconds
    Timer timer = new Timer((int) (GAME_SPEED * 1000),this);
    // game events
    @Override
    public void actionPerformed(ActionEvent e) {
        // spawn apple

        // border interactions
        if(y_snake_pos == FIELD_HEIGHT) y_snake_pos = 0;
        else if(x_snake_pos == FIELD_WIDTH) x_snake_pos = 0;
        else if(y_snake_pos < 0) y_snake_pos = FIELD_HEIGHT;
        else if(x_snake_pos < 0) x_snake_pos = FIELD_WIDTH;

        // change direction
        if(right) {
            x_snake_pos += ELEMENT_WIDTH;
        }
        if(left) {
            x_snake_pos -= ELEMENT_WIDTH;
        }
        if(up) {
            y_snake_pos -= ELEMENT_HEIGHT;
        }
        if(down) {
            y_snake_pos += ELEMENT_HEIGHT;
        }
        // managing the collision with the walls

        repaint();
    }

    // draw items
    @Override
    public void paint(Graphics g){
        Graphics2D gg = (Graphics2D) g;
        // draw game field
        gg.setPaint(Color.darkGray);
        gg.fillRect(0,0,FIELD_WIDTH,FIELD_HEIGHT);
        // draw snake head
        gg.setPaint(Color.red);
        gg.fillRect(x_snake_pos,y_snake_pos,snake_width ,snake_height);
        gg.setColor(Color.white);
        gg.drawRect(x_snake_pos,y_snake_pos,snake_width ,snake_height);
    }

    // setup
    SnakeGame() {
        // setting field size
        this.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }


    // key handler methods
    @Override
    public void keyTyped(KeyEvent e) {} // key char

    // check which button is currently pressed
    protected boolean right, left, up, down;
    @Override
    public void keyPressed(KeyEvent e) { // key int
        switch(e.getKeyCode()){
            case 37 -> { //left
                left = true;
                right = false; down = false; up = false;
            }
            case 38 -> { //up
                up = true;
                right = false; down = false; left = false;
            }
            case 40 -> { //down
                down = true;
                right = false; up = false; left = false;
            }
            case 39 -> { //right
                right = true;
                up = false; down = false; left = false;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) { // key when released
//        System.out.println("you typed key int: " + e.getKeyCode());
    }


}
