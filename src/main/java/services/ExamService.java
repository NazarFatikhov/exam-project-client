package services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.Exam;

public class ExamService {

    public String getCurrentExamJsonAsString(String oldExamJsonString, Exam currentExam){
        JsonParser parser = new JsonParser();
        JsonObject examJson = parser.parse(oldExamJsonString).getAsJsonObject();
        examJson.remove("date");
        examJson.addProperty("date", currentExam.getDate());
        examJson.get("student").getAsJsonObject().remove("email");
        examJson.get("student").getAsJsonObject().addProperty("email", currentExam.getStudentEmail());
        examJson.get("teacher").getAsJsonObject().remove("email");
        examJson.get("teacher").getAsJsonObject().addProperty("email", currentExam.getTeacherEmail());
        return examJson.toString();
    }
}
