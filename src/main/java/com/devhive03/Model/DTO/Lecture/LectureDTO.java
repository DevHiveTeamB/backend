package com.devhive03.Model.DTO.Lecture;

import com.devhive03.Model.DAO.Lecture;
import lombok.*;

@Data
@Builder
@Setter @Getter
@AllArgsConstructor
public class LectureDTO {
    private Long lectureId;
    private String lectureName;
    private String professorName;
    private String major;

    public static LectureDTO of(Lecture lecture) {
        return LectureDTO.builder()
            .lectureId(lecture.getLectureID())
            .lectureName(lecture.getLectureName())
            .professorName(lecture.getProfessorName())
            .major(lecture.getMajor())
            .build();
    }
}
