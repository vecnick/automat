package mirea.automat.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(exclude = {"qualities"},includeFieldNames = false)
@Entity
@Table(name="defect")
public class Defect {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "defectCase should not be empty")
    @Column(name = "defect_case")
    @NonNull
    private String defectCase;

    @OneToMany(mappedBy = "defect")
    private List<Quality> qualities;
}
