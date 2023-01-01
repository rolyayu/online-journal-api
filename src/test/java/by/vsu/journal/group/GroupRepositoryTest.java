package by.vsu.journal.group;

import by.vsu.journal.student.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupRepositoryTest {

    @Autowired
    private GroupRepository repository;

    @Test
    public void save(){
        Student ann = Student.builder().fio("Жариков Петр Игоревич").build();
        Student nas = Student.builder().fio("Сернов Адам Валерьянович").build();

        Group posk = Group.builder()
                .groupNumber("31")
                .specialty("ПИ(ПОКС)")
                .courseNumber((byte)3)
                .build();
        posk.addStudent(ann);
        posk.addStudent(nas);
        repository.save(posk);
    }

    @Test
    public void readAll() {
        repository.findAll().forEach(System.out::println);
    }

    @Test
    public void readByNumber() {
        System.out.println(repository.findStudentsByGroupNumber("31"));
    }

    @Test
    public void readAllStudentsFIO() {
        repository.findAll().stream()
                .map(Group::getStudents)
                .forEach(System.out::println);
    }

    @Test
    @Transactional
    public void deleteByNumber() {
        repository.deleteByGroupNumber("32");
    }
}