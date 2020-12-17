package sample;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Obstacle implements Collidable, Serializable {
    private double posX;
    private double posY;
    private transient Group group;
    private transient Timeline timeline;
    private transient Shape shape[];
    transient AudioClip collided = new AudioClip(new File("src/sample/die.wav").toURI().toString());
    private int count = 0;
    Obstacle(double posX,double posY){
        this.posX=posX;
        this.posY=posY;
    }
    public boolean isCollided(){
        //code elided//
        return false;
    }
    public double getXCoordinate(){
        return this.posX;
    }

    public double getYCoordinate(){
        return this.posY;
    }

    public void spawn(int posX,int posY){
        //code elided
    }
    public abstract double getOrientation();

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public double getPosX() {
        return posX;
    }

    public Shape[] getShape() {
        return shape;
    }

    public ArrayList<Shape> giveShape(Paint color){
        ArrayList<Shape> give = new ArrayList<>();
        int count=0;
        for(int i=0;i<this.getShape().length;i++){
            try{
            if(!(this.getShape()[i].getStroke().toString().equals(color.toString()))){
                give.add(this.getShape()[i]);
                count++;
            }
            }
            catch (NullPointerException e){
                System.out.println(this.toString());
            }
        }
        return give;
    }
    @Override
    public boolean actionsPerformed(Ball ball,Group g){
        if(count==0){
            System.out.println("Collided");
            collided.play();
            count=count+1;
            g.getChildren().add(ball.gameover_animation());
            ball.game_over(ball.getGroup().getBoundsInParent().getCenterX(),ball.getGroup().getBoundsInParent().getCenterY());
            this.getTimeline().stop();
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public void checkColor(Ball ball){

    }

    @Override
    public void pause(){
        getTimeline().pause();
    }

    @Override
    public void resume(){
        getTimeline().play();
    }

    public void setShape(Shape[] shape) {
        this.shape = shape;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public AudioClip getCollided() {
        return collided;
    }

    public void setCollided(AudioClip collided) {
        this.collided = collided;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public abstract Group draw();
}

