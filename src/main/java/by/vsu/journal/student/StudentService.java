package by.vsu.journal.student;

import by.vsu.journal.group.Group;
import by.vsu.journal.group.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public Student saveStudent(String fio, String groupNumber) {
        Group group = groupRepository.readGroupByGroupNumber(groupNumber);
        if(group==null) {
            throw new NoSuchElementException("There is no group with such number");
        }
        Student student = Student.builder()
                .fio(fio)
                .build();
        group.addStudent(student);
        groupRepository.save(group);
        return student;
    }

    public Optional<Student> getStudentByFioAndGroupNumber(String fio, String number) {
        return Optional.ofNullable(studentRepository.findStudentByFioAndGroupNumber(fio, number));
    }

    public Optional<Student> getById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> readAll() {
        return studentRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public void deleteByFioAndGroupNumber(String fio, String groupNumber) {
        Long id = studentRepository.findStudentByFioAndGroupNumber(fio, groupNumber).getId();
        studentRepository.deleteById(id);
    }
}
