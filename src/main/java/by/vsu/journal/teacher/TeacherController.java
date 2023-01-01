package by.vsu.journal.teacher;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("api/v1/teachers")
@RestController
public class TeacherController {
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> readAll() {
        return teacherService.readAll();
    }

    @GetMapping("/get_by_fio")
    public Teacher readByFio(@RequestParam("fio") String fio) {
        return teacherService.getTeacherByFio(fio)
                .orElseThrow(() -> new NoSuchElementException("There is no teacher with such FIO"));
    }

    @PutMapping
    public Teacher saveByFio(@RequestParam("fio") String fio) {
        return teacherService.saveByFio(fio);
    }

    @DeleteMapping
    public Long deleteByFio(@RequestParam("fio") String fio) {
        return teacherService.deleteByFio(fio).orElseThrow();
    }
}
