package by.vsu.journal.group;

import by.vsu.journal.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select s FROM Student s where s.group.groupNumber=?1")
    List<Student> findStudentsByGroupNumber(String number);
    Group readGroupByGroupNumber(String groupNumber);
    @Modifying
    Long deleteByGroupNumber(String groupNumber);
}
