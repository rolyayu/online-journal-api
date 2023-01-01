package by.vsu.journal.teacher;

import by.vsu.journal.discipline.Discipline;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "teachers_disciplines")
public class TeacherWithDiscipline {
    @Id
    @SequenceGenerator(
            name = "teachers_disciplines_seq",
            sequenceName = "teachers_disciplines_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teachers_disciplines_seq"
    )
    @Column(name = "teachers_disciplines_id")
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacher_id",
            nullable = false
    )
    @JsonIgnore
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(
            name = "discipline_id",
            referencedColumnName = "discipline_id",
            nullable = false
    )
    private Discipline discipline;
}
