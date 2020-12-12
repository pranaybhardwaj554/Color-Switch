package sample;

public class VerticalLine extends Obstacle {
    private double linearSpeed;
    private boolean isRightward;
    private double lineSize;
    private double orientation;

    public VerticalLine(int posX, int posY, double linearSpeed, boolean isRightward, double lineSize) {
        super(posX, posY);
        this.linearSpeed = linearSpeed;
        this.isRightward = isRightward;
        this.lineSize = lineSize;
        this.orientation=0;
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
}
