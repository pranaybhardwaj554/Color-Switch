package sample;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public abstract class Obstacle_Combiner {
    private Obstacle obstacle1;
    private Obstacle obstacle2;
    private String commonColor;
    private double orientation1;
    private double orientation2;
    private Group group;
    private Timeline timeline1;

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
        this.setGroup(getObstacle1().draw());
        this.getGroup().getChildren().add(getObstacle2().draw());
        getObstacle1().getTimeline().stop();
        getObstacle1().getTimeline().stop();
        return this.getGroup();
    }

    public Shape[] giveShape(Paint color){
        Shape[] give = new Shape[6];
        Shape[] part1 = new Shape[3];
        Shape[] part2 = new Shape[3];
        for(int i=0;i<3;i++)
            give[i]=part1[i];
        for(int i=3;i<6;i++)
            give[i]=part2[i-3];
        return give;
    }

    public abstract void move();
}

