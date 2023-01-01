package by.vsu.journal.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class,Long> {
    Class readClassByBeginTimeAndDate(Date beginTime, Date date);
    Class readClassByNumberAndDate(byte number, Date date);
    List<Class> readClassByDate(Date date);
}
