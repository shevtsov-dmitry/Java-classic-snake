import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

//implements ActionListener, KeyListener
public class SnakeGame extends JPanel {
    private SnakeView game;
    // game elements
    private final int ELEMENT_HEIGHT = 25;
    private final int ELEMENT_WIDTH = 25;
    // Field sizes
    private final int DIMENSION_MULTIPLIER = 20;
    private final int FIELD_HEIGHT = ELEMENT_HEIGHT * DIMENSION_MULTIPLIER;
    private final int FIELD_WIDTH = ELEMENT_WIDTH * DIMENSION_MULTIPLIER;


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
        // draw snake
        gg.setPaint(Color.red);
        gg.fillRect(FIELD_WIDTH/2,FIELD_HEIGHT/2,ELEMENT_WIDTH,ELEMENT_HEIGHT);

    }


}
