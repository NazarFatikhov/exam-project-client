package sample;

import com.google.gson.*;
import dto.Exam;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import services.ExamMapper;
import services.HttpUrlConnection;

import javax.annotation.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Controller {

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
    private TableView<Exam> examsTable;

    @FXML
    public void initialize() {
        updateExamsFromServer();
    }

    @FXML
    private List<String> updateExamsFromServer(){
        try {
            examsTable.getItems().clear();

            HttpUrlConnection con = new HttpUrlConnection();
            String response = con.sendGet("http://localhost:8080/api/exam");
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(response).getAsJsonArray();

            ExamMapper examMapper = new ExamMapper();
            List<Exam> exams = examMapper.mapList(array);

            studentEmail.setCellValueFactory(new PropertyValueFactory<Exam, String>("studentEmail"));
            studentNameAndSurname.setCellValueFactory(new PropertyValueFactory<Exam, String>("studentNameAndSurname"));
            subjectTypeName.setCellValueFactory(new PropertyValueFactory<Exam, String>("subjectTypeName"));
            date.setCellValueFactory(new PropertyValueFactory<Exam, String>("date"));
            score.setCellValueFactory(new PropertyValueFactory<Exam, String>("totalScore"));

            examsTable.getItems().addAll(exams);

        } catch (IOException e) {
            System.out.println("IOException");
        }
        return null;
    }

}
