package by.vsu.journal.scheduled_class;

import by.vsu.journal.classes.Class;
import by.vsu.journal.group.Group;
import by.vsu.journal.teacher.TeacherWithDiscipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduledClassService {
    private ScheduledClassRepository repository;

    @Autowired
    public ScheduledClassService(ScheduledClassRepository repository) {
        this.repository = repository;
    }

    public ScheduledClass save(ScheduledClass scheduledClass) {
        return repository.save(scheduledClass);
    }

    public Optional<List<ScheduledClass>> readAllByDate(Date date) {
        return Optional.ofNullable(
                repository.readAllByDate(date)
        );
    }

    public Optional<List<ScheduledClass>> readAllByDateAndCourse(Date date, byte courseNumber) {
        return Optional.ofNullable(
                repository.readAllByDateAndCourse(
                        date, courseNumber
                )
        );
    }

    public Optional<ScheduledClass> readScheduledClassByGroupAndClassDateAndNumber(
            String groupNumber, Date date, byte classNumber
            ) {
        return repository.readScheduledClassByGroupAndClassDate(groupNumber,date)
                .stream()
                .filter(sc -> sc.getPlanedClass().getNumber()==classNumber)
                .findFirst();
    }

    public Optional<List<ScheduledClass>> readScheduledClassByGroupAndClassDate(
            String groupNumber, Date date
    ) {
        return Optional.ofNullable(
                repository.readScheduledClassByGroupAndClassDate(groupNumber,date)
        );
    }

    public Optional<List<TeacherWithDiscipline>>
            getTeachersWithDisciplineByClassDateAndGroupNumber(
                    Date date, String groupNumber
    ) {
        return Optional.ofNullable(
                repository
                        .getTeachersWithDisciplineByClassDateAndGroupNumber(date, groupNumber)
        );
    }

    public Optional<List<String>>
    readAllAudienceByDateAndGroupName(
            Date date, String groupNumber
    ) {
        return Optional.ofNullable(
                repository
                        .readAllAudienceByDateAndGroupName(date, groupNumber)
        );
    }

    public List<ScheduledClass> readAll() {
        return repository.findAll();
    }
}
