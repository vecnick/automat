package mirea.automat.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(exclude = {"rule","position","orders"},includeFieldNames = false)
@Entity
@Table(name="staff")
public class Staff {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Staff name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @NotEmpty(message = "Staff second_name should not be empty")
    @Column(name = "second_name")
    @NonNull
    private String secondName;

    @Column(name = "patronymic")
    @NonNull
    private String patronymic;

    @Column(name = "telephone_number")
    @NonNull
    private String telephone;

    @ManyToOne
    @JoinColumn(name="rule_id", referencedColumnName = "id")
    private SafetyRule rule;

    @ManyToOne
    @JoinColumn(name="position_id", referencedColumnName = "id")
    private Position position;

    @OneToMany(mappedBy = "staff")
    private List<Order> orders;
}
