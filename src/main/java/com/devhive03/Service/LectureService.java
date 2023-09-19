package com.devhive03.Service;

import com.devhive03.Model.DAO.Lecture;
import com.devhive03.Model.DTO.Lecture.LectureDTO;
import com.devhive03.Repository.LectureDAORepository;
import jakarta.validation.constraints.Null;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureService {
    @Autowired
    private LectureDAORepository lectureDAORepository;

    public void saveLectures(List<Lecture> lectures) {
        lectureDAORepository.saveAll(lectures);
    }


    public List<LectureDTO> findByLectureName(String lectureName) {
        List<Lecture> lectures = lectureDAORepository.findByLectureName(lectureName);
        return lectures.stream().map(LectureDTO::of).collect(Collectors.toList());
    }

    public List<LectureDTO> findByMajor(String major) {
        List<Lecture> lectures = lectureDAORepository.findByMajor(major);
        return lectures.stream().map(LectureDTO::of).collect(Collectors.toList());
    }

    public List<LectureDTO> findByProfessorName(String professor) {
        List<Lecture> lectures = lectureDAORepository.findByProfessorName(professor);
        return lectures.stream().map(LectureDTO::of).collect(Collectors.toList());
    }


    public void readFromExcel(String filePath, String lecturename) {
        List<Lecture> lectures = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if(row.getCell(0).getStringCellValue()==""){ //수정필요 문자열 비교
                    break;
                }
                Lecture lecture = new Lecture();
                lecture.setLectureName(row.getCell(0).getStringCellValue());
                lecture.setProfessorName(row.getCell(1).getStringCellValue());
                lecture.setMajor(lecturename);
                lectures.add(lecture);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveLectures(lectures);
    }
}
