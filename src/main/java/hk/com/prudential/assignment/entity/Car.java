package hk.com.prudential.assignment.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String model;

    private Integer stock;

}
