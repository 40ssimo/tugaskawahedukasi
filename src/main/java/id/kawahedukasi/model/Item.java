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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
