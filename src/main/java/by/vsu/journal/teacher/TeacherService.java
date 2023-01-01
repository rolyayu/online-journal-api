package by.vsu.journal.teacher;

import by.vsu.journal.discipline.Discipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private TeacherRepository repository;

    @Autowired
    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public Teacher save(Teacher teacher) {
        return repository.save(teacher);
    }

    public List<Teacher> readAll() {
        return repository.findAll();
    }

    public Optional<List<Discipline>> getDisciplinesByTeacher(Teacher teacher) {
        return Optional.ofNullable(repository.readDisciplinesByTeacherFIO(teacher.getFio()));
    }

    public Optional<Teacher> getTeacherByFio(String fio) {
        return Optional.ofNullable(
                repository.readTeacherByFio(fio)
        );
    }

    @Transactional
    public Optional<Long> deleteByFio(String fio) {
        return Optional.ofNullable(repository.deleteByFio(fio));
    }

    public Teacher saveByFio(String fio) {
        Teacher teacher = Teacher.builder()
                .fio(fio)
                .build();
        return repository.save(teacher);
    }
}
