package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Translate;
import javax.swing.*;
import java.util.ArrayList;


public class TriangleObstacle extends Obstacle {
    private double rotatingSpeed;
    private boolean isClockwise;
    private double side;
    private double orientation;
    private Paint color;

    public TriangleObstacle(double posX, double posY, double rotatingSpeed, boolean isClockwise, double side, Paint color) {
        super(posX, posY);
        this.rotatingSpeed = rotatingSpeed;
        this.isClockwise = isClockwise;
        this.side = side;
        this.orientation = 0;
        this.color=color;
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
    @Override
    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
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
                new KeyFrame(Duration.seconds(3), new KeyValue(rotation1.angleProperty(), angle))));
        getTimeline().setCycleCount(Timeline.INDEFINITE);
        getTimeline().setAutoReverse(false);
        getTimeline().play();

    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    public Group draw(){
        //ArrayList of colors
        ArrayList<String> colors=new ArrayList<>();
        colors.add("FAE100");colors.add("900DFF");colors.add("32DBF0");colors.add("FF0181");
        double centerX = this.getPosX();
        double centerY = this.getPosY();
        double length = this.getSide();
        //Selecting Desired Color
        int toBeRemoved=0;
        for(int i=0;i<4;i++){
            if(Color.web(colors.get(i)).equals(this.color)){
                System.out.println("True");
                toBeRemoved=i;
            }
        }
        colors.remove(toBeRemoved);
        //Drawing Lines
        Line t1=new Line(centerX-length/2.0,centerY+length*Math.pow(3,-0.5)/2.0,centerX,centerY-length*Math.pow(3,-0.5));
        t1.setStrokeLineCap(StrokeLineCap.ROUND);
        t1.setStroke(Color.web(colors.get(0)));
        t1.setStrokeWidth(20);
        //Drawing Second Line
        Line t2=new Line(centerX-length/2.0,centerY+length*Math.pow(3,-0.5)/2.0,centerX+length/2.0,centerY+length*Math.pow(3,-0.5)/2.0);
        t2.setStrokeLineCap(StrokeLineCap.ROUND);
        t2.setStroke(this.getColor());
        t2.setStrokeWidth(20);
        //Drawing Third Line
        Line t3=new Line(centerX,centerY-length*Math.pow(3,-0.5),centerX+length/2.0,centerY+length*Math.pow(3,-0.5)/2.0);
        t3.setStrokeLineCap(StrokeLineCap.ROUND);
        t3.setStroke(Color.web(colors.get(1)));
        t3.setStrokeWidth(20);
        //Drawing 4th Line
        Line t4=new Line(centerX-length/2.0,centerY+length*Math.pow(3,-0.5)/2.0,centerX-length/4.0,centerY+length*Math.pow(3,-0.5)/2.0-length*Math.pow(3,0.5)/4.0);
        t4.setStrokeLineCap(StrokeLineCap.ROUND);
        t4.setStroke(Color.web(colors.get(0)));
        t4.setStrokeWidth(20);
        Shape[] shape = {t1,t2,t3,t4};
        this.setShape(shape);
        this.setGroup(new Group(t1,t3,t2,t4));
        this.rotate();
        return this.getGroup();
    }
}
