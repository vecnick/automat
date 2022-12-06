package mirea.automat.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
@Entity
@Table(name="producer")
public class Producer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Producer name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;
}
