public class GameLogic extends SnakeGame{
    public void borderInteraction(){
        if(super.y_snake_pos == super.FIELD_HEIGHT) super.y_snake_pos = 300;
    }

}
