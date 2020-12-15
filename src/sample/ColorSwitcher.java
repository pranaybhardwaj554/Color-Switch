package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;

import java.nio.channels.Pipe;
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;

public class ColorSwitcher implements Collidable {
    private double radius;
    private double rotatingSpeed;
    private double posX;
    private double posY;
    private transient Shape shape[];
    private transient Group group;
    private int count;
    public ColorSwitcher(double radius, double rotatingSpeed, double posX, double posY) {
        this.radius = radius;
        this.rotatingSpeed = rotatingSpeed;
        this.posX = posX;
        this.posY = posY;
        count=0;
    }

    public void spawn(int x,int y){
        //code elided
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
        switchColor(ball);
        return true;
    }

    public void checkColor(Ball ball){

    }

    public void switchColor(Ball ball){
        if(this.count==0){
            count=count+1;
            ArrayList<String> colors=new ArrayList<>();
            colors.add("FAE100");colors.add("900DFF");colors.add("32DBF0");colors.add("FF0181");
            int random =(int) (Math.random()*(4));
            while(ball.getPaint().equals(Color.web(colors.get(random)))){
                random =(int) (Math.random()*(4));
            }
            ball.changeColor(colors.get(random));
            this.getGroup().setOpacity(0);
        }
    }

    @Override
    public void pause(){

    }
    @Override
    public void resume(){
        return;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRotatingSpeed() {
        return rotatingSpeed;
    }

    public void setRotatingSpeed(double rotatingSpeed) {
        this.rotatingSpeed = rotatingSpeed;
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

    public Group draw(){
        double radius=this.getRadius();
        double centerX=this.getPosX();
        double centerY = this.getPosY();
        Arc a1 = new Arc(centerX, centerY, radius, radius, 0, 90);
        a1.setType(ArcType.ROUND);
        a1.setFill(Color.web("FAE100"));
        Arc a2 = new Arc(centerX, centerY, radius, radius, 90, 90);
        a2.setType(ArcType.ROUND);
        a2.setFill(Color.web("900DFF"));
        Arc a3 = new Arc(centerX, centerY, radius, radius, 180, 90);
        a3.setType(ArcType.ROUND);
        a3.setFill(Color.web("FF0181"));
        Arc a4 = new Arc(centerX, centerY, radius, radius, 270, 90);
        a4.setType(ArcType.ROUND);
        a4.setFill(Color.web("32DBF0"));
        Shape[] shape = {a1,a2,a3,a4};
        this.setShape(shape);
        this.setGroup(new Group (a1,a2,a3,a4));
        return this.getGroup();
    }
}
