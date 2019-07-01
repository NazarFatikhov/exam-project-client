package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {

    private Long id;
    private String studentNameAndSurname;
    private String studentEmail;
    private String subjectTypeName;
    private String date;
    private Integer totalScore;

}
