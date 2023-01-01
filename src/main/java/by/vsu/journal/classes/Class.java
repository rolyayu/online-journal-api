package by.vsu.journal.classes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "classes",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"class_date","class_begin_time","class_nubmer"}
        )
)
public class Class {
    @Id
    @SequenceGenerator(
            name = "classes_seq",
            sequenceName = "classes_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "classes_seq"
    )
    @Column(name = "class_id")
    private Long id;

    @Column(name ="class_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "class_begin_time", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date beginTime;

    @Column(name = "class_nubmer", nullable = false)
    private byte number;
}
