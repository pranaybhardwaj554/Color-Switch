package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class SquareObstacle extends Obstacle {
    private double rotatingSpeed;
    private boolean isClockwise;
    private double side;
    private double orientation;

    public SquareObstacle(double posX, double posY, double rotatingSpeed, boolean isClockwise, double side) {
        super(posX, posY);
        this.rotatingSpeed = rotatingSpeed;
        this.isClockwise = isClockwise;
        this.side = side;
        this.orientation = 0;
    }

    public void rotate(){
        Rotate rotation1 = new Rotate();
        rotation1.pivotXProperty().set(this.getPosX());
        rotation1.pivotYProperty().set(this.getPosY());
        this.getGroup().getTransforms().add(rotation1);
        int angle=10*3600;
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotation1.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(30*10), new KeyValue(rotation1.angleProperty(), angle)));
        timeline.play();

    }

    public double getRotatingSpeed() {
        return rotatingSpeed;
    }

    public void setRotatingSpeed(double rotatingSpeed) {
        this.rotatingSpeed = rotatingSpeed;
    }

    public boolean isClockwise() {
        return isClockwise;
    }

    public void setClockwise(boolean clockwise) {
        isClockwise = clockwise;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }


    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }
    @Override
    public double getOrientation(){
        return orientation;
    }
    public Group draw(){
        double centerX = this.getPosX();
        double centerY = this.getPosY();
        double size = this.getSide();
        Line l1= new Line(centerX-size/2.0,centerY-size/2.0,centerX-size/2.0,centerY+size/2.0);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        l1.setStroke(Color.web("FAE100"));
        l1.setStrokeWidth(20);
        Line l2= new Line(centerX-size/2.0,centerY-size/2.0,centerX+size/2.0,centerY-size/2.0);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        l2.setStroke(Color.web("900DFF"));
        l2.setStrokeWidth(20);
        Line l3= new Line(centerX-size/2.0,centerY+size/2.0,centerX+size/2.0,centerY+size/2.0);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        l3.setStroke(Color.web("FF0181"));
        l3.setStrokeWidth(20);
        Line l4= new Line(centerX+size/2.0,centerY-size/2.0,centerX+size/2.0,centerY+size/2.0);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);
        l4.setStroke(Color.web("32DBF0"));
        l4.setStrokeWidth(20);
        Line l5= new Line(centerX-size/2.0,centerY+size/2.0,centerX-size/2.0,centerY+size/4.0);
        l5.setStrokeLineCap(StrokeLineCap.ROUND);
        l5.setStroke(Color.web("FAE100"));
        l5.setStrokeWidth(20);
        Shape[] shape = {l1,l2,l3,l4,l5};
        this.setShape(shape);
        this.setGroup(new Group(l1,l2,l4,l3,l5));
        this.rotate();
        return this.getGroup();
    }
}
