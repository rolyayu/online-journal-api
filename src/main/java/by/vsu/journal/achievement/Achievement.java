package by.vsu.journal.achievement;

import by.vsu.journal.scheduled_class.ScheduledClass;
import by.vsu.journal.student.Student;
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
        name = "achievements",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_student_achievement_per_class",
                columnNames = {"scheduled_class_id", "student_id"}
        )
)
public class Achievement {
    @Id
    @SequenceGenerator(
            name = "achievements_seq",
            sequenceName = "achievements_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "achievements_seq"
    )
    @Column(name = "achievement_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "student_id"
    )
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "scheduled_class_id",
            referencedColumnName = "scheduled_class_id"
    )
    private ScheduledClass scheduledClass;

    @Column(
            name = "achievement_mark"
    )
    private byte mark;

    @Column(
            name = "student_absent",
            columnDefinition = "BOOLEAN DEFAULT FALSE",
            updatable = false,
            nullable = false
    )
    private boolean isAbsent;
}
