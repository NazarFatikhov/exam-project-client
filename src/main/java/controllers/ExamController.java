package controllers;

import com.google.gson.JsonParser;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import models.Exam;
import services.ExamMapper;
import services.ExamService;
import services.HttpUrlConnection;

import java.io.IOException;

public class ExamController {

    private static final double CELL_WIDTH = 50;
    private static final double FONT_SIZE = 20;

    private Exam exam;

    @FXML
    private Label titleLabel;
    @FXML
    private TextField studentEmailField;
    @FXML
    private TextField teacherEmailField;
    @FXML
    private TextField examDateField;
    @FXML
    private Label examTotalScoreLabel;
    @FXML
    private Label examSubjectTypeName;
    @FXML
    private GridPane scoresGridPane;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;

    public void initialize() {
    }

    public void initDate(Exam exam) {
        this.exam = exam;
        showExamFromServer();
    }

    @FXML
    public void showExamFromServer() {
        titleLabel.setText("Exam N " + exam.getId());
        studentEmailField.setText(exam.getStudentEmail());
        teacherEmailField.setText(exam.getTeacherEmail());
        examDateField.setText(exam.getDate());
        examTotalScoreLabel.setText(exam.getTotalScore().toString());
        examSubjectTypeName.setText(exam.getSubjectTypeName());

        for (int i = 0; i < exam.getExamTasks().size(); i++) {
            Label numLabel = new Label();
            numLabel.setPrefWidth(CELL_WIDTH);
            numLabel.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, FONT_SIZE));
            numLabel.setAlignment(Pos.BASELINE_CENTER);
            numLabel.setText(exam.getExamTasks().get(i).getTaskNum().toString());

            Label scoreLabel = new Label();
            scoreLabel.setPrefWidth(CELL_WIDTH);
            scoreLabel.setFont(Font.font(FONT_SIZE));
            scoreLabel.setAlignment(Pos.BASELINE_CENTER);
            scoreLabel.setText(exam.getExamTasks().get(i).getScore().toString()
                    + "/"
                    + exam.getExamTasks().get(i).getMaxScore().toString());

            int columnInd = i % 10;
            int numRowInd = (i / 10) * 2;
            int scoreRowInd = (i / 10) * 2 + 1;

            scoresGridPane.add(numLabel, columnInd, numRowInd);
            scoresGridPane.add(scoreLabel, columnInd, scoreRowInd);
        }
    }

    public void updateExam() {
        updateExamDate();
        updateExamStudentEmail();
        updateExamTeacherEmail();
    }

    public void updateFields() {
        examDateField.setText(exam.getDate());
        studentEmailField.setText(exam.getStudentEmail());
        teacherEmailField.setText(exam.getTeacherEmail());
    }

    @FXML
    public void updateExamDate() {
        exam.setDate(examDateField.getText());
    }

    @FXML
    public void updateExamStudentEmail() {
        exam.setStudentEmail(studentEmailField.getText());
    }

    @FXML
    public void updateExamTeacherEmail() {
        exam.setTeacherEmail(teacherEmailField.getText());
    }

    @FXML
    public Exam updateExamInServer() {
        updateExam();

        HttpUrlConnection connection = new HttpUrlConnection();
        try {
            String oldExamStr = connection.sendGet("http://localhost:8080/api/exam/" + exam.getId());
            ExamService examService = new ExamService();
            String currentExamJsonAsString = examService.getCurrentExamJsonAsString(oldExamStr, exam);
            String response = connection.sendPut("http://localhost:8080/api/exam/" + exam.getId(), currentExamJsonAsString);

            JsonParser parser = new JsonParser();
            ExamMapper examMapper = new ExamMapper();
            Exam newExam = examMapper.map(parser.parse(response));
            setExam(newExam);
            updateFields();

        } catch (IOException e) {
            System.out.println("IOException");
        }
        return null;
    }

    @FXML
    public void deleteExamInServer() {
        try {

            HttpUrlConnection connection = new HttpUrlConnection();
            connection.sentDelete("http://localhost:8080/api/exam/" + exam.getId());

            Stage stage = (Stage) deleteButton.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }


}
