package by.vsu.journal.teacher;

import by.vsu.journal.discipline.Discipline;
import by.vsu.journal.discipline.DisciplineRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherWithDisciplineRepositoryTest {

    @Autowired
    private TeacherWithDisciplineRepository teacherWithDisciplineRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private DisciplineRepository disciplineRepository;

    @Test
    public void save() {
        Teacher teacher = teacherRepository.readFirstByFio("Мехович Андрей Петрович");
        System.out.println(teacher);
        assertNotNull(teacher);

        Discipline discipline = disciplineRepository
                .readDisciplineByDisciplineNameAndType(
                        "Алгебра и теория чисел",
                        "Лекции"
                );
        System.out.println(discipline);
        assertNotNull(discipline);

        TeacherWithDiscipline teacherWithDiscipline = TeacherWithDiscipline.builder()
                .teacher(teacher)
                .discipline(discipline)
                .build();
        System.out.println(teacherWithDiscipline);
        assertNotNull(teacherWithDisciplineRepository.save(teacherWithDiscipline));
    }
}