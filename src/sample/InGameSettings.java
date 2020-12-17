package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;

public class InGameSettings {
    private Pane pane;
    private Scene scene;
    private Game game;
    private SaveWork saveWork;

    public SaveWork getSaveWork() {
        return saveWork;
    }

    public void setSaveWork(SaveWork saveWork) {
        this.saveWork = saveWork;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public InGameSettings(Pane pane, Scene scene) {
        this.scene = scene;
        this.pane = pane;
    }
    AudioClip buttonplay = new AudioClip(new File("src/sample/button.wav").toURI().toString());

    public void saveGame() throws IOException {
        MainMenu mainMenu = new MainMenu();
        Stage stage = MainMenu.getPrimaryStage();
        Pane savepane=savescreen();
        Scene savescene=new Scene(savepane,450,770);
        stage.setScene(savescene);



    }

    public void resumeGame() throws IOException {
        MainMenu mainMenu = new MainMenu();
        Stage stage = MainMenu.getPrimaryStage();
        saveWork.removeall();
        for(int i=0;i<game.getColli().size();i++){
            game.getColli().get(i).resume();
        }

        game.setisPaused(false);

        stage.setScene(scene);


    }

    public void exitGame() {


    }

    public Pane settings() {
        pane.setStyle("-fx-background-color: #272727");

        javafx.scene.control.Button btn4 = new javafx.scene.control.Button("RESUME");
        javafx.scene.control.Button btn5 = new javafx.scene.control.Button("SAVE GAME");
        javafx.scene.control.Button btn6 = new javafx.scene.control.Button("RESTART");
        javafx.scene.control.Button btn7 = new javafx.scene.control.Button("EXIT");
        btn4.setId("btn4");
        btn5.setId("btn5");
        btn6.setId("btn6");
        btn7.setId("btn7");
        CircleObstacle c4=new CircleObstacle(0,0,3,true,15,5);
        CircleObstacle c5=new CircleObstacle(0,0,3,false,15,5);
        Group o1=c4.draw();
        Group o2=c5.draw();
        Pane pane=new Pane();
        pane.setStyle("-fx-background-color: #333333");
        javafx.scene.control.Button btn1= new javafx.scene.control.Button();
        javafx.scene.control.Button btn2= new javafx.scene.control.Button();
        javafx.scene.control.Button btn3= new javafx.scene.control.Button();

        javafx.scene.control.Label label = new javafx.scene.control.Label("C");

        javafx.scene.text.Font font = Font.font("Arial, Helvetica, sans-serif", FontPosture.REGULAR, 45);
        label.setFont(font);

        label.setTextFill(Color.WHITE);

        javafx.scene.control.Label label2 = new javafx.scene.control.Label("L");
        label2.setFont(font);

        label2.setTextFill(Color.WHITE);

        javafx.scene.control.Label label3 = new javafx.scene.control.Label("R");


        label3.setFont(font);

        label3.setTextFill(Color.WHITE);

        javafx.scene.control.Label label4 = new javafx.scene.control.Label("S");


        label4.setFont(font);

        label4.setTextFill(Color.WHITE);

        javafx.scene.control.Label label5 = new javafx.scene.control.Label("W");


        label5.setFont(font);

        label5.setTextFill(Color.WHITE);

        javafx.scene.control.Label label6 = new javafx.scene.control.Label("I");


        label6.setFont(font);

        label6.setTextFill(Color.WHITE);

        javafx.scene.control.Label label7 = new javafx.scene.control.Label("T");


        label7.setFont(font);

        label7.setTextFill(Color.WHITE);

        javafx.scene.control.Label label8 = new javafx.scene.control.Label("C");


        label8.setFont(font);

        label8.setTextFill(Color.WHITE);
        javafx.scene.control.Label label9 = new Label("H");


        label9.setFont(font);

        label9.setTextFill(Color.WHITE);




        label.setTranslateX(175);
        label.setTranslateY(25);
        o1.setTranslateX(223);
        o1.setTranslateY(59);
        label2.setTranslateX(245);
        label2.setTranslateY(25);
        o2.setTranslateX(285);
        o2.setTranslateY(58);
        label3.setTranslateX(305);
        label3.setTranslateY(25);
        label4.setTranslateX(150);
        label4.setTranslateY(65);
        label5.setTranslateX(175);
        label5.setTranslateY(65);
        label6.setTranslateX(220);
        label6.setTranslateY(65);
        label7.setTranslateX(240);
        label7.setTranslateY(65);
        label8.setTranslateX(268);
        label8.setTranslateY(65);
        label9.setTranslateX(300);
        label9.setTranslateY(65);
        Group labels=new Group(label,label2, label3, o1, o2);
        Group labels2=new Group(label4,label5, label6, label7, label8, label9);
        labels.setTranslateX(-22);
        labels2.setTranslateY(8);
        labels2.setTranslateX(-5);

        Font font1 = Font.font(
                "Franklin Gothic Medium", FontPosture.REGULAR, 20);

        VerticalLine obstacle3 = new VerticalLine(0, 700, 4, true, 75);
        pane.getChildren().add(obstacle3.draw());
        btn4.setLayoutX(140);
        btn4.setLayoutY(225);
        btn5.setLayoutX(140);
        btn5.setLayoutY(300);
        btn6.setLayoutX(140);
        btn6.setLayoutY(375);
        btn7.setLayoutX(140);
        btn7.setLayoutY(450);

        btn4.setFont(font1);
        btn4.setTextFill(Color.WHITE);
        btn5.setFont(font1);
        btn5.setTextFill(Color.WHITE);
        btn6.setFont(font1);
        btn6.setTextFill(Color.WHITE);
        btn7.setFont(font1);
        btn7.setTextFill(Color.WHITE);


        btn4.setPrefWidth(180);
        btn4.setPrefHeight(40);
        btn5.setPrefWidth(180);
        btn5.setPrefHeight(40);
        btn6.setPrefWidth(180);
        btn6.setPrefHeight(40);
        btn7.setPrefWidth(180);
        btn7.setPrefHeight(40);


        pane.getChildren().add(btn4);
        pane.getChildren().add(btn5);
        pane.getChildren().add(btn6);
        pane.getChildren().add(btn7);
        pane.getChildren().add(labels);
        pane.getChildren().add(labels2);




        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                try {
                    resumeGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                try {
                    saveGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btn6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                try {
                    restart();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        MainMenu menus=new MainMenu();
        Pane menupane=menus.launch();
        Scene menuscene=new Scene(menupane, 450,600);
        File f = new File("stylesheet.css");
        menuscene.getStylesheets().clear();
        menuscene.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));
        btn7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                game.stopTimer();
                MainMenu.getPrimaryStage().setScene(menuscene);
            }
        });
        return pane;

    }

    public void serialize(int i) throws IOException {
        ObjectOutputStream out=null;
        try {
            File file=new File("./allsaves/out"+i+".txt");
            if(file.delete()){
                out=new ObjectOutputStream(new FileOutputStream("./allsaves/out"+i+".txt"));
                out.writeObject(saveWork);

            }
            else {
                System.out.println("failed");
                out = new ObjectOutputStream(new FileOutputStream("./allsaves/out" + i + ".txt"));
                out.writeObject(saveWork);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert out != null;
            out.close();
        }
    }


    public Pane savescreen(){
        Pane savepane=new Pane();
        savepane.setStyle("-fx-background-color: #272727");

        javafx.scene.control.Button btn4 = new javafx.scene.control.Button("SLOT 1");
        javafx.scene.control.Button btn5 = new javafx.scene.control.Button("SLOT 2");
        javafx.scene.control.Button btn6 = new javafx.scene.control.Button("SLOT 3");
        javafx.scene.control.Button btn7 = new javafx.scene.control.Button("SLOT 4");
        javafx.scene.control.Button btn8 = new javafx.scene.control.Button("BACK TO GAME");
        btn4.setId("btn4");
        btn5.setId("btn5");
        btn6.setId("btn6");
        btn7.setId("btn7");


        savepane.getChildren().add(btn4);
        savepane.getChildren().add(btn5);
        savepane.getChildren().add(btn6);
        savepane.getChildren().add(btn7);
        savepane.getChildren().add(btn8);

        Font font = Font.font(
                "Franklin Gothic Medium", FontPosture.REGULAR, 20);


        btn4.setLayoutX(140);
        btn4.setLayoutY(225);
        btn5.setLayoutX(140);
        btn5.setLayoutY(300);
        btn6.setLayoutX(140);
        btn6.setLayoutY(375);
        btn7.setLayoutX(140);
        btn7.setLayoutY(450);

        btn8.setLayoutX(140);
        btn8.setLayoutY(540);

        btn4.setFont(font);
        btn4.setTextFill(Color.BLACK);
        btn5.setFont(font);
        btn5.setTextFill(Color.BLACK);
        btn6.setFont(font);
        btn6.setTextFill(Color.BLACK);
        btn7.setFont(font);
        btn7.setTextFill(Color.BLACK);
        btn8.setFont(font);


        btn4.setPrefWidth(180);
        btn4.setPrefHeight(40);
        btn5.setPrefWidth(180);
        btn5.setPrefHeight(40);
        btn6.setPrefWidth(180);
        btn6.setPrefHeight(40);
        btn7.setPrefWidth(180);
        btn7.setPrefHeight(40);
        btn8.setPrefWidth(180);
        btn8.setPrefHeight(40);
        MainMenu mainMenu = new MainMenu();
        Stage stage = MainMenu.getPrimaryStage();

        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                try {
                    serialize(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                try {
                    serialize(2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btn6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                try {
                    serialize(3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btn7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                try {
                    serialize(4);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btn8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                for(int i=0;i<game.getColli().size();i++){
                    game.getColli().get(i).resume();
                }
                game.setisPaused(false);
                stage.setScene(scene);


            }
        });

        return savepane;
    }
    public void restart() throws InterruptedException {
        Game game1=new Game();
        game.stopTimer();
        this.game=game1;
        this.scene=game1.Start(450,770);

        MainMenu.getPrimaryStage().setScene(scene);

    }
    public Pane Gameover(){
        Pane overpane=new Pane();
        overpane.setStyle("-fx-background-color: #333333");
        VerticalLine obstacle3 = new VerticalLine(0, 700, 4, true, 75);
        overpane.getChildren().add(obstacle3.draw());
        Rectangle rect1 = new Rectangle(0,128+170,450,45);
        rect1.setFill(Color.web("#606060"));
        overpane.getChildren().add(rect1);
        javafx.scene.control.Label label11 = new javafx.scene.control.Label("GAME OVER");
        javafx.scene.control.Label label12 = new javafx.scene.control.Label("SCORE:");
        javafx.scene.control.Label label13 = new javafx.scene.control.Label(Integer.toString(game.getBall().getStarsCollected()));
        overpane.getChildren().add(label13);
        overpane.getChildren().add(label12);
        Button exit=new Button();
        Button continue2=new Button();
        Button restart=new Button();
        exit.setId("btn1");
        restart.setId("btn2");
        continue2.setId("btn3");
        CircleObstacle c4=new CircleObstacle(0,0,3,true,15,5);
        CircleObstacle c5=new CircleObstacle(0,0,3,false,15,5);
        Group o1=c4.draw();
        Group o2=c5.draw();
        Pane pane=new Pane();
        pane.setStyle("-fx-background-color: #333333");
        javafx.scene.control.Button btn1= new javafx.scene.control.Button();
        javafx.scene.control.Button btn2= new javafx.scene.control.Button();
        javafx.scene.control.Button btn3= new javafx.scene.control.Button();

        javafx.scene.control.Label label = new javafx.scene.control.Label("C");

        javafx.scene.text.Font font = Font.font("Arial, Helvetica, sans-serif", FontPosture.REGULAR, 45);
        label.setFont(font);

        label.setTextFill(Color.WHITE);

        javafx.scene.control.Label label2 = new javafx.scene.control.Label("L");
        label2.setFont(font);

        label2.setTextFill(Color.WHITE);

        javafx.scene.control.Label label3 = new javafx.scene.control.Label("R");


        label3.setFont(font);

        label3.setTextFill(Color.WHITE);

        javafx.scene.control.Label label4 = new javafx.scene.control.Label("S");


        label4.setFont(font);

        label4.setTextFill(Color.WHITE);

        javafx.scene.control.Label label5 = new javafx.scene.control.Label("W");


        label5.setFont(font);

        label5.setTextFill(Color.WHITE);

        javafx.scene.control.Label label6 = new javafx.scene.control.Label("I");


        label6.setFont(font);

        label6.setTextFill(Color.WHITE);

        javafx.scene.control.Label label7 = new javafx.scene.control.Label("T");


        label7.setFont(font);

        label7.setTextFill(Color.WHITE);

        javafx.scene.control.Label label8 = new javafx.scene.control.Label("C");


        label8.setFont(font);

        label8.setTextFill(Color.WHITE);
        javafx.scene.control.Label label9 = new Label("H");


        label9.setFont(font);

        label9.setTextFill(Color.WHITE);




        label.setTranslateX(175);
        label.setTranslateY(25);
        o1.setTranslateX(223);
        o1.setTranslateY(59);
        label2.setTranslateX(245);
        label2.setTranslateY(25);
        o2.setTranslateX(285);
        o2.setTranslateY(58);
        label3.setTranslateX(305);
        label3.setTranslateY(25);
        label4.setTranslateX(150);
        label4.setTranslateY(65);
        label5.setTranslateX(175);
        label5.setTranslateY(65);
        label6.setTranslateX(220);
        label6.setTranslateY(65);
        label7.setTranslateX(240);
        label7.setTranslateY(65);
        label8.setTranslateX(268);
        label8.setTranslateY(65);
        label9.setTranslateX(300);
        label9.setTranslateY(65);
        Group labels=new Group(label,label2, label3, o1, o2);
        Group labels2=new Group(label4,label5, label6, label7, label8, label9);
        labels.setTranslateX(-22);
        labels2.setTranslateY(8);
        labels2.setTranslateX(-5);
        overpane.getChildren().add(labels);
        overpane.getChildren().add(labels2);





        javafx.scene.text.Font font1 = Font.font("Franklin Gothic Medium", FontPosture.REGULAR, 45);
        javafx.scene.text.Font font2 = Font.font("Franklin Gothic Medium", FontWeight.BOLD, FontPosture.REGULAR, 45);
        javafx.scene.text.Font font3 = Font.font("Franklin Gothic Medium", FontWeight.BOLD, FontPosture.REGULAR, 45);
        label12.setFont(font2);
        label13.setFont(font3);
        double move=170;
        label11.setTextFill(Color.WHITE);
        label12.setTextFill(Color.WHITE);
        label13.setTextFill(Color.WHITE);
        label11.setFont(font1);
        label11.setTranslateX(110);
        label11.setTranslateY(25+move);
        Rectangle rect = new Rectangle(0,25+move+3,450,45);
        rect.setFill(Color.web("#FFA500"));
        overpane.getChildren().add(rect);

        label12.setTranslateX(125);
        label12.setTranslateY(125+move);
        label13.setTranslateX(285);
        label13.setTranslateY(125+move);

        File f3 = new File("exit.png");

        Image img3 = new Image("file:///"+f3.getAbsolutePath().replace("\\","/"));
        ImageView view3 = new ImageView(img3);
        view3.setFitHeight(82);
        view3.setPreserveRatio(true);
        exit.setGraphic(view3);
        exit.setPadding(Insets.EMPTY);
        overpane.getChildren().add(label11);

        File f2 = new File("resart.png");

        Image img2 = new Image("file:///"+f2.getAbsolutePath().replace("\\","/"));
        ImageView view2 = new ImageView(img2);
        view2.setFitHeight(82);
        view2.setPreserveRatio(true);
        restart.setGraphic(view2);
        restart.setPadding(javafx.geometry.Insets.EMPTY);

        File f4 = new File("Playbutton.png");

        Image img4 = new Image("file:///"+f4.getAbsolutePath().replace("\\","/"));
        ImageView view4 = new ImageView(img4);
        view4.setFitHeight(80);
        view4.setPreserveRatio(true);
        continue2.setGraphic(view4);
        continue2.setPadding(javafx.geometry.Insets.EMPTY);
        CircleObstacle c=new CircleObstacle(450/2.0-4.5,400+move-50,3,true,48,9);
        CircleObstacle c2=new CircleObstacle(450/2.0-4.5,400+move-50,3,false,64,12);
        CircleObstacle c3=new CircleObstacle(450/2.0-4.5,400+move-50,3,true,84,15);
        Star s = new Star("gg",450/2.0-4.5,400+move-50);
        overpane.getChildren().add(c.draw());overpane.getChildren().add(c2.draw());overpane.getChildren().add(c3.draw());overpane.getChildren().add(s.draw());
        overpane.getChildren().add(restart);
        overpane.getChildren().add(exit);
        if(game.getBall().getStarsCollected()>=10) {
            overpane.getChildren().add(continue2);
        }

        restart.setLayoutX(30);
        restart.setLayoutY(350.8+move-90);
        exit.setLayoutX(326);
        exit.setLayoutY(350.8+move-90);
        continue2.setLayoutX(180);
        continue2.setLayoutY(400+move-90);

        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    restart();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        MainMenu menus=new MainMenu();
        Pane menupane=menus.launch();
        Scene menuscene=new Scene(menupane, 450,600);

        File f = new File("stylesheet.css");
        menuscene.getStylesheets().clear();
        menuscene.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.stopTimer();
                MainMenu.getPrimaryStage().setScene(menuscene);
            }
        });
        continue2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               // game.stopTimer();
                game.getBall().setStarsCollected(game.getBall().getStarsCollected()-10);
                game.toberemoved.getGroup().setOpacity(0);
                MainMenu.getPrimaryStage().setScene(scene);
                game.getBall().getShape().setOpacity(10);
                game.revive(true);
               // game.Timer().start();
            }
        });
        return overpane;

    }



}
