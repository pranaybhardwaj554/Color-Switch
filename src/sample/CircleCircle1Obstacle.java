package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class CircleCircle1Obstacle extends Obstacle_Combiner{

    public CircleCircle1Obstacle(CircleObstacle obstacle1, CircleObstacle obstacle2, String commonColor) {
        super(obstacle1, obstacle2, commonColor);
    }

    public void set(){

    }

    public void move(){
        Rotate rotate1 = new Rotate();
        getObstacle1().getGroup().getTransforms().add(rotate1);
        Rotate rotate2 = new Rotate();
        getObstacle2().getGroup().getTransforms().add(rotate2);
        rotate1.pivotXProperty().set(obstacle1.getPosX());
        System.out.println(obstacle1.getPosX());
        System.out.println(obstacle2.getPosX());
        rotate1.pivotYProperty().set(obstacle1.getPosY());
        rotate2.pivotXProperty().set(obstacle2.getPosX());
        rotate2.pivotYProperty().set(obstacle2.getPosY());
        if(getCommonColor().equals("FAE100") || getCommonColor().equals("FF0181")){
        setTimeline1(new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(rotate1.angleProperty(), 180+45)),
                new KeyFrame(Duration.seconds(4), new KeyValue(rotate1.angleProperty(), 180+45+360))));
        setTimeline2(new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(rotate2.angleProperty(), 45)),
                new KeyFrame(Duration.seconds(4), new KeyValue(rotate2.angleProperty(), 45-360))));
        }
        else if(getCommonColor().equals("900DFF")||getCommonColor().equals("32DBF0")){
            setTimeline1(new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(rotate1.angleProperty(), 90+45)),
                    new KeyFrame(Duration.seconds(4), new KeyValue(rotate1.angleProperty(), 90+45+360))));
            setTimeline2(new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(rotate2.angleProperty(), -45)),
                    new KeyFrame(Duration.seconds(4), new KeyValue(rotate2.angleProperty(), -360-45))));
        }
        getTimeline2().setCycleCount(Timeline.INDEFINITE);
        getTimeline2().setAutoReverse(false);
        getTimeline1().setCycleCount(Timeline.INDEFINITE);
        getTimeline1().setAutoReverse(false);
        getTimeline1().play();
        getTimeline2().play();
    }
}
