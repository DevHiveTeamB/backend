package com.devhive03;

import com.devhive03.Model.DAO.Lecture;
import com.devhive03.Repository.LectureDAORepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(LectureDAORepository repository) {
        return args -> {
            BufferedReader reader = new BufferedReader(new FileReader("lectures.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                Long id = Long.parseLong(fields[0]);
                String lectureName = fields[1];
                String professorName = fields[2];
                String major = fields[3];

                Lecture lecture = new Lecture(id, lectureName, professorName, major);
                repository.save(lecture);
            }
            reader.close();
        };
    }
}
