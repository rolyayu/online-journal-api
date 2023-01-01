package by.vsu.journal.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    private ClassRepository repository;

    @Autowired
    public ClassService(ClassRepository repository) {
        this.repository = repository;
    }

    public Class save(Class toSave) {
        return repository.save(toSave);
    }

    public Optional<Class> readClassByBeginTimeAndDate(
            Date beginTime,
            Date date) {
        return Optional.ofNullable(
                repository
                        .readClassByBeginTimeAndDate(beginTime, date)
        );
    }

    public Optional<Class> readClassByNumberAndDate(byte number, Date date) {
        return Optional.ofNullable(
                repository.readClassByNumberAndDate(number, date)
        );
    }

    public Optional<List<Class>> readClassesByDate(Date date) {
        return Optional.ofNullable(repository.readClassByDate(date));
    }
}
