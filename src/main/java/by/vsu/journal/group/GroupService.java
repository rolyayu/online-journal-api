package by.vsu.journal.group;

import by.vsu.journal.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GroupService {
    private GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group save(byte course, String specialty, String number) {
        Group group = Group.builder()
                .courseNumber(course)
                .specialty(specialty)
                .groupNumber(number)
                .build();
        return groupRepository.save(group);
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public Group addStudentToGroup(Student student,Group group) {
        group.addStudent(student);
        return save(group);
    }

    public List<Group> readAll() {
        return groupRepository.findAll();
    }

    public Group addStudentToGroupByGroupNumber(Student student,String number) {
        Group group = groupRepository.readGroupByGroupNumber(number);
        group.addStudent(student);
        return save(group);
    }

    public List<Student> getStudentByGroupNumber(String number) {
        return groupRepository.findStudentsByGroupNumber(number);
    }

    public Optional<Group> getGroupByGroupNumber(String number) {
        return Optional.ofNullable(groupRepository.readGroupByGroupNumber(number));
    }

    @Transactional
    public Long deleteGroupByGroupNumber(String number) {
        return groupRepository.deleteByGroupNumber(number);
    }
}
