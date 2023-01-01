package by.vsu.journal.discipline;

import by.vsu.journal.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline,Long> {
    Discipline readDisciplineByDisciplineNameAndType(String disciplineName, String type);

    List<Discipline> readAllByDisciplineName(String discipline);

    @Query("select t from Discipline d JOIN TeacherWithDiscipline td ON d.id=td.discipline.id " +
            "JOIN Teacher t ON td.teacher.id=t.id WHERE d.disciplineName=:name and d.type=:type")
    List<Teacher> readTeachersByDisciplineNameAndType(
            @Param("name") String name
            ,@Param("type") String type
    );

    @Modifying
    Long deleteByDisciplineNameAndType(String discipline,String type);
}
