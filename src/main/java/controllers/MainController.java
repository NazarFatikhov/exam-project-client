package controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.EditButton;
import models.Exam;
import services.ExamMapper;
import services.HttpUrlConnection;

import java.io.IOException;
import java.util.List;

public class MainController {

    private static final double NORMAL_WINDOW_WIDTH = 734;
    private static final double NORMAL_WINDOW_HEIGHT = 634;

    @FXML
    private VBox mainPane;

    @FXML
    private TableColumn<Exam, String> studentNameAndSurname;
    @FXML
    private TableColumn<Exam, String> subjectTypeName;
    @FXML
    private TableColumn<Exam, String> date;
    @FXML
    private TableColumn<Exam, String> score;
    @FXML
    private TableColumn<Exam, String> studentEmail;
    @FXML
    private TableColumn<Exam, EditButton> editButton;

    @FXML
    private TableView<Exam> examsTable;

    @FXML
    public void initialize() {
        updateExamsFromServer();
    }

    @FXML
    public List<String> updateExamsFromServer(){
        try {
            examsTable.getItems().clear();

            HttpUrlConnection con = new HttpUrlConnection();
            String response = con.sendGet("http://localhost:8080/api/exam");
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(response).getAsJsonArray();

            ExamMapper examMapper = new ExamMapper();
            List<Exam> exams = examMapper.mapList(array);

            for(Exam exam : exams){
                exam.getEditButton().setOnAction((event -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/exam.fxml"));
                        Parent root = loader.load();
                        ExamController examController = loader.getController();
                        examController.initDate(exam);
                        Stage stage = new Stage();
                        stage.setTitle("Exam # " + exam.getId());
                        stage.setScene(new Scene(root, NORMAL_WINDOW_HEIGHT, NORMAL_WINDOW_WIDTH));
                        stage.setMinWidth(NORMAL_WINDOW_WIDTH);
                        stage.setMinHeight(NORMAL_WINDOW_HEIGHT);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
            }

            studentEmail.setCellValueFactory(new PropertyValueFactory<Exam, String>("studentEmail"));
            studentNameAndSurname.setCellValueFactory(new PropertyValueFactory<Exam, String>("studentNameAndSurname"));
            subjectTypeName.setCellValueFactory(new PropertyValueFactory<Exam, String>("subjectTypeName"));
            date.setCellValueFactory(new PropertyValueFactory<Exam, String>("date"));
            score.setCellValueFactory(new PropertyValueFactory<Exam, String>("totalScore"));
            editButton.setCellValueFactory(new PropertyValueFactory<>("editButton"));

            examsTable.getItems().addAll(exams);

        } catch (IOException e) {
            System.out.println("IOException");
        }
        return null;
    }

}
