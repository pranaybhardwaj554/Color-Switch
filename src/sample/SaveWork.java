package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveWork implements Serializable {
    private ArrayList<Collidable> pointobs;
    private ArrayList<Point> points;
    private Point ballpoint;
    private Ball ball;





    public SaveWork() {
        pointobs=new ArrayList<>();
        points=new ArrayList<>();
    }

    public void add(Collidable obstacle){
        pointobs.add(obstacle);
    }
    public void remove(Collidable obstacle){
        pointobs.remove(obstacle);
    }
    public Collidable get(int i){
        return pointobs.get(i);

    }


    public void addPoint(Point point){
        points.add(point);
    }
    public void remove(Point point){
        points.remove(point);
    }
    public Point getPoint(int i){
        return points.get(i);
    }



    public ArrayList<Collidable> getPointobs() {
        return pointobs;
    }

    public void setPointobs(ArrayList<Collidable> pointobs) {
        this.pointobs = pointobs;
    }

    public Point getBallpoint() {
        return ballpoint;
    }

    public void setBallpoint(Point ballpoint) {
        this.ballpoint = ballpoint;
    }
    public void display(){
        for(int i=0;i<pointobs.size();i++){
            System.out.println(pointobs.get(i));
        }
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
    public void removeall(){
        pointobs.clear();
        points.clear();
        ballpoint=null;
        ball=null;
    }

}
