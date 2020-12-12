package sample;

public class Obstacle_Combiner {
    private Obstacle obstacle1;
    private Obstacle obstacle2;
    private String commonColor;
    private double orientation1;
    private double orientation2;

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
}
