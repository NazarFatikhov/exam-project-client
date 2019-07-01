package services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dto.Exam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamMapper {

    public List<Exam> mapList(JsonArray jsonElements) {

        List<Exam> exams = new ArrayList<>();

        for (JsonElement element : jsonElements) {
            JsonObject studentJson = element.getAsJsonObject().get("student").getAsJsonObject();
            JsonObject typeJson = element.getAsJsonObject().get("type").getAsJsonObject();
            String dateJson = element.getAsJsonObject().get("date").getAsString();
            Integer totalScore = element.getAsJsonObject().get("totalScore").getAsInt();

            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH.mm");
            Date date = new Date();
            date.setTime(Long.parseLong(dateJson));
            String dateStr = format.format(date);

            Exam exam = Exam.builder()
                    .studentEmail(studentJson.get("email").getAsString())
                    .studentNameAndSurname(studentJson.get("name").getAsString() + " " + studentJson.get("surname").getAsString())
                    .subjectTypeName(typeJson.get("typeName").getAsString() + " " + typeJson.get("subjectName").getAsString())
                    .date(dateStr)
                    .totalScore(totalScore)
                    .build();

            exams.add(exam);
        }

        return exams;
    }
}
