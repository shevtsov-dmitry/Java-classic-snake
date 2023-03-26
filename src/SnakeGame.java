public class SnakeGame {
    private SnakeView game;
    private final int FIELD_HEIGHT = 50;
    private final int FIELD_WIDTH = 50;

    public SnakeGame(SnakeView game) {
        this.game = game;
    }

    public void initialize(){
        game.setSize(FIELD_WIDTH,FIELD_HEIGHT);

        game.setVisible(true);
    }
}
