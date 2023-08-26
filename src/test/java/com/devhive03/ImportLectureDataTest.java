package com.devhive03;

import com.devhive03.Repository.LectureDAORepository;
import com.devhive03.Service.LectureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImportLectureDataTest {
    @Autowired
    private LectureService lectureService;

    @Test
    public void datatest(){
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\교양.xls", "교양");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\소프트웨어.xls", "소프트웨어");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\컴공.xls", "컴공");
    }
}
