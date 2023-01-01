package by.vsu.journal.scheduled_class;

import by.vsu.journal.teacher.TeacherWithDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduledClassRepository extends JpaRepository<ScheduledClass,Long> {
    @Query("select td from ScheduledClass sc JOIN Class c ON sc.planedClass.id=c.id " +
            "JOIN TeacherWithDiscipline td ON sc.teacherWithDiscipline.id=td.id " +
            "JOIN Group g ON sc.group.id=g.id " +
            "where c.date=:date and g.groupNumber=:number")
    List<TeacherWithDiscipline> getTeachersWithDisciplineByClassDateAndGroupNumber(
            @Param("date") Date date,
            @Param("number")String groupNumber
    );

    @Query("select sc.audience from ScheduledClass sc JOIN Class c ON sc.planedClass.id=c.id " +
            "JOIN Group g ON sc.group.id=g.id " +
            "where c.date=:date and g.groupNumber=:number")
    List<String> readAllAudienceByDateAndGroupName(
            @Param("date") Date date,
            @Param("number")String groupNumber
    );

    @Query("select sc from ScheduledClass sc JOIN Class c ON sc.planedClass.id=c.id " +
            "JOIN Group g ON sc.group.id=g.id " +
            "where c.date=:date and g.courseNumber=:course")
    List<ScheduledClass> readAllByDateAndCourse(
            @Param("date") Date date,
            @Param("course") byte courseNumber
    );

    @Query("select sc from ScheduledClass sc JOIN Class c ON sc.planedClass.id=c.id " +
            "where c.date=:date")
    List<ScheduledClass> readAllByDate(
            @Param("date") Date date
    );

    @Query("select sc from ScheduledClass sc JOIN Class c ON sc.planedClass.id=c.id " +
            "JOIN Group g ON sc.group.id=g.id " +
            "where c.date=:date and g.groupNumber=:groupNumber")
    List<ScheduledClass> readScheduledClassByGroupAndClassDate(
            @Param("groupNumber") String groupNumber,
            @Param("date") Date date
    );
}
