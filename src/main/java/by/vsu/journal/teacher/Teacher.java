package by.vsu.journal.teacher;

import by.vsu.journal.discipline.Discipline;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
        name = "teachers"
)
public class Teacher {
    @Id
    @SequenceGenerator(
            name = "teacher_seq",
            sequenceName = "teacher_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_seq"
    )
    @Column(name ="teacher_id")
    private Long id;

    @Column(name = "fio", nullable = false, updatable = false)
    private String fio;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "teachers_disciplines",
//            joinColumns = @JoinColumn(
//                    name = "teacher_id",
//                    referencedColumnName = "teacher_id"
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "discipline_id",
//                    referencedColumnName = "discipline_id"
//            )
//    )
//    private List<Discipline> disciplines;

//    public void addDisciplines(List<Discipline> disciplines){
//        if(this.disciplines==null) this.disciplines = new LinkedList<>();
//        this.disciplines.addAll(disciplines);
//    }

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<TeacherWithDiscipline> teacherWithDisciplines;

}
