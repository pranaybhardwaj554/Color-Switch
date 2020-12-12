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
    ArrayList<Obstacle> Colli=new ArrayList<>();
    SaveWork saveWork;
    Timeline tl;
    int k=0;
    int savestuff=0;
    public Scene Start(double width, double height)throws
            InterruptedException{
        finalg = new Group();
        saveWork=new SaveWork();
        String yellow="FAE100";String purple="900DFF";String blue ="32DBF0";String pink="FF0181";
        ball = new Ball(purple,10,0,0,19);
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
        obs1= new CircleObstacle( width/2.0,300,width/2,true,100,20);
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
                        down_by=3;
                        if(times==0) {
                            up(ball, ballg);
                        }
                }
            }
        });
        finalg.getChildren().add(pause);
        InGameSettings ingamesettings=new InGameSettings(new Pane(), scene);
        ingamesettings.setGame(this);
        pausebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        int spawnob=(int) (Math.random()*6);
        int clock= (int) (Math.random()*2);
        boolean clockWise=true;
        if(clock==0)
            clockWise=false;
        if(spawnob==0) {
            CircleObstacle obstacle = new CircleObstacle(width / 2.0, -130, 20, clockWise, 100, 20);
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
            CrossObstacle obstacle = new CrossObstacle(width / 2.0+70, -130, 20, clockWise, 120);
            Colli.add(obstacle);
            return obstacle.draw();
        }
        else if(spawnob==4) {
            VerticalLine obstacle = new VerticalLine(0, -130, 20, clockWise, 75);
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
            for(int i=0;i<Colli.size();i++ ){
                checkCollision(Colli.get(i));
            }
            if(obss.get(obss.size()-1).getTranslateY()>500){
                Group c=spawnobstacle(width, height);
                obss.add(c);
                obstacle.getChildren().add(c);
            }
            if(a && ball.getGroup().getBoundsInParent().getCenterY()<500 ) {
                for(int i=0;i<obss.size();i++){
                    bringdownobstacle(width, height, obss.get(i));
                }
                bringdownobstacle(width,height, ball.getGroup());
            }
            if(savestuff==1){

            }

        }
    }

    public void stopTimer(){
        timer.stop();
    }

    //Collision Checker
    public void checkCollision(Obstacle obstacle){
        ArrayList<Shape> intersect = new ArrayList<>();
        Shape shape[] = obstacle.giveShape(ball.getPaint());
        for (int i = 0; i < shape.length; i++) {
            intersect.add(Shape.intersect(ball.getShape(), shape[i]));
            if (intersect.get(i).getBoundsInLocal().getWidth() != -1) {
                System.out.println("Collided");
                if(a){
                    stopTimer();
                    a=false;
                    finalg.getChildren().add(ball.gameover_animation());


                    ball.game_over(ball.getGroup().getBoundsInParent().getCenterX(),ball.getGroup().getBoundsInParent().getCenterY());
                    tl.pause();
                }
                times=times+1;
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
                boolean t=true;
                boolean a=ball.getGroup().getBoundsInParent().getCenterY() <= ball.getPosY();
                if(a){
                    tl.play();
                }
                else {
                    tl.pause();
                    t=false;
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
        double c=-150;
        TriangleObstacle triangleObstacle = new TriangleObstacle(2, -130, 20, true, 250,ball.getPaint());
        CircleObstacle obs1 = new CircleObstacle(2, -130, 20, true, 100, 20);
        HorizontalLine horline = new HorizontalLine(2, -130, 20, true);
        Point ballpoint=new Point(ball.getGroup().getBoundsInParent().getCenterX(),ball.getGroup().getBoundsInParent().getCenterY(), "Ball");
        System.out.println(ballpoint);
        saveWork.setBallpoint(ballpoint);
        for(int i=0;i<obss.size();i++){
            if(Colli.get(i).getClass()==triangleObstacle.getClass()) {
                Point tripoint=new Point(obss.get(i).getTranslateX(), obss.get(i).getTranslateY()+c, "TriangleObstacle");
                saveWork.add(tripoint);


            }
            else if(Colli.get(i).getClass()==obs1.getClass()) {
                Point cirpoint=new Point(obss.get(i).getTranslateX(), obss.get(i).getTranslateY()+c, "CircleObstacle");
                saveWork.add(cirpoint);


            }
            else if(Colli.get(i).getClass()==horline.getClass()) {
                Point horpoint=new Point(obss.get(i).getTranslateX(), obss.get(i).getTranslateY()+c, "HorizontalLine");
                saveWork.add(horpoint);


            }


        }
        ObjectOutputStream out=null;
        try {
            out=new ObjectOutputStream(new FileOutputStream("out.txt"));
            out.writeObject(saveWork);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
    public void deserialize() throws IOException {
        ObjectInputStream in=null;
        try {
            in=new ObjectInputStream(new FileInputStream("out.txt"));
            saveWork=(SaveWork) in.readObject();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert in != null;
            in.close();
        }
    }



    public Scene loadGame(double width, double height) throws
            InterruptedException, IOException {
        deserialize();
        String yellow="FAE100";String purple="900DFF";String blue ="32DBF0";String pink="FF0181";
        Group finalg = new Group();
        ball = new Ball(purple,10,0,0,19);
        Group ballg = new Group();

        Group group1 = new Group();
        ballg2 = new Group();
        ballg.getChildren().add(ball.draw(width/2.0,saveWork.getBallpoint().getY()));

        ballg2.getChildren().add(ballg);
        ballg2.getChildren().add(ball.gameover_animation());
        ballg2.getChildren().add(group1);
        finalg.getChildren().add(ballg2);




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
                        down_by=3;
                        if(times==0) {
                            up(ball, ballg);
                        }
                }
            }
        });

        return loadscene;

    }

}
