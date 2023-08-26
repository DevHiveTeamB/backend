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
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\교양.xls", "교양기초교육원");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\소프트웨어.xls", "소프트웨어학과");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\컴공.xls", "컴퓨터공학과");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\겜공.xls", "게임공학과");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\경영.xls", "경영학부");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\글로벌융합.xls", "글로벌융합공학과");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\기계공.xls", "기계공학과");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\기설.xls", "기계설계공학부");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\나노.xls", "나노반도체공학과");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\메카.xls", "메카트로닉스공학부");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\산디.xls", "디자인공학부");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\생화공.xls", "생명화학공학과");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\신소재.xls", "신소재공학과");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\애전.xls", "애너지전기공학과");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\인공지능.xls", "인공지능학과");
        lectureService.readFromExcel(".\\src\\main\\resources\\lectureData\\전자.xls", "전자공학부");
    }
}
