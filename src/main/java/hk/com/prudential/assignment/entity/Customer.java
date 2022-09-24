package hk.com.prudential.assignment.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

}
