package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.net.PortUnreachableException;
import java.security.PublicKey;
import java.sql.Time;
import java.util.ArrayList;

public class Ball {
    boolean only_once=true;
    private Shape shape;
    private String color;
    private double posX;
    private double posY;
    private double radius;
    private double distanceCovered;
    private int starsCollected;
    private double velocity;
    private Group group;
    private Group group2;
    private Paint paint;
    ArrayList<Group> game_over;
    PathTransition pathTransition;


    public Ball(String color, double radius, double distanceCovered, int starsCollected, double velocity) {
        this.color = color;
        this.radius = radius;
        this.distanceCovered = distanceCovered;
        this.starsCollected = starsCollected;
        this.velocity = velocity;
    }
    public double getCurrentXCoordinate(){
        //code elided;
        return 0;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public double getCurrentYCoordinate(){
        //code elided;
        return 0;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getDistanceCovered() {
        return distanceCovered;
    }

    public void setDistanceCovered(double distanceCovered) {
        this.distanceCovered = distanceCovered;
    }

    public int getStarsCollected() {
        return starsCollected;
    }

    public void setStarsCollected(int starsCollected) {
        this.starsCollected = starsCollected;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getPosX() {
        return posX;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
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

    public void changeColor(String color){
        this.getShape().setFill(Color.web(color));
        this.setColor(color);
        this.setPaint(Color.web(color));
    }

    public ArrayList<Group> getGame_over() {
        return game_over;
    }

    public void setGame_over(ArrayList<Group> game_over) {
        this.game_over = game_over;
    }

    public PathTransition getPathTransition() {
        return pathTransition;
    }

    public void setPathTransition(PathTransition pathTransition) {
        this.pathTransition = pathTransition;
    }

    public Group draw(double centerX, double centerY) throws InterruptedException {
        double radius=this.getRadius();
        this.setPosX(centerX);
        this.setPosY(centerY);
        Ellipse ball=new Ellipse(centerX,centerY,radius,radius);
        ball.setFill(Color.web(this.getColor()));
        this.setPaint(Color.web(this.getColor()));
        this.setShape(ball);
        this.setGroup( new Group());
        this.getGroup().getChildren().add(ball);
        return this.getGroup();
    }
    public Group draw_obstacle(String color, double radius, double centerX, double centerY){
        Ellipse ball = new Ellipse(centerX,centerY,radius,radius);
        ball.setFill(Color.web(color));
        Group g =new Group(ball);
        return g;
    }
    public Group gameover_animation(){
        group2 = new Group();
        this.setGame_over(new ArrayList<>());
        ArrayList<String> colors=new ArrayList<>();
        colors.add("FAE100");colors.add("900DFF");colors.add("32DBF0");colors.add("FF0181");
        double x=this.getPosX();
        double y=this.getPosY();
        for(int i=0;i<100;i++){
            String color = colors.get((int) (Math.random()*(4)));
            int radius =(int) (Math.random()*(3)+2.5);
            this.getGame_over().add(draw_obstacle(color,radius,x,y));
            this.group2.getChildren().add(this.getGame_over().get(i));
            this.getGame_over().get(i).setOpacity(0);
        }
        return group2;
    }
    public void game_over(double x, double y){
        if(only_once){
            only_once=false;
            this.getShape().setOpacity(0);
            ArrayList<Polyline> path = new ArrayList<>();
            for(int i=0;i<this.getGame_over().size()/2;i++){
                path.add(new Polyline());
                path.get(i).getPoints().addAll(new Double[]{
                        x,y,
                        450.0,y-(Math.random()*(3)+2.5)-i*5,
                        x-(Math.random()*(3)+2.5)*10-i*20,y+(Math.random()*(3)+2.5)*20
                });
                this.getGame_over().get(i).setOpacity(10);
            }
            for(int i=this.getGame_over().size()/2;i<this.getGame_over().size();i++){
                path.add(new Polyline());
                path.get(i).getPoints().addAll(new Double[]{
                        x,y,
                        0.0,y-(Math.random()*(3)+2.5)-i*5,
                        450-(x-(Math.random()*(3)+2.5)*10-i*20),y+(Math.random()*(3)+2.5)*20
                });
                this.getGame_over().get(i).setOpacity(10);
            }
            for(int i=0;i<this.getGame_over().size();i++){
                this.setPathTransition(new PathTransition());
                this.getPathTransition().setNode(game_over.get(i));
                this.getPathTransition().setDuration(Duration.seconds((Math.random()*(5)+1)));
                this.getPathTransition().setPath(path.get(i));
                this.getPathTransition().play();
            }
        }
    }
}
