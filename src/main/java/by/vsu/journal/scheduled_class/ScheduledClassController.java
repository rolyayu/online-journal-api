package by.vsu.journal.scheduled_class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/scheduled_classes")
public class ScheduledClassController {
    private ScheduledClassService scheduledClassService;

    public final static SimpleDateFormat CLASS_DATE = new SimpleDateFormat("dd.MM.yyyy");
    public final static SimpleDateFormat CLASS_TIME = new SimpleDateFormat("HH:mm");

    @Autowired
    public ScheduledClassController(ScheduledClassService scheduledClassService) {
        this.scheduledClassService = scheduledClassService;
    }

    @GetMapping
    public List<ScheduledClass> readAll() {
        return scheduledClassService.readAll();
    }

    @GetMapping("/get_by_date_and_group")
    public List<ScheduledClass> getByDateAndGroup(
            @RequestParam("date") String date,
            @RequestParam("group") String number
    ) {
        Date dateOfClasses = null;
        try {
            dateOfClasses = CLASS_DATE.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Incorrect date of class");
        }
        return scheduledClassService
                .readScheduledClassByGroupAndClassDate(
                        number,dateOfClasses
                ).orElseThrow();
    }
}
