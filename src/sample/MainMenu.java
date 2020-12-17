package sample;



import com.sun.javafx.webkit.theme.ScrollBarWidget;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;

import javafx.animation.*;
import javafx.geometry.Insets;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.scene.media.AudioClip;
import javafx.scene.text.FontPosture;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;


import javafx.stage.Stage;




public class MainMenu {
    static Stage primaryStage;
    Stage stage2;

    AudioClip buttonplay = new AudioClip(new File("src/sample/button.wav").toURI().toString());
    public MainMenu(){
        stage2=new Stage();
    }



    public Pane launch(){

        String yellow="FAE100";String purple="900DFF";String blue ="32DBF0";String pink="FF0181";
        int width=450;
        int height=600;
        int height1=770;

        CircleObstacle c=new CircleObstacle(0,0,3,true,53,7);
        CircleObstacle c2=new CircleObstacle(0,0,3,false,68,12);
        CircleObstacle c3=new CircleObstacle(0,0,3,true,88,15);
        CircleObstacle c4=new CircleObstacle(0,0,3,true,15,5);
        CircleObstacle c5=new CircleObstacle(0,0,3,false,15,5);
        Group a=c.draw();
        Group conc=c2.draw();
        Group conc2=c3.draw();
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

        btn1.setPrefWidth(87);
        btn1.setPrefHeight(87);

        btn2.setPrefWidth(80);
        btn2.setPrefHeight(80);

        btn3.setPrefWidth(80);
        btn3.setPrefHeight(80);


        btn1.setLayoutX(186);
        btn2.setLayoutX(100);
        btn3.setLayoutX(285);
        btn1.setLayoutY(246.8);
        btn2.setLayoutY(445);
        btn3.setLayoutY(445);
        a.setTranslateX(230);
        a.setTranslateY(290);
        conc.setTranslateX(230);
        conc.setTranslateY(290);
        conc2.setTranslateX(230);
        conc2.setTranslateY(290);


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

        Group logo=new Group(btn1,a, conc, conc2);
        logo.setTranslateX(5);


        pane.getChildren().add(logo);
        pane.getChildren().add(btn2);
        pane.getChildren().add(btn3);



        pane.getChildren().add(labels);
        pane.getChildren().add(labels2);

        File f1 = new File("play.png");

        Image img = new Image("file:///"+f1.getAbsolutePath().replace("\\","/"));
        ImageView view = new ImageView(img);
        view.setFitHeight(87);
        view.setPreserveRatio(true);
        btn1.setGraphic(view);
        btn1.setPadding(javafx.geometry.Insets.EMPTY);

        File f2 = new File("save.png");

        Image img2 = new Image("file:///"+f2.getAbsolutePath().replace("\\","/"));
        ImageView view2 = new ImageView(img2);
        view2.setFitHeight(80);
        view2.setPreserveRatio(true);
        btn2.setGraphic(view2);
        btn2.setPadding(javafx.geometry.Insets.EMPTY);

        File f3 = new File("file.png");

        Image img3 = new Image("file:///"+f3.getAbsolutePath().replace("\\","/"));
        ImageView view3 = new ImageView(img3);
        view3.setFitHeight(80);
        view3.setPreserveRatio(true);
        btn3.setGraphic(view3);
        btn3.setPadding(Insets.EMPTY);
        pane.setStyle("-fx-background-color: #333333");
        btn1.setId("btn1");
        btn2.setId("btn2");
        btn3.setId("btn3");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                Game game = new Game();

//                    Pane pane=game.Start(width,height1);
                    try {
                        primaryStage.setScene(game.Start(width,height1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    stage2.setScene(game.Start(width,height1));
//                  stage2.show();



            }
        });
        Pane loadpane=loadscreen();
        Scene loadscene=new Scene(loadpane, width, height1);

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                primaryStage.setScene(loadscene);

            }
        });
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                primaryStage.close();

            }
        });
        return pane;





    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        MainMenu.primaryStage = primaryStage;
    }
    public Pane loadscreen(){
        int width=450;
        int height=600;
        int height1=770;

        Pane savepane=new Pane();
        savepane.setStyle("-fx-background-color: #272727");

        javafx.scene.control.Button btn4 = new javafx.scene.control.Button("SLOT 1");
        javafx.scene.control.Button btn5 = new javafx.scene.control.Button("SLOT 2");
        javafx.scene.control.Button btn6 = new javafx.scene.control.Button("SLOT 3");
        javafx.scene.control.Button btn7 = new javafx.scene.control.Button("SLOT 4");
        javafx.scene.control.Button btn8 = new javafx.scene.control.Button("BACK TO MAINMENU");
        btn4.setId("btn4");
        btn5.setId("btn5");
        btn6.setId("btn6");
        btn7.setId("btn7");



        savepane.getChildren().add(btn8);

        Font font = Font.font(
                "Franklin Gothic Medium", FontPosture.REGULAR, 20);

        File directory = new File("./allsaves/");
        String[] flist = directory.list();

        if (flist == null) {
            System.out.println("Empty directory.");
        }
        for(int i=0;i<flist.length;i++){
            if(flist[i].equals("out1.txt")){
                savepane.getChildren().add(btn4);


            }
            else if(flist[i].equals("out2.txt")){
                savepane.getChildren().add(btn5);

            }
            else if(flist[i].equals("out3.txt")){
                savepane.getChildren().add(btn6);

            }
            else if(flist[i].equals("out4.txt")){
                savepane.getChildren().add(btn7);

            }
        }



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

        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                Game game = new Game();
                try {
                    primaryStage.setScene(game.loadGame(width,height1,1));

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                Game game = new Game();
                try {
                    primaryStage.setScene(game.loadGame(width,height1,2));

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        btn6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                Game game = new Game();
                try {
                    primaryStage.setScene(game.loadGame(width,height1,3));

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        btn7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                Game game = new Game();
                try {
                    primaryStage.setScene(game.loadGame(width,height1,4));

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        btn8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonplay.play();
                Scene mainscene=new Scene(launch(),width,height);
                File f = new File("stylesheet.css");
                mainscene.getStylesheets().clear();
                mainscene.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));

                primaryStage.setScene(mainscene);


            }
        });



        return savepane;

    }

}

