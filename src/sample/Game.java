package sample;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game  {
    boolean a=true;
    AnimationTimer timer;
    Label score;
    Group finalg;
    int i=0;
    int down_by=2;
    static int times=0;
    static Group ballg2;
    static Ball ball;
    static CircleObstacle obs1;
    Group obstacle;
    ArrayList<Group> obss=new ArrayList<>();
    ArrayList<Collidable> Colli=new ArrayList<>();
    SaveWork saveWork;
    Timeline tl;
    int k=0;
    int savestuff=0;
    boolean isPaused=false;
    public Scene Start(double width, double height)throws
            InterruptedException{
        finalg = new Group();
        saveWork=new SaveWork();
        String yellow="FAE100";String purple="900DFF";String blue ="32DBF0";String pink="FF0181";
        ball = new Ball(pink,10,0,0,19);
        Group ballg = new Group();
        obstacle=new Group();
        Button pausebutton=new Button();
        Group pause=new Group();
        pause.getChildren().add(pausebutton);
        File pause1 = new File("pause.png");
        Image img = new Image("file:///"+pause1.getAbsolutePath().replace("\\","/"));
        ImageView view = new ImageView(img);
        view.setFitHeight(25);
        view.setPreserveRatio(true);
        pausebutton.setGraphic(view);
        pausebutton.setLayoutX(415);
        pausebutton.setId("pausebutton");
        pausebutton.setLayoutY(5);
        pausebutton.setPrefWidth(25);
        pausebutton.setPrefHeight(25);
        pausebutton.setPadding(Insets.EMPTY);
        Group group1 = new Group();

        ballg2 = new Group();
        ballg.getChildren().add(ball.draw(width/2.0,height-30));
        ballg2.getChildren().add(ballg);
        ballg2.getChildren().add(group1);
        finalg.getChildren().add(ballg2);
        finalg.getChildren().add(Score(width,height));
        Scene scene = new Scene(finalg,width,height);
        obs1= new CircleObstacle( width/2.0,300,width/2,true,100,17.5);
        Colli.add(obs1);
        Group prevobs=obs1.draw();
        obstacle.getChildren().add(prevobs);
        obss.add(prevobs);
        timer = new SpawnTimer(width,height, prevobs);
        timer.start();
        finalg.getChildren().add(obstacle);
        scene.setFill(Color.web("272727"));
        File f = new File("stylesheet.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));
        //Setting EventListener For Listening event i.e. when UP Button is being pressed
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case W:
                        System.out.println("yes");

                        if(times==0) {
                            up(ball, ballg);
                        }
                }
            }
        });
        finalg.getChildren().add(pause);
        InGameSettings ingamesettings=new InGameSettings(new Pane(), scene);
        Pane pausepane=ingamesettings.settings();
        Scene pauseScene=new Scene(pausepane,width, height);

        pausebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    save();
                    ingamesettings.setGame(Game.this);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                isPaused=true;
                ingamesettings.setSaveWork(saveWork);

                File f = new File("stylesheet.css");
                pauseScene.getStylesheets().clear();
                pauseScene.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));

                MainMenu menu= new MainMenu();
                Stage stage=MainMenu.getPrimaryStage();
                stage.setScene(pauseScene);
            }
        });
        return scene;
    }

    //Move Ball upwards on pressing UP Button
    public void up(Ball ball,Group obs){
        Translate translate = new Translate();
        ball.getGroup().getTransforms().add(translate);
        KeyValue end = new KeyValue(translate.yProperty(), -150);
        KeyFrame go_up = new KeyFrame(Duration.millis(250),end);
        Timeline t1 = new Timeline(go_up);
        t1.setAutoReverse(false);

        t1.play();
        if(i++ == 0) {
            down(ball,obs);
        }
    }

    public Group spawnobstacle(double width, double height){
        int spawnob=(int) (Math.random()*10);
        int clock= (int) (Math.random()*2);
        boolean clockWise=true;
        if(clock==0)
            clockWise=false;
        if(spawnob==0) {
            CircleObstacle obstacle = new CircleObstacle(width / 2.0, -130, 20, clockWise, 100, 17.5);
            Colli.add(obstacle);
            return obstacle.draw();
        }
        else if(spawnob==1) {
            TriangleObstacle obstacle = new TriangleObstacle(width / 2.0, -130, 20, clockWise, 250,ball.getPaint());
            Colli.add(obstacle);
            return obstacle.draw();
        }
        else if(spawnob==2) {
            SquareObstacle obstacle = new SquareObstacle(width / 2.0, -130, 20, clockWise, 200);
            Colli.add(obstacle);
            return obstacle.draw();
        }
        else if(spawnob==3) {
            CrossObstacle obstacle = new CrossObstacle(width / 2.0+70, -130, 20, clockWise, 120,20);
            Colli.add(obstacle);
            return obstacle.draw();
        }
        else if(spawnob==4) {
            VerticalLine obstacle = new VerticalLine(0, -130, 20, clockWise, 75);
            Colli.add(obstacle);
            return obstacle.draw();
        }
        else if(spawnob==5) {
            double radius1=100;double radius2=70;double stroke=15;
            CircleObstacle obstacle1 = new CircleObstacle(width / 2.0+radius1+stroke/2.0, -130, 20, clockWise, radius1, stroke);
            CircleObstacle obstacle2 = new CircleObstacle(width / 2.0-radius2-stroke/2.0, -130, 20, clockWise, radius2, stroke);
            obstacle1.draw();obstacle2.draw();
            CircleCircle1Obstacle obstacle = new CircleCircle1Obstacle(obstacle1,obstacle2,ball.getColor());
            Colli.add(obstacle);
            return obstacle.draw();
        }
        else if(spawnob==6) {
            double radius1=115;double radius2=90;double stroke=15;
            CircleObstacle obstacle1 = new CircleObstacle(width / 2.0, -130, 20, clockWise, radius1, stroke);
            CircleObstacle obstacle2 = new CircleObstacle(width / 2.0, -130, 20, clockWise, radius2, stroke);
            obstacle1.draw();obstacle2.draw();
            CircleCircle2Obstacle obstacle = new CircleCircle2Obstacle(obstacle1,obstacle2,ball.getColor());
            Colli.add(obstacle);
            return obstacle.draw();
        }
        else if(spawnob==7) {
            double radius1=90;double radius2=60;double stroke=22;
            CrossObstacle obstacle1 = new CrossObstacle(width / 2.0+radius1+stroke/2.0, -130.0, 20,clockWise,radius1,stroke );
            CrossObstacle obstacle2 = new CrossObstacle(width / 2.0-radius2-stroke/2.0, -130.0, 20, clockWise, radius2,2*stroke/3.0);
            obstacle1.draw();obstacle2.draw();
            PlusPlusObstacle obstacle = new PlusPlusObstacle(obstacle1,obstacle2,ball.getColor());
            Colli.add(obstacle);
            return obstacle.draw();
        }
        else if(spawnob==8) {
            Star obstacle = new Star("white",width/2.0,-130 );
            Colli.add(obstacle);
            return obstacle.draw();
        }
        else if(spawnob==9) {
            ColorSwitcher obstacle = new ColorSwitcher(15,width/2.0,width/2.0,-130 );
            Colli.add(obstacle);
            return obstacle.draw();
        }
        else {
            HorizontalLine obstacle = new HorizontalLine(0, -130, 20, clockWise);
            Colli.add(obstacle);
            return obstacle.draw();
        }
    }
    public void addtosavework(Group c){

    }

    public void bringdownobstacle(double width, double height, Group c){
        c.setTranslateY(c.getTranslateY()+5);
    }

    class SpawnTimer extends AnimationTimer {
        double height;
        double width;
        Group prevobs;

        public SpawnTimer( double width,double height, Group prevobs){
            this.width=width;
            this.height=height;
            this.prevobs=prevobs;

        }
        @Override
        public void handle(long now) {
            if(isPaused){
                for (int i = 0; i < Colli.size(); i++) {
                    Colli.get(i).pause();
                }

            }
            else {
                for (int i = 0; i < Colli.size(); i++) {
                    checkCollision(Colli.get(i));
                }
                if (obss.get(obss.size() - 1).getTranslateY() > 500) {
                    Group c = spawnobstacle(width, height);
                    obss.add(c);
                    obstacle.getChildren().add(c);
                }
                if (a && ball.getGroup().getBoundsInParent().getCenterY() < 500) {
                    for (int i = 0; i < obss.size(); i++) {
                        bringdownobstacle(width, height, obss.get(i));
                    }
                    bringdownobstacle(width, height, ball.getGroup());
                }
            }

        }
    }

    public void stopTimer(){
        timer.stop();
    }

    //Collision Checker
    public void checkCollision(Collidable obstacle){
        obstacle.checkColor(ball);
        ArrayList<Shape> intersect = new ArrayList<>();
        ArrayList<Shape> shape = obstacle.giveShape(ball.getPaint());
        for (int i = 0; i < shape.size(); i++) {
            try{
                intersect.add(Shape.intersect(ball.getShape(), shape.get(i)));
            }
            catch (NullPointerException e){
                System.out.println(obstacle.toString());
            }
            if (intersect.get(i).getBoundsInLocal().getWidth() != -1) {
                System.out.println("Collided");
                if(a){
                    a=obstacle.actionsPerformed(ball,finalg);
                    if(!a){
                        stopTimer();
                        tl.pause();
                    }
                    setScore();
                }
                break;
            }
        }
    }

    //Down Function To make ball feels gravity
    public void down(Ball ball,Group obs) {
        Translate translate = new Translate();
        ball.getGroup().getTransforms().addAll(translate);
        KeyValue end = new KeyValue(translate.yProperty(),140000);
        KeyFrame go_down = new KeyFrame(Duration.seconds(500),end);
        tl = new Timeline(go_down);
        tl.play();
        AnimationTimer a = new AnimationTimer() {
            @Override
            public void handle(long l) {

                    boolean t = true;
                    boolean a = ball.getGroup().getBoundsInParent().getCenterY() <= ball.getPosY();
                    if (a) {
                        tl.play();
                    } else {
                        tl.pause();
                        t = false;
                    }

            }
        };
        a.start();
    }

    public Label Score(double width , double height){
        score = new Label();
        Font style = Font.font("Arial, Helvetica, sans-serif", FontPosture.REGULAR, 45);
        score.setFont(style);
        score.setText(Integer.toString(ball.getStarsCollected()));
        score.setTextFill(Color.WHITE);
        score.setTranslateX(15);
        return score;
    }
    public void setScore(){
        score.setText(Integer.toString(ball.getStarsCollected()));
        return;
    }
    public void save() throws IOException {

        Point ballpoint=new Point(ball.getGroup().getBoundsInParent().getCenterX(),ball.getGroup().getBoundsInParent().getCenterY());
        saveWork.setBall(ball);
        System.out.println(ballpoint);
        saveWork.setBallpoint(ballpoint);
        for(int i=0;i<obss.size();i++){
            saveWork.add(Colli.get(i));
            saveWork.addPoint(new Point(obss.get(i).getTranslateX(), obss.get(i).getTranslateY()));

        }

    }
    public void deserialize(int i) throws IOException {
        ObjectInputStream in=null;
        try {
            in=new ObjectInputStream(new FileInputStream("./allsaves/out"+i+".txt"));
            saveWork=(SaveWork) in.readObject();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert in != null;
            in.close();
        }
    }



    public Scene loadGame(double width, double height, int x) throws
            InterruptedException, IOException {
        deserialize(x);
        obstacle=new Group();
        String yellow="FAE100";String purple="900DFF";String blue ="32DBF0";String pink="FF0181";
        finalg = new Group();
        ball = new Ball(saveWork.getBall().getColor(),10,0,saveWork.getBall().getStarsCollected(),19);
        Group ballg = new Group();

        Group group1 = new Group();
        ballg2 = new Group();
        ballg.getChildren().add(ball.draw(width/2.0,saveWork.getBallpoint().getY()-50));

        ballg2.getChildren().add(ballg);
        ballg2.getChildren().add(ball.gameover_animation());
        ballg2.getChildren().add(group1);
        finalg.getChildren().add(ballg2);
        finalg.getChildren().add(obstacle);
        TriangleObstacle triangleObstacle = new TriangleObstacle(2, -130, 20, true, 250,ball.getPaint());
        CircleObstacle obs1 = new CircleObstacle(2, -130, 20, true, 100, 20);
        HorizontalLine horline = new HorizontalLine(2, -130, 20, true);
        CrossObstacle obstaclea = new CrossObstacle(width / 2.0+70, -130, 20, true, 120,20);
        VerticalLine obstacle1 = new VerticalLine(0, -130, 20, true, 75);
        CircleObstacle cobstacle1 = new CircleObstacle(2, -130, 20, true, 30, 20);
        CircleObstacle cobstacle2 = new CircleObstacle(4, -130, 20, false, 40, 20);
        CircleCircle1Obstacle cobstacle = new CircleCircle1Obstacle(cobstacle1,cobstacle2,ball.getColor());
        CircleCircle2Obstacle cobstaclec = new CircleCircle2Obstacle(cobstacle1,cobstacle2,ball.getColor());
        Star sta = new Star("white",width/2.0,-130 );
        ColorSwitcher co = new ColorSwitcher(15,width/2.0,width/2.0,-130 );

        CrossObstacle xobstacle1 = new CrossObstacle(5, -130.0, 20,true,2,20 );
        CrossObstacle xobstacle2 = new CrossObstacle(5, -130.0, 20, false, 5,6);

        PlusPlusObstacle xobstacle = new PlusPlusObstacle(xobstacle1,xobstacle2,ball.getColor());
        finalg.getChildren().add(Score(width, height));
        for(int i=0; i<saveWork.getPointobs().size();i++){
            if(saveWork.get(i).getClass()==triangleObstacle.getClass()) {

                TriangleObstacle triangleObstacle1 = new TriangleObstacle(((TriangleObstacle)saveWork.get(i)).getPosX(), ((TriangleObstacle)saveWork.get(i)).getPosY(), ((TriangleObstacle)saveWork.get(i)).getRotatingSpeed(), ((TriangleObstacle)saveWork.get(i)).isClockwise(), ((TriangleObstacle)saveWork.get(i)).getSide(),((TriangleObstacle)saveWork.get(i)).getColor());
                Colli.add(triangleObstacle1);

                Group c=triangleObstacle1.draw();
                c.setTranslateX(saveWork.getPoint(i).getX());
                c.setTranslateY(saveWork.getPoint(i).getY());
                obstacle.getChildren().add(c);
                obss.add(c);


            }
            else if(saveWork.get(i).getClass()==obs1.getClass()) {
                CircleObstacle circ = new CircleObstacle(((CircleObstacle)saveWork.get(i)).getPosX(), ((CircleObstacle)saveWork.get(i)).getPosY(), ((CircleObstacle)saveWork.get(i)).getRotatingSpeed(), ((CircleObstacle)saveWork.get(i)).getIsClockWise(), ((CircleObstacle)saveWork.get(i)).getRadius(), ((CircleObstacle)saveWork.get(i)).getStrokeWidth());
                Colli.add(circ);
                Group c=circ.draw();
                c.setTranslateX(saveWork.getPoint(i).getX());
                c.setTranslateY(saveWork.getPoint(i).getY());
                obstacle.getChildren().add(c);
                obss.add(c);

            }
            else if(saveWork.get(i).getClass()==horline.getClass()) {
                HorizontalLine horline2 = new HorizontalLine(((HorizontalLine)saveWork.get(i)).getPosX(), ((HorizontalLine)saveWork.get(i)).getPosY(), ((HorizontalLine)saveWork.get(i)).getLinearSpeed(), ((HorizontalLine)saveWork.get(i)).isRightward());
                Colli.add(horline2);
                Group c=horline2.draw();
                c.setTranslateX(saveWork.getPoint(i).getX());
                c.setTranslateY(saveWork.getPoint(i).getY());
                obstacle.getChildren().add(c);
                obss.add(c);
            }
            else if(saveWork.get(i).getClass()==obstaclea.getClass()) {
                CrossObstacle cross = new CrossObstacle(((CrossObstacle)saveWork.get(i)).getPosX(), ((CrossObstacle)saveWork.get(i)).getPosY(), ((CrossObstacle)saveWork.get(i)).getRotatingSpeed(), ((CrossObstacle)saveWork.get(i)).isClockwise(),((CrossObstacle)saveWork.get(i)).getOneSideLength(),((CrossObstacle)saveWork.get(i)).getStroke());
                Colli.add(cross);
                Group c=cross.draw();
                c.setTranslateX(saveWork.getPoint(i).getX());
                c.setTranslateY(saveWork.getPoint(i).getY());
                obstacle.getChildren().add(c);
                obss.add(c);
            }
            else if(saveWork.get(i).getClass()==obstacle1.getClass()) {
                VerticalLine vert = new VerticalLine(((VerticalLine)saveWork.get(i)).getPosX(), ((VerticalLine)saveWork.get(i)).getPosY(), ((VerticalLine)saveWork.get(i)).getLinearSpeed(), ((VerticalLine)saveWork.get(i)).isRightward(),((VerticalLine)saveWork.get(i)).getLineSize());
                Colli.add(vert);
                Group c=vert.draw();
                c.setTranslateX(saveWork.getPoint(i).getX());
                c.setTranslateY(saveWork.getPoint(i).getY());
                obstacle.getChildren().add(c);
                obss.add(c);
            }

            else if(saveWork.get(i).getClass()==cobstacle.getClass()) {
                CircleObstacle circ1 = new CircleObstacle(((CircleCircle1Obstacle)saveWork.get(i)).getObstacle1().getPosX(), ((CircleCircle1Obstacle)saveWork.get(i)).getObstacle1().getPosY(), ((CircleObstacle)(((CircleCircle1Obstacle)saveWork.get(i)).getObstacle1())).getRotatingSpeed(), ((CircleObstacle)(((CircleCircle1Obstacle)saveWork.get(i)).getObstacle1())).isClockWise(), ((CircleObstacle)(((CircleCircle1Obstacle)saveWork.get(i)).getObstacle1())).getRadius(), ((CircleObstacle)(((CircleCircle1Obstacle)saveWork.get(i)).getObstacle1())).getStrokeWidth());
                CircleObstacle circ2 = new CircleObstacle(((CircleCircle1Obstacle)saveWork.get(i)).getObstacle1().getPosX(), ((CircleCircle1Obstacle)saveWork.get(i)).getObstacle1().getPosY(), ((CircleObstacle)(((CircleCircle1Obstacle)saveWork.get(i)).getObstacle2())).getRotatingSpeed(), ((CircleObstacle)(((CircleCircle1Obstacle)saveWork.get(i)).getObstacle2())).isClockWise(), ((CircleObstacle)(((CircleCircle1Obstacle)saveWork.get(i)).getObstacle2())).getRadius(), ((CircleObstacle)(((CircleCircle1Obstacle)saveWork.get(i)).getObstacle2())).getStrokeWidth());
                CircleCircle1Obstacle circ3 = new CircleCircle1Obstacle(circ1,circ2,((CircleCircle1Obstacle)saveWork.get(i)).getCommonColor());
                Colli.add(circ3);
                Group c=circ3.draw();
                c.setTranslateX(saveWork.getPoint(i).getX());
                c.setTranslateY(saveWork.getPoint(i).getY());
                obstacle.getChildren().add(c);
                obss.add(c);
            }
            else if(saveWork.get(i).getClass()==xobstacle.getClass()) {
                CrossObstacle circ1 = new CrossObstacle(((PlusPlusObstacle)saveWork.get(i)).getObstacle1().getPosX(), ((PlusPlusObstacle)saveWork.get(i)).getObstacle1().getPosY(), ((CrossObstacle)(((PlusPlusObstacle)saveWork.get(i)).getObstacle1())).getRotatingSpeed(), ((CrossObstacle)(((PlusPlusObstacle)saveWork.get(i)).getObstacle1())).isClockwise(), ((CrossObstacle)(((PlusPlusObstacle)saveWork.get(i)).getObstacle1())).getOneSideLength(), ((CrossObstacle)(((PlusPlusObstacle)saveWork.get(i)).getObstacle1())).getStroke());
                CrossObstacle circ2 = new CrossObstacle(((PlusPlusObstacle)saveWork.get(i)).getObstacle1().getPosX(), ((PlusPlusObstacle)saveWork.get(i)).getObstacle1().getPosY(), ((CrossObstacle)(((PlusPlusObstacle)saveWork.get(i)).getObstacle2())).getRotatingSpeed(), ((CrossObstacle)(((PlusPlusObstacle)saveWork.get(i)).getObstacle2())).isClockwise(), ((CrossObstacle)(((PlusPlusObstacle)saveWork.get(i)).getObstacle2())).getOneSideLength(), ((CrossObstacle)(((PlusPlusObstacle)saveWork.get(i)).getObstacle2())).getStroke());
                PlusPlusObstacle circ3 = new PlusPlusObstacle(circ1,circ2,((PlusPlusObstacle)saveWork.get(i)).getCommonColor());
                Colli.add(circ3);
                Group c=circ3.draw();
                c.setTranslateX(saveWork.getPoint(i).getX());
                c.setTranslateY(saveWork.getPoint(i).getY());
                obstacle.getChildren().add(c);
                obss.add(c);
            }
            else if(saveWork.get(i).getClass()==cobstaclec.getClass()) {
                CircleObstacle circ1 = new CircleObstacle(((CircleCircle2Obstacle)saveWork.get(i)).getObstacle1().getPosX(), ((CircleCircle2Obstacle)saveWork.get(i)).getObstacle1().getPosY(), ((CircleObstacle)(((CircleCircle2Obstacle)saveWork.get(i)).getObstacle1())).getRotatingSpeed(), ((CircleObstacle)(((CircleCircle2Obstacle)saveWork.get(i)).getObstacle1())).isClockWise(), ((CircleObstacle)(((CircleCircle2Obstacle)saveWork.get(i)).getObstacle1())).getRadius(), ((CircleObstacle)(((CircleCircle2Obstacle)saveWork.get(i)).getObstacle1())).getStrokeWidth());
                CircleObstacle circ2 = new CircleObstacle(((CircleCircle2Obstacle)saveWork.get(i)).getObstacle1().getPosX(), ((CircleCircle2Obstacle)saveWork.get(i)).getObstacle1().getPosY(), ((CircleObstacle)(((CircleCircle2Obstacle)saveWork.get(i)).getObstacle2())).getRotatingSpeed(), ((CircleObstacle)(((CircleCircle2Obstacle)saveWork.get(i)).getObstacle2())).isClockWise(), ((CircleObstacle)(((CircleCircle2Obstacle)saveWork.get(i)).getObstacle2())).getRadius(), ((CircleObstacle)(((CircleCircle2Obstacle)saveWork.get(i)).getObstacle2())).getStrokeWidth());
                CircleCircle2Obstacle circ3 = new CircleCircle2Obstacle(circ1,circ2,((CircleCircle2Obstacle)saveWork.get(i)).getCommonColor());
                Colli.add(circ3);
                Group c=circ3.draw();
                c.setTranslateX(saveWork.getPoint(i).getX());
                c.setTranslateY(saveWork.getPoint(i).getY());
                obstacle.getChildren().add(c);
                obss.add(c);
            }
            else if(saveWork.get(i).getClass()==sta.getClass()) {
                Star star = new Star(((Star)saveWork.get(i)).getColor(),((Star)saveWork.get(i)).getPosX(),((Star)saveWork.get(i)).getPosY());
                Colli.add(star);
                Group c=star.draw();
                c.setTranslateX(saveWork.getPoint(i).getX());
                c.setTranslateY(saveWork.getPoint(i).getY());
                obstacle.getChildren().add(c);
                obss.add(c);
                if(c.getBoundsInParent().getCenterY()>saveWork.getBallpoint().getY()){
                    c.setOpacity(0);


                }
            }
            else if(saveWork.get(i).getClass()==co.getClass()) {
                ColorSwitcher colorSwitcher = new ColorSwitcher(((ColorSwitcher)saveWork.get(i)).getRadius(),((ColorSwitcher)saveWork.get(i)).getRotatingSpeed(),((ColorSwitcher)saveWork.get(i)).getPosX(),((ColorSwitcher)saveWork.get(i)).getPosY() );
                Colli.add(colorSwitcher);
                Group c=colorSwitcher.draw();
                c.setTranslateX(saveWork.getPoint(i).getX());
                c.setTranslateY(saveWork.getPoint(i).getY());
                obstacle.getChildren().add(c);
                obss.add(c);
                if(c.getBoundsInParent().getCenterY()>saveWork.getBallpoint().getY()){
                    c.setOpacity(0);

                }
            }


        }
        Button pausebutton=new Button();
        Group pause=new Group();
        pause.getChildren().add(pausebutton);
        File pause1 = new File("pause.png");
        Image img = new Image("file:///"+pause1.getAbsolutePath().replace("\\","/"));
        ImageView view = new ImageView(img);
        view.setFitHeight(25);
        view.setPreserveRatio(true);
        pausebutton.setGraphic(view);
        pausebutton.setLayoutX(415);
        pausebutton.setId("pausebutton");
        pausebutton.setLayoutY(5);
        pausebutton.setPrefWidth(25);
        pausebutton.setPrefHeight(25);
        pausebutton.setPadding(Insets.EMPTY);

        timer = new SpawnTimer(width,height, triangleObstacle.draw());
        timer.start();







        Scene loadscene=new Scene(finalg,width,height);

        loadscene.setFill(Color.web("272727"));
        File f = new File("stylesheet.css");
        loadscene.getStylesheets().clear();
        loadscene.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));
        loadscene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case W:
                        System.out.println("yes");
                        if(times==0) {
                            up(ball, ballg);
                        }
                }
            }
        });

        finalg.getChildren().add(pause);
        InGameSettings ingamesettings=new InGameSettings(new Pane(), loadscene);

        pausebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    save();
                    ingamesettings.setGame(Game.this);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                isPaused=true;
                ingamesettings.setSaveWork(saveWork);
                savestuff++;
                Pane pausepane=ingamesettings.settings();
                Scene pauseScene=new Scene(pausepane,width, height);
                File f = new File("stylesheet.css");
                pauseScene.getStylesheets().clear();
                pauseScene.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));

                MainMenu menu= new MainMenu();
                Stage stage=MainMenu.getPrimaryStage();
                stage.setScene(pauseScene);
            }
        });

        return loadscene;

    }
    public void setisPaused(boolean val){
        isPaused=val;
    }

    public ArrayList<Collidable> getColli() {
        return Colli;
    }
}
