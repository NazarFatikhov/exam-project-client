package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.text.TabableView;
import javax.swing.text.TableView;
import java.awt.event.ActionEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        primaryStage.setTitle("Exam (Admin)");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.setMinWidth(734);
        primaryStage.setMinHeight(664);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}