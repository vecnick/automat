package mirea.automat.models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name="quality")
public class Quality {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
