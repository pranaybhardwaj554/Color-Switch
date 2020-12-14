package sample;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public abstract class Obstacle_Combiner implements Collidable {
    protected Obstacle obstacle1;
    protected Obstacle obstacle2;
    private String commonColor;
    private double orientation1;
    private double orientation2;
    private Group group;
    private Timeline timeline1;
    private Timeline timeline2;

    public Obstacle_Combiner(Obstacle obstacle1, Obstacle obstacle2, String commonColor) {
        this.obstacle1 = obstacle1;
        this.obstacle2 = obstacle2;
        this.commonColor = commonColor;
        this.orientation1 = 0;
        this.orientation2 = 0;
    }
    public boolean isCollidedWithAny(){
        //code elided
        return false;
    }
    public boolean isCombinable(){
        //code elided
        return false;
    }
    public void spawn(int posX,int posY){
        //code elided
        return;
    }
    public Obstacle getObstacle1() {
        return obstacle1;
    }

    public void setObstacle1(Obstacle obstacle1) {
        this.obstacle1 = obstacle1;
    }

    public Obstacle getObstacle2() {
        return obstacle2;
    }

    public void setObstacle2(Obstacle obstacle2) {
        this.obstacle2 = obstacle2;
    }

    public String getCommonColor() {
        return commonColor;
    }

    public void setCommonColor(String commonColor) {
        this.commonColor = commonColor;
    }

    public double getOrientation1() {
        return orientation1;
    }

    public void setOrientation1(double orientation1) {
        this.orientation1 = orientation1;
    }

    public double getOrientation2() {
        return orientation2;
    }

    public void setOrientation2(double orientation2) {
        this.orientation2 = orientation2;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group draw(){
        Group g1=getObstacle1().draw();
        Group g2=getObstacle2().draw();
        this.setGroup(new Group(g1,g2));
        getObstacle1().getTimeline().stop();
        getObstacle2().getTimeline().stop();
        this.set();
        this.move();
        return this.getGroup();
    }

    @Override
    public boolean actionsPerformed(Ball ball,Group g){
        g.getChildren().add(ball.gameover_animation());
        ball.game_over(ball.getGroup().getBoundsInParent().getCenterX(),ball.getGroup().getBoundsInParent().getCenterY());
        this.getTimeline1().stop();
        this.getTimeline2().stop();
        return false;
    }

    @Override
    public void checkColor(Ball ball){
        if(!ball.getColor().equals(getCommonColor())){
            setCommonColor(ball.getColor());
            getTimeline1().stop();
            getTimeline2().stop();
            move();
        }
    }

    @Override
    public void pause(){
        getTimeline1().pause();
        getTimeline2().pause();
    }

    @Override
    public void resume(){
        getTimeline1().play();
        getTimeline2().play();
    }

    public ArrayList<Shape> giveShape(Paint color){
        ArrayList<Shape> give = new ArrayList<>();
        ArrayList<Shape> part1 = obstacle1.giveShape(color);
        ArrayList<Shape> part2 = obstacle2.giveShape(color);
        for(int i=0;i<part1.size();i++)
            give.add(part1.get(i));
        for(int i=0;i<part2.size();i++)
            give.add(part2.get(i));
        return give;
    }

    public abstract void move();

    public abstract void set();

    public Timeline getTimeline1() {
        return timeline1;
    }

    public void setTimeline1(Timeline timeline1) {
        this.timeline1 = timeline1;
    }

    public Timeline getTimeline2() {
        return timeline2;
    }

    public void setTimeline2(Timeline timeline2) {
        this.timeline2 = timeline2;
    }
}

