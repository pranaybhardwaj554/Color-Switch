package sample;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

import java.io.Serializable;

public abstract class Obstacle {
    private double posX;
    private double posY;
    private Group group;
    private Shape shape[];
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

    public double getPosX() {
        return posX;
    }

    public Shape[] getShape() {
        return shape;
    }

    public Shape[] giveShape(Paint color){
        Shape give[] = new Shape[this.getShape().length-1];
        int count=0;
        for(int i=0;i<this.getShape().length;i++){
            if(!(this.getShape()[i].getStroke().toString().equals(color.toString()))){
                give[count]=this.getShape()[i];
                count++;
            }
        }
        return give;
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
}

