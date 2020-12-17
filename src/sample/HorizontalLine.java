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

public class HorizontalLine extends Obstacle {
    private double linearSpeed;
    private boolean isRightward;
    private double Orientation;

    public HorizontalLine(double posX, double posY, double linearSpeed, boolean isRightward) {
        super(posX, posY);
        this.linearSpeed = linearSpeed;
        this.isRightward = isRightward;
        Orientation = 0;
    }

    @Override
    public double getSize(){
        return 10.0;
    }
    public void moving(){
        Translate translate = new Translate();
        this.getGroup().getTransforms().add(translate);
        this.setTimeline(new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(translate.xProperty(), -450)),
                new KeyFrame(Duration.seconds(getLinearSpeed()), new KeyValue(translate.xProperty(), 0))));
        this.getTimeline().setCycleCount(Timeline.INDEFINITE);
        this.getTimeline().setAutoReverse(false);
        this.getTimeline().play();

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

    @Override
    public double getOrientation() {
        return Orientation;
    }

    public void setOrientation(double orientation) {
        Orientation = orientation;
    }

    public Group draw(){
        double line_length=450;
        double startX=this.getPosX();
        double startY=this.getPosY();
        Line r1 = new Line(startX, startY, startX+line_length/4.0, startY);
        r1.setStrokeLineCap(StrokeLineCap.BUTT);
        r1.setStroke(Color.web("32DBF0"));
        r1.setStrokeWidth(20);
        Line r2 = new Line(startX+line_length/4.0, startY, startX+line_length/2.0, startY);
        r2.setStrokeLineCap(StrokeLineCap.BUTT);
        r2.setStroke(Color.web("900DFF"));
        r2.setStrokeWidth(20);
        Line r3 = new Line(startX+line_length/2.0, startY, startX+3*line_length/4.0, startY);
        r3.setStrokeLineCap(StrokeLineCap.BUTT);
        r3.setStroke(Color.web("FF0181"));
        r3.setStrokeWidth(20);
        Line r4 = new Line(startX+3*line_length/4.0, startY, startX+line_length, startY);
        r4.setStrokeLineCap(StrokeLineCap.BUTT);
        r4.setStroke(Color.web("FAE100"));
        r4.setStrokeWidth(20);
        Line r5 = new Line(startX+line_length, startY, startX+5*line_length/4.0, startY);
        r5.setStrokeLineCap(StrokeLineCap.BUTT);
        r5.setStroke(Color.web("32DBF0"));
        r5.setStrokeWidth(20);
        Line r6 = new Line(startX+5*line_length/4.0, startY, startX+6*line_length/4.0, startY);
        r6.setStrokeLineCap(StrokeLineCap.BUTT);
        r6.setStroke(Color.web("900DFF"));
        r6.setStrokeWidth(20);
        Line r7 = new Line(startX+6*line_length/4.0, startY, startX+7*line_length/4.0, startY);
        r7.setStrokeLineCap(StrokeLineCap.BUTT);
        r7.setStroke(Color.web("FF0181"));
        r7.setStrokeWidth(20);
        Line r8 = new Line(startX+7*line_length/4.0, startY,startX+8*line_length/4.0, startY);
        r8.setStrokeLineCap(StrokeLineCap.BUTT);
        r8.setStroke(Color.web("FAE100"));
        r8.setStrokeWidth(20);
        Shape shape[] =  {r1,r2,r3,r4,r5,r6,r7,r8};
        this.setShape(shape);
        this.setGroup(new Group(r1,r2,r3,r4,r5,r6,r7,r8));
        this.moving();
        return this.getGroup();
    }
}
