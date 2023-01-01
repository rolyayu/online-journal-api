package by.vsu.journal.discipline;

import by.vsu.journal.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/disciplines")
public class DisciplineController {

    private final DisciplineService disciplineService;

    @Autowired
    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public List<Discipline> getAll() {
        return disciplineService.readAll();
    }

    @GetMapping("/get_by_name_and_type")
    public Discipline getByNameAndType(@RequestParam("name") String name, @RequestParam("type") String type) {
        return disciplineService.getByNameAndType(name,type).orElseThrow();
    }

    @GetMapping("/get_teachers_by_name_and_type")
    public List<Teacher> getTeachersByNameAndType(@RequestParam("name") String name, @RequestParam("type") String type) {
        return disciplineService.getTeacherByDiscipline(name,type).orElseThrow();
    }

    @PutMapping
    public Discipline save(@RequestParam("name") String name, @RequestParam("type") String type) {
        return disciplineService.save(name,type);
    }

    @DeleteMapping
    public Long delete(@RequestParam("name") String name, @RequestParam("type") String type) {
        return disciplineService.deleteDiscipline(name,type).orElseThrow();
    }
}
