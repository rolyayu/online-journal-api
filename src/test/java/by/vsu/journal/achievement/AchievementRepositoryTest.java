package by.vsu.journal.achievement;

import by.vsu.journal.scheduled_class.ScheduledClass;
import by.vsu.journal.scheduled_class.ScheduledClassRepository;
import by.vsu.journal.student.Student;
import by.vsu.journal.student.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AchievementRepositoryTest {

    private AchievementRepository achievementRepository;
    private ScheduledClassRepository scheduledClassRepository;
    private StudentRepository studentRepository;

    @Autowired
    public AchievementRepositoryTest(AchievementRepository achievementRepository,
                                     ScheduledClassRepository scheduledClassRepository,
                                     StudentRepository studentRepository) {
        this.achievementRepository = achievementRepository;
        this.scheduledClassRepository = scheduledClassRepository;
        this.studentRepository = studentRepository;
    }

    @Test
    public void save() {
        ScheduledClass scheduledClass = scheduledClassRepository.getReferenceById(2L);
        Student student = studentRepository.getReferenceById(14L);
        Achievement achievement = Achievement.builder()
                .scheduledClass(scheduledClass)
                .student(student)
                .build();
        assertNotNull(achievementRepository.save(achievement));
    }
}