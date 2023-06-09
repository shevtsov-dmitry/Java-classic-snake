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
    protected final int ELEMENT_PERIMETER = (ELEMENT_HEIGHT + ELEMENT_WIDTH) / 2;
    protected final int ELEMENT_BORDER_SIZE = 1;

    // Field sizes
    protected final int DIMENSION_MULTIPLIER = 25;
    protected final int FIELD_HEIGHT = ELEMENT_HEIGHT * DIMENSION_MULTIPLIER;
    protected final int FIELD_WIDTH = ELEMENT_WIDTH * DIMENSION_MULTIPLIER;
    protected final int FIELD_PERIMETER = (FIELD_HEIGHT + FIELD_WIDTH) / 2;

    // snake mutable parameters
    protected int snake_height = ELEMENT_HEIGHT;
    protected int snake_width = ELEMENT_WIDTH;
    protected int x_snake_pos = FIELD_WIDTH/2 - ELEMENT_WIDTH;
    protected int y_snake_pos = FIELD_HEIGHT/2 - ELEMENT_HEIGHT;
    // apple
    protected int applePosition = randomize();
    // FIXME
    // randomizer doesn't follow the convention of 25 element size
    // so it can be even 437 instead of 425 or 450
    public int randomize(){
        int range = FIELD_PERIMETER / ELEMENT_PERIMETER;
        int randInRange = (int) Math.floor(Math.random() * range);
        // TODO
        // stopped here with a great idea to add @ELEMENT_BORDER_SIZE
        // for each 25th, which were been generated during Math.random()
        int addTo = randInRange / ELEMENT_PERIMETER;
        System.out.println(randInRange * ELEMENT_PERIMETER + ELEMENT_BORDER_SIZE);
        return randInRange * ELEMENT_PERIMETER + ELEMENT_BORDER_SIZE * addTo;
    }
    //Timer
    protected final double GAME_SPEED = 0.5; // in seconds
    Timer timer = new Timer((int) (GAME_SPEED * 1000),this);
    // game events
    @Override
    public void actionPerformed(ActionEvent e) {
        // spawn apple

        // FIXME
        // I need to fix invisible margin after coming through the border
        // and also blinking near the wall
        // border interactions
        if(y_snake_pos == FIELD_HEIGHT) y_snake_pos = 0;
        else if(x_snake_pos == FIELD_WIDTH) x_snake_pos = 0;
        else if(y_snake_pos < 0) y_snake_pos = FIELD_HEIGHT;
        else if(x_snake_pos < 0) x_snake_pos = FIELD_WIDTH;

        // auxiliary construction to not leave element size restriction
        // change direction
        if(right) {
            x_snake_pos += ELEMENT_WIDTH + ELEMENT_BORDER_SIZE;
        }
        if(left) {
            x_snake_pos -= ELEMENT_WIDTH + ELEMENT_BORDER_SIZE;
        }
        if(up) {
            y_snake_pos -= ELEMENT_HEIGHT + ELEMENT_BORDER_SIZE;
        }
        if(down) {
            y_snake_pos += ELEMENT_HEIGHT + ELEMENT_BORDER_SIZE;
        }

        // change apple position after snake picked apple up
        // TODO
        // this code wouldn't work until I fix apple randomizer
        if(x_snake_pos == applePosition || y_snake_pos == applePosition){
            applePosition = randomize();
            repaint();
            System.out.println("GOT YOU !");
        }

        repaint();
    }

    // draw items
    @Override
    public void paint(Graphics g){
        Graphics2D gg = (Graphics2D) g;
        // draw game field
        gg.setPaint(Color.darkGray);
        gg.fillRect(0,0,FIELD_WIDTH,FIELD_HEIGHT);
        // draw field element borders
        gg.setPaint(new Color(154, 154, 154, 255));
        for (int i = 0; i < FIELD_HEIGHT; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gg.drawRect(i,j,ELEMENT_WIDTH,ELEMENT_HEIGHT);
                j+=ELEMENT_WIDTH;
            }
            i+=ELEMENT_HEIGHT;
        }
        // draw snake head
        gg.setPaint(Color.red);
        gg.fillRect(x_snake_pos,y_snake_pos,snake_width ,snake_height);
        // draw apple
        gg.setPaint(new Color(99, 206, 12));
        gg.fillOval(applePosition,applePosition,ELEMENT_WIDTH,ELEMENT_HEIGHT);
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
    // FIXME
    // need to repair the possibility to turn back  
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
