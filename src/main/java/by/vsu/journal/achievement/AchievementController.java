package by.vsu.journal.achievement;


import by.vsu.journal.scheduled_class.ScheduledClassController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/achievements")
public class AchievementController {
    private AchievementService service;

    @Autowired
    public AchievementController(AchievementService service) {
        this.service = service;
    }

    @GetMapping
    public List<Achievement> readAll() {
        return service.readAll();
    }

    @GetMapping("/get_by_student_and_date")
    public List<Achievement> readAllByStudentAndDate(
            @RequestParam("fio") String fio,
            @RequestParam("group") String groupNumber,
            @RequestParam("date") String date
    ) {
        Date dateOfClass = null;
        try {
            dateOfClass = ScheduledClassController.CLASS_DATE.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("No classes at such date");
        }
        return service.readAchievementsByStudentAndDate(fio,groupNumber,dateOfClass);
    }

    @PutMapping
    public void addAchievementByStudent(
            @RequestParam("fio") String fio,
            @RequestParam("group") String groupNumber,
            @RequestParam("date") String date,
            @RequestParam("classNumber") byte classNumber
    ) {
        Date dateOfClass = null;
        try {
            dateOfClass = ScheduledClassController.CLASS_DATE.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("No classes at such date");
        }
        service.addAchievementByStudent(fio,groupNumber,dateOfClass,classNumber);
    }


}
