package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveWork implements Serializable {
    private ArrayList<Point> points;
    private Point ballpoint;



    public SaveWork() {
        points=new ArrayList<>();
    }

    public void add(Point point){
        points.add(point);
    }
    public void remove(Point point){
        points.remove(point);
    }



    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public Point getBallpoint() {
        return ballpoint;
    }

    public void setBallpoint(Point ballpoint) {
        this.ballpoint = ballpoint;
    }
    public void display(){
        for(int i=0;i<points.size();i++){
            System.out.println(points.get(i));
        }
    }

}
