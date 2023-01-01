package by.vsu.journal.scheduled_class;

import by.vsu.journal.classes.Class;
import by.vsu.journal.classes.ClassRepository;
import by.vsu.journal.discipline.Discipline;
import by.vsu.journal.discipline.DisciplineRepository;
import by.vsu.journal.group.Group;
import by.vsu.journal.group.GroupRepository;
import by.vsu.journal.teacher.Teacher;
import by.vsu.journal.teacher.TeacherRepository;
import by.vsu.journal.teacher.TeacherWithDiscipline;
import by.vsu.journal.teacher.TeacherWithDisciplineRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScheduledClassRepositoryTest {
    private final ScheduledClassRepository scheduledClassRepository;
    private final GroupRepository groupRepository;

    private final TeacherWithDisciplineRepository teacherWithDisciplineRepository;
    private final ClassRepository classRepository;

    @Autowired
    public ScheduledClassRepositoryTest(ScheduledClassRepository scheduledClassRepository, GroupRepository groupRepository, TeacherWithDisciplineRepository teacherWithDisciplineRepository, ClassRepository classRepository) {
        this.scheduledClassRepository = scheduledClassRepository;
        this.groupRepository = groupRepository;
        this.teacherWithDisciplineRepository = teacherWithDisciplineRepository;
        this.classRepository = classRepository;
    }

    @Test
    public void save() throws ParseException {
        Group group = groupRepository.readGroupByGroupNumber("32");
        assertNotNull(group);
        Class planedClass = classRepository.readClassByNumberAndDate(
                (byte) 1,
                new SimpleDateFormat("dd.MM.yyyy").parse("19.12.2022")
        );
        TeacherWithDiscipline teacher = teacherWithDisciplineRepository
                .findByTeacherFioAndDisciplineNameAndType(
                        "Мехович Андрей Петрович",
                        "Алгебра и теория чисел",
                        "Лекции"
                );
        assertNotNull(teacher);
        ScheduledClass scheduledClass = ScheduledClass.builder()
                .planedClass(planedClass)
                .group(group)
                .audience("141")
                .teacherWithDiscipline(teacher)
                .build();
        assertNotNull(scheduledClassRepository.save(scheduledClass));
    }
}