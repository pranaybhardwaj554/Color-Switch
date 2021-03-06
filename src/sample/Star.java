package sample;

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Star implements Collidable, Serializable {
    private String color;
    private double posX;
    private double posY;
    private transient Shape shape[];
    private transient Group group;
    private int count;
    private transient FadeTransition Fade;
    transient AudioClip starcollide = new AudioClip(new File("src/sample/star.wav").toURI().toString());

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

    public void checkColor(Ball ball){

    }

    @Override
    public double getSize(){
        return 60*Math.pow(3,-2);
    }

    @Override
    public ArrayList<Shape> giveShape(Paint color){
        ArrayList<Shape> give = new ArrayList<>();
        for(int i=0;i<getShape().length;i++){
            give.add(getShape()[i]);
        }
        return give;
    }

    @Override
    public boolean actionsPerformed(Ball ball, Group g){
        getFade().stop();
        switchColor(ball);
        return true;
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
            this.getGroup().setOpacity(0);
            ball.setStarsCollected(ball.getStarsCollected()+1);
            starcollide.play();
            count++;
        }
    }

    @Override
    public void pause(){
        getFade().pause();
    }

    @Override
    public void resume(){
        if(this.count==0)
            getFade().play();
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public FadeTransition getFade() {
        return Fade;
    }

    public void setFade(FadeTransition fade) {
        Fade = fade;
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
        setFade( new FadeTransition());
        getFade().setDuration(Duration.millis(500));
        getFade().setFromValue(10);
        getFade().setToValue(0.7);
        getFade().setAutoReverse(true);
        getFade().setCycleCount(1000);
        this.setGroup(new Group(star,star1));
        Shape shape[] ={star,star1};
        this.setShape(shape);
        getFade().setNode(this.getGroup());
        getFade().play();
        return this.getGroup();
    }

}





