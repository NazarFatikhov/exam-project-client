package services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import models.EditButton;
import models.Exam;
import models.ExamTask;

import java.util.ArrayList;
import java.util.List;

public class ExamMapper {

    public List<Exam> mapList(JsonArray jsonElements) {

        List<Exam> exams = new ArrayList<>();

        for (JsonElement element : jsonElements) {
            JsonObject studentJson = element.getAsJsonObject().get("student").getAsJsonObject();
            JsonObject teacherJson = element.getAsJsonObject().get("teacher").getAsJsonObject();
            JsonObject typeJson = element.getAsJsonObject().get("type").getAsJsonObject();
            String dateJson = element.getAsJsonObject().get("date").getAsString();
            Integer totalScore = element.getAsJsonObject().get("totalScore").getAsInt();
            Long examId = element.getAsJsonObject().get("id").getAsLong();

            TaskMapper taskMapper = new TaskMapper();
            List<ExamTask> examTasks = taskMapper.mapExamTaskList(element.getAsJsonObject().get("tasks").getAsJsonArray());

            Exam exam = Exam.builder()
                    .id(examId)
                    .studentEmail(studentJson.get("email").getAsString())
                    .teacherEmail(teacherJson.get("email").getAsString())
                    .studentNameAndSurname(studentJson.get("name").getAsString() + " " + studentJson.get("surname").getAsString())
                    .subjectTypeName(typeJson.get("typeName").getAsString() + " " + typeJson.get("subjectName").getAsString())
                    .date(dateJson)
                    .totalScore(totalScore)
                    .examTasks(examTasks)
                    .editButton(new EditButton("Show"))
                    .build();

            exams.add(exam);
        }

        return exams;
    }

    public Exam map(JsonElement element){
        JsonObject studentJson = element.getAsJsonObject().get("student").getAsJsonObject();
        JsonObject teacherJson = element.getAsJsonObject().get("teacher").getAsJsonObject();
        JsonObject typeJson = element.getAsJsonObject().get("type").getAsJsonObject();
        String dateJson = element.getAsJsonObject().get("date").getAsString();
        Integer totalScore = element.getAsJsonObject().get("totalScore").getAsInt();
        Long examId = element.getAsJsonObject().get("id").getAsLong();

        TaskMapper taskMapper = new TaskMapper();
        List<ExamTask> examTasks = taskMapper.mapExamTaskList(element.getAsJsonObject().get("tasks").getAsJsonArray());

        Exam exam = Exam.builder()
                .id(examId)
                .studentEmail(studentJson.get("email").getAsString())
                .teacherEmail(teacherJson.get("email").getAsString())
                .studentNameAndSurname(studentJson.get("name").getAsString() + " " + studentJson.get("surname").getAsString())
                .subjectTypeName(typeJson.get("typeName").getAsString() + " " + typeJson.get("subjectName").getAsString())
                .date(dateJson)
                .totalScore(totalScore)
                .examTasks(examTasks)
                .editButton(new EditButton("Show"))
                .build();

        return exam;
    }
}
