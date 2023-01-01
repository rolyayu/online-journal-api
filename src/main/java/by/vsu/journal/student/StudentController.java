package by.vsu.journal.student;

import by.vsu.journal.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> readById() {
        return studentService.readAll();
    }

    @GetMapping("/{id}")
    public Student readByIdParam(@PathVariable Long id) {
        return studentService.getById(id)
                .orElseThrow( () -> new RecordNotFoundException("No student with " +id +" id"));
    }

    @GetMapping("/get_by_fio_number")
    public Student byFioAndNumber(@RequestParam("fio") String fio, @RequestParam("number") String number) {
        return studentService.getStudentByFioAndGroupNumber(
                fio, number).get();
    }

    @PutMapping
    public Student save(@RequestParam("student") String fio, @RequestParam("groupNumber") String groupNumber) {
        return studentService.saveStudent(fio,groupNumber);
    }

    @DeleteMapping
    public void delete(@RequestParam("student") String fio, @RequestParam("groupNumber") String groupNumber) {
         studentService.deleteByFioAndGroupNumber(fio,groupNumber);
    }
}
