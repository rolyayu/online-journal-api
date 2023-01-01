package by.vsu.journal.scheduled_class;

import by.vsu.journal.classes.Class;
import by.vsu.journal.group.Group;
import by.vsu.journal.teacher.TeacherWithDiscipline;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "scheduled_classes",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"class_id", "group_id", "teachers_disciplines_id", "audience"}
        )
)
public class ScheduledClass {
    @Id
    @SequenceGenerator(
            name = "scheduled_classes_seq",
            sequenceName = "scheduled_classes_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "scheduled_classes_seq"
    )
    @Column(name = "scheduled_class_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "class_id",
            referencedColumnName = "class_id"
    )
    private Class planedClass;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "group_id",
            referencedColumnName = "group_id"
    )
    private Group group;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "teachers_disciplines_id",
            referencedColumnName = "teachers_disciplines_id"
    )
    private TeacherWithDiscipline teacherWithDiscipline;

    @Column(
            name = "audience",
            columnDefinition = "VARCHAR(20)",
            nullable = false)
    private String audience;
}
