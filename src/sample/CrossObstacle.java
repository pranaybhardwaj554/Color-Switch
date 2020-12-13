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

public class CrossObstacle extends Obstacle {
    private double rotatingSpeed;
    private boolean isClockwise;
    private double oneSideLength;
    private double orientation;
    private double stroke;
    public CrossObstacle(double posX, double posY, double rotatingSpeed, boolean isClockwise, double oneSideLength,double stroke) {
        super(posX, posY);
        this.rotatingSpeed = rotatingSpeed;
        this.isClockwise = isClockwise;
        this.oneSideLength = oneSideLength;
        this.orientation = 0;
        this.stroke=stroke;
    }

    public double getStroke() {
        return stroke;
    }

    public void setStroke(double stroke) {
        this.stroke = stroke;
    }

    public void rotate(){
        Rotate rotation1 = new Rotate();
        rotation1.pivotXProperty().set(this.getPosX());
        rotation1.pivotYProperty().set(this.getPosY());
        this.getGroup().getTransforms().add(rotation1);
        int angle=360;
        if(this.isClockwise()==false){
            angle=angle*-1;
        }
        setTimeline(new Timeline(
                new KeyFrame(Duration.seconds(4), new KeyValue(rotation1.angleProperty(), angle))));
        getTimeline().setCycleCount(Timeline.INDEFINITE);
        getTimeline().setAutoReverse(false);
        getTimeline().play();
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

    public double getOneSideLength() {
        return oneSideLength;
    }

    public void setOneSideLength(double oneSideLength) {
        this.oneSideLength = oneSideLength;
    }

    @Override
    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }
    public Group draw(){
        String yellow="FAE100";String purple="900DFF";String blue ="32DBF0";String pink="FF0181";
        double length=this.getOneSideLength();
        double centerX=this.getPosX();
        double centerY = this.getPosY();
        double stroke = this.getStroke();
        Line line1=new Line(centerX,centerY,centerX+length,centerY);
        line1.setStrokeLineCap(StrokeLineCap.ROUND);
        line1.setStroke(Color.web(yellow));
        line1.setStrokeWidth(stroke);
        Line line2=new Line(centerX,centerY,centerX-length,centerY);
        line2.setStrokeLineCap(StrokeLineCap.ROUND);
        line2.setStroke(Color.web(pink));
        line2.setStrokeWidth(stroke);
        Line line3=new Line(centerX,centerY,centerX,centerY+length);
        line3.setStrokeLineCap(StrokeLineCap.ROUND);
        line3.setStroke(Color.web(blue));
        line3.setStrokeWidth(stroke);
        Line line4=new Line(centerX,centerY,centerX,centerY-length);
        line4.setStrokeLineCap(StrokeLineCap.ROUND);
        line4.setStroke(Color.web(purple));
        line4.setStrokeWidth(stroke);
        Line point=new Line(centerX,centerY,centerX,centerY);
        point.setStrokeLineCap(StrokeLineCap.SQUARE);
        point.setStroke(Color.web("272727"));
        point.setStrokeWidth(stroke);
        Shape[] shape = {line1,line2,line3,line4,point};
        this.setShape(shape);
        this.setGroup(new Group(line1,line2,line3,line4,point));
        this.rotate();
        return this.getGroup();
    }
}
