package by.vsu.journal.classes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClassRepositoryTest {
    @Autowired
    private ClassRepository repository;

    @Test
    public void save() throws ParseException {
        Class classToSave = Class.builder()
                .beginTime(new SimpleDateFormat("HH:mm").parse("08:00"))
                .date(new SimpleDateFormat("dd.MM.yyyy").parse("19.12.2022"))
                .number((byte) 1)
                .build();
        assertNotNull(repository.save(classToSave));
    }
}