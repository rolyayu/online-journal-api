package by.vsu.journal.discipline;

import by.vsu.journal.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineService {
    private DisciplineRepository repository;

    @Autowired
    public DisciplineService(DisciplineRepository repository) {
        this.repository = repository;
    }

    public Discipline save(String name, String type) {
        Discipline discipline = Discipline.builder()
                .disciplineName(name)
                .type(type)
                .build();
        return repository.save(discipline);
    }

    public Optional<List<Teacher>> getTeacherByDiscipline(String name, String type) {
        return Optional.ofNullable(
                repository.readTeachersByDisciplineNameAndType(
                        name, type)
        );
    }

    public Optional<Discipline> getByNameAndType(String name, String type) {
        return Optional.ofNullable(
                repository.readDisciplineByDisciplineNameAndType(name,type)
        );
    }

    @Transactional
    public Optional<Long> deleteDiscipline(String name, String type) {
        return Optional.ofNullable(
                repository.deleteByDisciplineNameAndType(
                        name, type)
        );
    }

    @Transactional
    public void deleteDisciplineById(Long id) {
        repository.deleteById(id);
    }

    public List<Discipline> readAll() {
        return repository.findAll();
    }
}
