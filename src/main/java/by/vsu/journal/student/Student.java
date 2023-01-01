package by.vsu.journal.student;

import by.vsu.journal.group.Group;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "students"
)
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_seq",
            sequenceName = "student_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_seq"
    )
    @Column(name = "student_id")
    private Long id;

    @Column(name = "fio", nullable = false)
    private String fio;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    @JsonIgnore
    private Group group;
}
