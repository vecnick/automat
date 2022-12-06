package mirea.automat.models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name="staff")
public class SafetyRules {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
