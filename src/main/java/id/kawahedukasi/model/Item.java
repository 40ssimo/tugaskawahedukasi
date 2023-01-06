package id.kawahedukasi.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "item")
public class Item extends AuditModel {
    @Id
    @SequenceGenerator(
            name = "itemSequence",
            sequenceName = "item_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(generator = "peserta_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    public Integer id;
    @Column(name = "name")
    public String name;
    @Column(name = "count")
    public Integer count;
    @Column(name = "price")
    public Integer price;
    @Column(name = "type")
    public String type;
    @Column(name = "description")
    public String description;

}
