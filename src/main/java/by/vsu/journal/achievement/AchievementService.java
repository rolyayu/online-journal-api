package by.vsu.journal.achievement;

import by.vsu.journal.scheduled_class.ScheduledClass;
import by.vsu.journal.scheduled_class.ScheduledClassService;
import by.vsu.journal.student.Student;
import by.vsu.journal.student.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AchievementService {
    private AchievementRepository repository;

    private StudentRepository studentRepository;

    private ScheduledClassService scheduledClassService;

    public AchievementService(AchievementRepository repository, StudentRepository studentRepository, ScheduledClassService scheduledClassService) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.scheduledClassService = scheduledClassService;
    }

    public Achievement save(Achievement achievement) {
        return repository.save(achievement);
    }

    public Optional<List<Achievement>> readAllByStudentAndDate(Student student, Date date) {
        return Optional.ofNullable(
                repository.readAllByStudentAndDate(student, date)
        );
    }

    public List<Achievement> readAchievementsByStudentAndDate(
            String fio,
            String groupNumber,
            Date date
    ) {
        Student student = studentRepository.findStudentByFioAndGroupNumber(fio,groupNumber);
        List<ScheduledClass> scheduledClassList = scheduledClassService
                .readScheduledClassByGroupAndClassDate(
                        groupNumber, date
                ).orElseThrow();
        return scheduledClassList.stream()
                .map(planedClass -> repository.readByStudentAndScheduledClass(student, planedClass))
                .toList();
    }

    public List<Achievement> readAll() {
        return repository.findAll();
    }

    public void addAchievementByStudent(String fio, String groupNumber, Date dateOfClass, byte classNumber) {
    }
}
