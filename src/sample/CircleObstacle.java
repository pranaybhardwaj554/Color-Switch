package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class CircleObstacle extends Obstacle {
    private double rotatingSpeed;
    private boolean isClockWise;
    private double radius;
    private double orientation;
    private double strokeWidth;
    public CircleObstacle(double posX, double posY, double rotatingSpeed, boolean isClockWise, double radius, double strokeWidth) {
        super(posX, posY);
        this.rotatingSpeed = rotatingSpeed;
        this.isClockWise = isClockWise;
        this.radius = radius;
        this.orientation=0;
        this.strokeWidth=strokeWidth;
    }


    public void rotate(){
        Rotate rotation1 = new Rotate();
        rotation1.pivotXProperty().set(this.getPosX());
        rotation1.pivotYProperty().set(this.getPosY());
        this.getGroup().getTransforms().add(rotation1);
        int angle=10*3600;
        if(this.getIsClockWise()==false){
            angle=angle*-1;
        }
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

    public boolean getIsClockWise() {
        return isClockWise;
    }

    public void setClockWise(boolean clockWise) {
        isClockWise = clockWise;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public boolean isClockWise() {
        return isClockWise;
    }

    @Override
    public double getOrientation() {
        return orientation;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }
    public Group draw(){
        double centerX=this.getPosX();
        double centerY=this.getPosY();
        double radiusX=this.getRadius();
        double radiusY=this.getRadius();
        double strokeWidth = this.getStrokeWidth();
        Arc a1 = new Arc(centerX, centerY, radiusX, radiusY, 0, 90);
        a1.setType(ArcType.OPEN);
        a1.setStrokeLineCap(StrokeLineCap.BUTT);
        a1.setStroke(Color.web("FAE100"));
        a1.setFill(null);
        a1.setStrokeWidth(strokeWidth);
        Arc a2 = new Arc(centerX, centerY, radiusX, radiusY, 90, 90);
        a2.setType(ArcType.OPEN);
        a2.setStrokeLineCap(StrokeLineCap.BUTT);
        a2.setStroke(Color.web("900DFF"));
        a2.setFill(null);
        a2.setStrokeWidth(strokeWidth);
        Arc a3 = new Arc(centerX, centerY, radiusX, radiusY, 180, 90);
        a3.setType(ArcType.OPEN);
        a3.setStrokeLineCap(StrokeLineCap.BUTT);
        a3.setStroke(Color.web("FF0181"));
        a3.setFill(null);
        a3.setStrokeWidth(strokeWidth);
        Arc a4 = new Arc(centerX, centerY, radiusX, radiusY, 270, 90);
        a4.setType(ArcType.OPEN);
        a4.setStrokeLineCap(StrokeLineCap.BUTT);
        a4.setStroke(Color.web("32DBF0"));
        a4.setFill(null);
        a4.setStrokeWidth(strokeWidth);
        Shape[] shape = {a1,a2,a3,a4};
        this.setShape(shape);
        this.setGroup(new Group (a1,a2,a3,a4));
        this.rotate();
        return this.getGroup();
    }
}
