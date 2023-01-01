package by.vsu.journal.teacher;

import by.vsu.journal.discipline.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher readFirstByFio(String fio);

    @Query("select d from Teacher t JOIN TeacherWithDiscipline td ON t.id=td.teacher.id " +
            "JOIN Discipline d ON td.discipline.id=d.id WHERE t.fio=?1")
    List<Discipline> readDisciplinesByTeacherFIO(String fio);

    Teacher readTeacherByFio(String fio);

    @Modifying
    Long deleteByFio(String fio);
}
