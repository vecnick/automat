package mirea.automat.models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name="\"Order\"")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
