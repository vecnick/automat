package mirea.automat.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(exclude = {"staffs"},includeFieldNames = false)
@Entity
@Table(name="safety_rule")
public class SafetyRule {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Rule name should not be empty")
    @Column(name = "rule_name")
    @NonNull
    private String name;

    @NotEmpty(message = "Enter description")
    @Column(name = "rule_desc")
    @NonNull
    private String description;

    @OneToMany(mappedBy = "rule")
    private List<Staff> staffs;
}
