package models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Exam {

    private Long id;
    private String studentNameAndSurname;
    private String studentEmail;
    private String teacherEmail;
    private String subjectTypeName;
    private Long subjectTypeId;
    private String date;
    private Integer totalScore;
    private List<ExamTask> examTasks;
    private EditButton editButton;
}
