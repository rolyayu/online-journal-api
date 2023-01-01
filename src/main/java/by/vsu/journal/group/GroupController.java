package by.vsu.journal.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getAll() {
        return groupService.readAll();
    }

    @GetMapping("/{number}")
    public Group getByNumber(@PathVariable("number") String number) {
        return groupService.getGroupByGroupNumber(number)
                .orElseThrow(() -> new NoSuchElementException("There is no group with such number"));
    }

    @PutMapping
    public Group add(@RequestParam("course") byte course,
                     @RequestParam("specialty") String specialty,
                     @RequestParam("number") String number
    ) {
        return groupService.save(course,specialty,number);
    }

    @DeleteMapping
    public Long deleteStudentFromGroup(@RequestParam("groupNumber") String number) {
        return groupService.deleteGroupByGroupNumber(number);
    }
}
