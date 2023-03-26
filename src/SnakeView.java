import javax.swing.*;

public class SnakeView extends JFrame {
    private SnakeGame snakeGame = new SnakeGame();
    public SnakeView(){
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(snakeGame); // add & pack DrawPanel realisation
        this.setIconImage(new ImageIcon("C:\\Users\\shado\\Pictures\\icons\\snake.png").getImage());
        this.pack();
        this.setLocationRelativeTo(null); // center the app on open
        this.setResizable(false);
        this.setVisible(true);
    }

}
