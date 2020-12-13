package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class PlusPlusObstacle extends Obstacle_Combiner{
    public PlusPlusObstacle(Obstacle obstacle1, Obstacle obstacle2, String commonColor) {
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
        if(getCommonColor().equals("900DFF")||getCommonColor().equals("32DBF0")){
            setTimeline1(new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(rotate1.angleProperty(), -90)),
                    new KeyFrame(Duration.seconds(4), new KeyValue(rotate1.angleProperty(), -90+360))));
            setTimeline2(new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(rotate2.angleProperty(), 90)),
                    new KeyFrame(Duration.seconds(4), new KeyValue(rotate2.angleProperty(), 90-360))));
        }
        else if(getCommonColor().equals("FAE100") || getCommonColor().equals("FF0181")){
            setTimeline1(new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(rotate1.angleProperty(), -180)),
                    new KeyFrame(Duration.seconds(4), new KeyValue(rotate1.angleProperty(), -180+360))));
            setTimeline2(new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(rotate2.angleProperty(), 0)),
                    new KeyFrame(Duration.seconds(4), new KeyValue(rotate2.angleProperty(), -360))));
        }
        getTimeline2().setCycleCount(Timeline.INDEFINITE);
        getTimeline2().setAutoReverse(false);
        getTimeline1().setCycleCount(Timeline.INDEFINITE);
        getTimeline1().setAutoReverse(false);
        getTimeline1().play();
        getTimeline2().play();
    }
}
