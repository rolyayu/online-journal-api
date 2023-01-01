package by.vsu.journal.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherWithDisciplineRepository extends JpaRepository<TeacherWithDiscipline,Long> {
    @Query("select td FROM TeacherWithDiscipline td JOIN Teacher t on td.teacher.id=t.id " +
            "JOIN Discipline d ON td.discipline.id=d.id " +
            "WHERE t.fio=:fio and d.disciplineName=:discipline_name and d.type=:type")
    TeacherWithDiscipline findByTeacherFioAndDisciplineNameAndType(
            @Param("fio") String fio,
            @Param("discipline_name") String name,
            @Param("type") String type
            );
}
