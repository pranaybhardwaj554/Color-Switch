package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.util.ArrayList;

public class VerticalLine extends Obstacle {
    private double linearSpeed;
    private boolean isRightward;
    private double lineSize;
    private double orientation;
    private Group group1;
    private Group group2;
    private Timeline timeline1;
    private Timeline timeline2;

    public VerticalLine(double posX, double posY, double linearSpeed, boolean isRightward, double lineSize) {
        super(posX, posY);
        this.linearSpeed = linearSpeed;
        this.isRightward = isRightward;
        this.lineSize = lineSize;
        this.orientation=0;
    }

    public void translate(){
        Translate translate1 = new Translate();
        Translate translate2 = new Translate();
        this.getGroup1().getTransforms().add(translate1);
        this.getGroup2().getTransforms().add(translate2);
        timeline1 = new Timeline(
                new KeyFrame(Duration.seconds(5), new KeyValue(translate1.xProperty(), translate1.getX()+420)));
        timeline1.setCycleCount(Timeline.INDEFINITE);
        timeline1.setAutoReverse(true);
        timeline2 = new Timeline(
                new KeyFrame(Duration.seconds(5), new KeyValue(translate2.xProperty(), translate1.getX()-420)));
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.setAutoReverse(true);
        timeline2.play();
        timeline1.play();
    }
    @Override
    public boolean actionsPerformed(Ball ball,Group g){
        g.getChildren().add(ball.gameover_animation());
        ball.game_over(ball.getGroup().getBoundsInParent().getCenterX(),ball.getGroup().getBoundsInParent().getCenterY());
        timeline1.stop();
        timeline2.stop();
        return false;
    }

    @Override
    public void pause(){
        timeline1.pause();
        timeline2.pause();
    }

    @Override
    public void resume(){
        timeline1.play();
        timeline2.play();
    }

    public Group getGroup1() {
        return group1;
    }

    public void setGroup1(Group group1) {
        this.group1 = group1;
    }

    public Group getGroup2() {
        return group2;
    }

    public void setGroup2(Group group2) {
        this.group2 = group2;
    }

    public double getLinearSpeed() {
        return linearSpeed;
    }

    public void setLinearSpeed(double linearSpeed) {
        this.linearSpeed = linearSpeed;
    }

    public boolean isRightward() {
        return isRightward;
    }

    public void setRightward(boolean rightward) {
        isRightward = rightward;
    }

    public double getLineSize() {
        return lineSize;
    }

    public void setLineSize(double lineSize) {
        this.lineSize = lineSize;
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
        double length=this.getLineSize();
        double centerX=this.getPosX();
        double centerY = this.getPosY();
        Line line1=new Line(-100,centerY-length,-100,centerY+length);
        line1.setStrokeLineCap(StrokeLineCap.ROUND);
        line1.setStroke(Color.web(yellow));
        line1.setStrokeWidth(20);
        Line line2=new Line(430,centerY-length/2.0,430,centerY+length/2.0);
        line2.setStrokeLineCap(StrokeLineCap.ROUND);
        line2.setStroke(Color.web(yellow));
        line2.setStrokeWidth(10);
        Line line3=new Line(550,centerY-length,550,centerY+length);
        line3.setStrokeLineCap(StrokeLineCap.ROUND);
        line3.setStroke(Color.web(pink));
        line3.setStrokeWidth(20);
        Line line4=new Line(15,centerY-length/2.0,15,centerY+length/2.0);
        line4.setStrokeLineCap(StrokeLineCap.ROUND);
        line4.setStroke(Color.web(pink));
        line4.setStrokeWidth(10);
        Line line5=new Line(235,centerY-length,235,centerY+length);
        line5.setStrokeLineCap(StrokeLineCap.ROUND);
        line5.setStroke(Color.web(blue));
        line5.setStrokeWidth(20);
        Line line6=new Line(290,centerY-length/2.0,290,centerY+length/2.0);
        line6.setStrokeLineCap(StrokeLineCap.ROUND);
        line6.setStroke(Color.web(blue));
        line6.setStrokeWidth(10);
        Line line7=new Line(150,centerY-length,150,centerY+length);
        line7.setStrokeLineCap(StrokeLineCap.ROUND);
        line7.setStroke(Color.web(purple));
        line7.setStrokeWidth(20);
        Line line8=new Line(350,centerY-length/2.0,350,centerY+length/2.0);
        line8.setStrokeLineCap(StrokeLineCap.ROUND);
        line8.setStroke(Color.web(purple));
        line8.setStrokeWidth(10);
        this.setGroup1(new Group(line1,line4,line5,line8));
        this.setGroup2(new Group(line2,line3,line6,line7));
        Shape[] shape = {line8,line6,line1,line3,line2,line4,line7,line5};
        this.setShape(shape);
        this.setGroup(new Group(this.getGroup1(),this.getGroup2()));
        this.translate();
        return this.getGroup();
    }

}
