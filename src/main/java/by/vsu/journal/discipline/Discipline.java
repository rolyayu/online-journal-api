package by.vsu.journal.discipline;

import by.vsu.journal.teacher.Teacher;
import by.vsu.journal.teacher.TeacherWithDiscipline;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "disciplines",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"discipline_name","discipline_type"}
        )
)
public class Discipline {
    @Id
    @SequenceGenerator(
            name = "disciplines_seq",
            sequenceName = "disciplines_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "disciplines_seq"
    )
    @Column(name = "discipline_id")
    private Long id;

    @Column(name = "discipline_name", updatable = false, nullable = false)
    private String disciplineName;

    @Column(name = "discipline_type",
            columnDefinition = "VARCHAR(15) CHECK(discipline_type in ('Лекции','Практики','Лабораторные'))",
            updatable = false,
            nullable = false)
    private String type;

//    @ManyToMany(mappedBy = "disciplines", fetch = FetchType.LAZY)
//    private List<Teacher> teachers;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<TeacherWithDiscipline> teachersWithDiscipline;
}
