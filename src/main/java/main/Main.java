package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final double NORMAL_WINDOW_WIDTH = 820;
    private static final double NORMAL_WINDOW_HEIGHT = 634;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        primaryStage.setTitle("Exam (Admin)");
        primaryStage.setScene(new Scene(root, NORMAL_WINDOW_HEIGHT, NORMAL_WINDOW_WIDTH));
        primaryStage.setMinWidth(NORMAL_WINDOW_WIDTH);
        primaryStage.setMinHeight(NORMAL_WINDOW_HEIGHT);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}