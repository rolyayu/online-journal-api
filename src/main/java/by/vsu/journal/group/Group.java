package by.vsu.journal.group;

import by.vsu.journal.student.Student;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name="groups",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_spec_per_course",
                columnNames = {"specialty","course_number"}
        )
)
public class Group {
    @Id
    @SequenceGenerator(
            name = "groups_seq",
            sequenceName = "groups_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "groups_seq"
    )
    @Column(name = "group_id")
    @ToString.Exclude
    private Long id;

    @Column(name = "group_number",
            columnDefinition = "VARCHAR(5)",
            nullable = false,
            unique = true
    )
    private String groupNumber;

    @Column(name = "specialty", nullable = false)
    private String specialty;

    @Column(name = "course_number", nullable = false)
    private byte courseNumber;

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @ToString.Exclude
    private List<Student> students;

    public void addStudent(Student student) {
        if(students==null) {
            students = new LinkedList<>();
        }
        student.setGroup(this);
        students.add(student);
    }
}
