package sample;
import java.io.*;


import javafx.application.Application;
import javafx.scene.*;

import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;

import java.io.FileNotFoundException;



public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception, FileNotFoundException {
        String yellow="FAE100";String purple="900DFF";String blue ="32DBF0";String pink="FF0181";
        int width=450;
        int height=600;
        int height1=770;

        MainMenu mainMenu=new MainMenu();
        MainMenu.setPrimaryStage(primaryStage);
        Pane pan1=mainMenu.launch();
        Scene scene = new Scene(pan1,width,height);
        File f = new File("stylesheet.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));

        primaryStage.setScene(scene);
        scene.setFill(Color.web("272727"));
        primaryStage.setTitle("Color-Switch");
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Application is being Closed Thanks For using it.");
            }
        });

    }

    public static void main (String[] args) throws Exception {
        Main main = new Main() ;
        main.launch(args);
        CircleObstacle obs1 = new CircleObstacle(2, -130, 20, true, 100, 20);
        Obstacle obs2=(Obstacle)obs1;


    }
}
