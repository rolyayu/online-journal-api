package by.vsu.journal.student;

import by.vsu.journal.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s JOIN Group g on s.group.id=g.id " +
            "WHERE s.fio=:fio and g.groupNumber=:number")
    Student findStudentByFioAndGroupNumber(@Param("fio")String fio, @Param("number") String number);

    @Query("select s from Student s JOIN Group g on s.group.id=g.id WHERE g.courseNumber=?1")
    List<Student> findAllByCourseNumber(byte course);

    Long deleteByGroupAndFio(Group group, String fio);

    @Modifying
    @Query("delete from Student s where s.fio=:fio and  s.group=:group")
    Long deleteByFioAndGroupNumber(@Param("fio")String fio, @Param("group") Group group);
}
