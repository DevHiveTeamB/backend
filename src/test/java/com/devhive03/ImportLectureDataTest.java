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
        lectureService.readFromExcel("D:\\devhiveLectureData\\교양.xls");
    }
}