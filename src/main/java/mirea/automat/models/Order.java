package mirea.automat.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@NoArgsConstructor
@Data
@ToString(exclude = {"consumer","cloth","staff"},includeFieldNames = false)
@Entity
@Table(name="\"Order\"")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Order name should not be empty")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Price should be greater than 0, dont hack!")
    @Column(name = "price")
    private int price;

    @Min(value = 0, message = "NDS should be greater than 0, dont hack!")
    @Column(name = "nds")
    private int nds;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name="consumer_id", referencedColumnName = "id")
    private Consumer consumer;

    @ManyToOne
    @JoinColumn(name="cloth_id", referencedColumnName = "id")
    private Cloth cloth;

    @ManyToOne
    @JoinColumn(name="staff_id", referencedColumnName = "id")
    private Staff staff;
}
