package by.vsu.journal.teacher;

import by.vsu.journal.discipline.Discipline;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository repository;

    @Test
    public void save() {
        Teacher ivanova = Teacher.builder()
                .fio("Иванова Жанна Викторовна")
                .build();
        Teacher meh = Teacher.builder()
                .fio("Мехович Андрей Петрович")
                .build();

        assertNotNull(repository.save(ivanova));
        assertNotNull(repository.save(meh));
    }

    @Test
    public void readByFIO() {
        List<Discipline> disciplines = repository
                .readDisciplinesByTeacherFIO("Мехович Андрей Петрович");
        assertNotNull(disciplines);
        disciplines.forEach(System.out::println);
    }
}