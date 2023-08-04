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

//스프링 컨테이너가 @Bean, @Component, @Repository, @Service, @Controller 등의 어노테이션이 붙은 클래스를 찾아서 빈으로 생성합니다.

//스프링 컨테이너는 @Autowired, 생성자, setter 등을 통해 의존성을 주입해야 하는 필드를 찾습니다.

//스프링 컨테이너는 해당 타입의 빈을 찾아서 필드에 주입합니다. 이 때, 해당 타입의 빈이 없거나 둘 이상이면 문제가 발생할 수 있습니다.

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
