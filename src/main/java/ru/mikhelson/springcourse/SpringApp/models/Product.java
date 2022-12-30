package ru.mikhelson.springcourse.SpringApp.models;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "Goods")
public class Product
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_good")
    private String name;

    @Column(name = "count")
    @Min(value = 0, message = "Количество товара должно быть больше 0")
    private int count;

    public Product() {}

    public Product(int id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
