package mirea.automat.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(exclude = {"staffs"},includeFieldNames = false)
@Entity
@Table(name="position")
public class Position {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "position should not be empty")
    @Column(name = "position")
    @NonNull
    private String position;

    @OneToMany(mappedBy = "position")
    private List<Staff> staffs;
}
