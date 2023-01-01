package by.vsu.journal.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void readByFioAndGroupNumber() {
        assertNotNull(
                studentRepository.findStudentByFioAndGroupNumber(
                        "Сернов Адам Валерьянович","31"
                )
        );
    }

    @Test
    public void readAllByCourse() {
        List<Student> students = studentRepository.findAllByCourseNumber((byte) 3);
        students.forEach(System.out::println);
        assertNotNull(students);
    }

    @Transactional
    @Test
    public void deleteByFioAndGroupNumber() {
        assertNotNull(
                studentRepository
                        .findStudentByFioAndGroupNumber("Сернов Адам Валерьянович","31")
        );
    }
}