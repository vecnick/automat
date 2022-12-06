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

    @NotEmpty(message = "position name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @OneToMany(mappedBy = "position")
    private List<Staff> staffs;
}
