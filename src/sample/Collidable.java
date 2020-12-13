package sample;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public interface Collidable {
    public Shape[] giveShape(Paint color);
    public boolean actionsPerformed(Ball ball, Group g);
    public void checkColor(Ball ball);
}
