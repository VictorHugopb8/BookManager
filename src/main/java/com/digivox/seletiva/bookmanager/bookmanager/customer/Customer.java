package com.digivox.seletiva.bookmanager.bookmanager.customer;

import lombok.*;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;

@Entity
@AccessType(AccessType.Type.FIELD)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "sq_customer", initialValue = 1)
//@AttributeOverride(name = "id", column = @Column(name = "customer_id"))
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String nationalId;
}
