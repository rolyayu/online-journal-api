package by.vsu.journal.achievement;

import by.vsu.journal.scheduled_class.ScheduledClass;
import by.vsu.journal.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    @Query("select a from Achievement a " +
            "JOIN ScheduledClass sc ON a.scheduledClass.id=sc.id " +
            "JOIN Class c ON sc.planedClass.id=c.id " +
            "WHERE a.student=:student and c.date=:date")
    List<Achievement> readAllByStudentAndDate(
            @Param("student")Student student,
            @Param("date")Date date
            );

    Achievement readByStudentAndScheduledClass(
            Student student,
            ScheduledClass scheduledClass
    );


}
