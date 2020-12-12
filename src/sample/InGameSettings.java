package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
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

    public void saveGame() throws IOException {


    }

    public void resumeGame() throws IOException {
        SaveWork safe=deserialize();
        safe.display();


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


        pane.getChildren().add(btn4);
        pane.getChildren().add(btn5);
        pane.getChildren().add(btn6);
        pane.getChildren().add(btn7);

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

        btn4.setFont(font);
        btn4.setTextFill(Color.WHITE);
        btn5.setFont(font);
        btn5.setTextFill(Color.WHITE);
        btn6.setFont(font);
        btn6.setTextFill(Color.WHITE);
        btn7.setFont(font);
        btn7.setTextFill(Color.WHITE);


        btn4.setPrefWidth(180);
        btn4.setPrefHeight(40);
        btn5.setPrefWidth(180);
        btn5.setPrefHeight(40);
        btn6.setPrefWidth(180);
        btn6.setPrefHeight(40);
        btn7.setPrefWidth(180);
        btn7.setPrefHeight(40);


        MainMenu mainMenu = new MainMenu();
        Stage stage = MainMenu.getPrimaryStage();

        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
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
                try {
                    saveGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return pane;

    }

    public SaveWork deserialize() throws IOException {
        ObjectInputStream in = null;
        SaveWork Asave = null;
        try {
            in = new ObjectInputStream(new FileInputStream("out.txt"));
            Asave = (SaveWork) in.readObject();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert in != null;
            in.close();
        }
        return Asave;


    }
}
