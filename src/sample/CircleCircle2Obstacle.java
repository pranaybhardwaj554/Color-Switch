package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class CircleCircle2Obstacle extends Obstacle_Combiner{
    public CircleCircle2Obstacle(CircleObstacle obstacle1, CircleObstacle obstacle2, String commonColor) {
        super(obstacle1, obstacle2, commonColor);
    }

    @Override
    public double getSize(){
        CircleObstacle c1= (CircleObstacle)obstacle1;
        CircleObstacle c2= (CircleObstacle)obstacle2;
        return Math.max(c1.getRadius()+c1.getStrokeWidth()/2.0,c2.getRadius()+c2.getStrokeWidth()/2.0);
    }
    public void move(){
        CircleObstacle c1= (CircleObstacle)obstacle1;
        CircleObstacle c2= (CircleObstacle)obstacle2;
        Rotate rotate1 = new Rotate();
        getObstacle1().getGroup().getTransforms().add(rotate1);
        Rotate rotate2 = new Rotate();
        getObstacle2().getGroup().getTransforms().add(rotate2);
        rotate1.pivotXProperty().set(obstacle1.getPosX());
        rotate1.pivotYProperty().set(obstacle1.getPosY());
        rotate2.pivotXProperty().set(obstacle2.getPosX());
        rotate2.pivotYProperty().set(obstacle2.getPosY());
        if(getCommonColor().equals("FAE100") || getCommonColor().equals("FF0181") ){
            setTimeline1(new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(rotate1.angleProperty(), -45)),
                    new KeyFrame(Duration.seconds(c1.getRotatingSpeed()), new KeyValue(rotate1.angleProperty(), -45+360))));
            setTimeline2(new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(rotate2.angleProperty(), -45)),
                    new KeyFrame(Duration.seconds(c2.getRotatingSpeed()), new KeyValue(rotate2.angleProperty(), -45-360))));
        }
        else if(getCommonColor().equals("900DFF")||getCommonColor().equals("32DBF0")){
            setTimeline1(new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(rotate1.angleProperty(), -(90+45))),
                    new KeyFrame(Duration.seconds(c1.getRotatingSpeed()), new KeyValue(rotate1.angleProperty(), -(90+45)+360))));
            setTimeline2(new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(rotate2.angleProperty(), -(90+45))),
                    new KeyFrame(Duration.seconds(c2.getRotatingSpeed()), new KeyValue(rotate2.angleProperty(), -(90+45)-360))));
        }
        getTimeline2().setCycleCount(Timeline.INDEFINITE);
        getTimeline2().setAutoReverse(false);
        getTimeline1().setCycleCount(Timeline.INDEFINITE);
        getTimeline1().setAutoReverse(false);
        getTimeline1().play();
        getTimeline2().play();
    }
    public void set(){

    }
}

