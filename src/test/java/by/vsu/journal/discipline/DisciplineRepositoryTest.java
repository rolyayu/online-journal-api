package by.vsu.journal.discipline;

import by.vsu.journal.teacher.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DisciplineRepositoryTest {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Test
    public void save() {
        Discipline disciplinePz = Discipline.builder()
                .type("Практики")
                .disciplineName("Математический анализ")
                .build();
        Discipline disciplineLk = Discipline.builder()
                .type("Лекции")
                .disciplineName("Математический анализ")
                .build();
        Discipline algebra = Discipline.builder()
                .type("Лекции")
                .disciplineName("Алгебра и теория чисел")
                .build();
        Discipline algebraPz = Discipline.builder()
                .type("Практики")
                .disciplineName("Алгебра и теория чисел")
                .build();
        assertNotNull(
                disciplineRepository.saveAll(
                        List.of(disciplineLk,disciplinePz,algebraPz,algebra)
                )
        );
    }

    @Test
    public void readTeachers() {
        List<Teacher> teachers = disciplineRepository
                .readTeachersByDisciplineNameAndType(
                        "Алгебра и теория чисел","Лекции"
                );
        assertNotNull(teachers);
        teachers.forEach(System.out::println);
    }

}