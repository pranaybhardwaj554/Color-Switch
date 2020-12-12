package sample;

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class Star {
    private String color;
    private double posX;
    private double posY;
    private Shape shape[];
    private Group group;
    private int count;


    public Star(String color, double posX, double posY) {
        this.color = color;
        this.posX = posX;
        this.posY = posY;
    }

    public void spawn(int x,int y){
        //code elided
    }

    public Shape[] getShape() {
        return shape;
    }

    public void setShape(Shape[] shape) {
        this.shape = shape;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void switchColor(Ball ball){
        if(this.count==0){
            ball.setStarsCollected(ball.getStarsCollected()+1);
            this.getGroup().setOpacity(0);
            count++;
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public Group draw(){
        double centerX=this.getPosX();
        double centerY=this.getPosY();
        double side=30;
        Polygon star= new Polygon(
                centerX-side/2.0,centerY+side/2.0*Math.pow(3,-0.5),centerX,centerY-side*Math.pow(3,-0.5),
                centerX,centerY-side*Math.pow(3,-0.5),centerX+side/2.0,centerY+side/2.0*Math.pow(3,-0.5),
                centerX+side/2.0,centerY+side/2.0*Math.pow(3,-0.5),centerX-side/2.0,centerY+side/2.0*Math.pow(3,-0.5)
        );
        star.setStrokeLineJoin(StrokeLineJoin.ROUND);
        star.setStroke(Color.web("#FFFFFF"));
        star.setStrokeWidth(2);
        Polygon star1= new Polygon(
                centerX-side/2.0,centerY+side/2.0*Math.pow(3,-0.5),centerX,centerY-side*Math.pow(3,-0.5),
                centerX,centerY-side*Math.pow(3,-0.5),centerX+side/2.0,centerY+side/2.0*Math.pow(3,-0.5),
                centerX+side/2.0,centerY+side/2.0*Math.pow(3,-0.5),centerX-side/2.0,centerY+side/2.0*Math.pow(3,-0.5)
        );
        star1.setStrokeLineJoin(StrokeLineJoin.ROUND);
        star1.setStroke(Color.web("#FFFFFF"));
        star1.setStrokeWidth(2);
        Rotate rotation = new Rotate();
        rotation.pivotXProperty().set(centerX);
        rotation.pivotYProperty().set(centerY);
        rotation.setAngle(180);
        star1.getTransforms().add(rotation);
        star.setFill(Color.web("#FFFFFF"));
        star1.setFill(Color.web("#FFFFFF"));
        FadeTransition Fade = new FadeTransition();
        Fade.setDuration(Duration.millis(500));
        Fade.setFromValue(10);
        Fade.setToValue(0.7);
        Fade.setAutoReverse(true);
        Fade.setCycleCount(1000);
        this.setGroup(new Group(star,star1));
        Shape shape[] ={star,star1};
        this.setShape(shape);
        Fade.setNode(this.getGroup());
        Fade.play();
        return this.getGroup();
    }

}





