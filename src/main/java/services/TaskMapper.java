package services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import models.ExamTask;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {

    public List<ExamTask> mapExamTaskList(JsonArray jsonArray){
        List<ExamTask> examTasks = new ArrayList<>();

        for(JsonElement jsonElement : jsonArray){
            JsonObject typeTask = jsonElement.getAsJsonObject().get("typeTask").getAsJsonObject();

            ExamTask examTask = ExamTask.builder()
                    .taskNum(typeTask.get("taskNumber").getAsInt())
                    .maxScore(typeTask.get("maxScore").getAsInt())
                    .score(jsonElement.getAsJsonObject().get("score").getAsInt())
                    .build();

            examTasks.add(examTask);
        }

        return examTasks;
    }
}
