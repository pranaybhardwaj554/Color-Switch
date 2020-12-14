package sample;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public interface Collidable {
    public ArrayList<Shape> giveShape(Paint color);
    public boolean actionsPerformed(Ball ball, Group g);
    public void checkColor(Ball ball);
    public void pause();
    public void resume();
}
