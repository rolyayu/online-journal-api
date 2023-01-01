package by.vsu.journal;

import by.vsu.journal.student.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean(name = "student")
    public Student service() {
        return Student.builder()
                .fio("Пешкоом Гулять Лучше")
                .id(123L)
                .build();
    }
}
