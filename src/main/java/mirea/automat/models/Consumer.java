package mirea.automat.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(exclude = {"orders"},includeFieldNames = false)
@Entity
@Table(name="consumer")
public class Consumer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Consumer name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @NotEmpty(message = "Consumer second_name should not be empty")
    @Column(name = "second_name")
    @NonNull
    private String secondName;

    @Column(name = "patronymic")
    @NonNull
    private String patronymic;

    @OneToMany(mappedBy = "consumer")
    private List<Order> orders;
}
