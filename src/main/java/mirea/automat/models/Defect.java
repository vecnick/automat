package mirea.automat.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
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
}
