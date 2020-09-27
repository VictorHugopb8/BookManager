package com.digivox.seletiva.bookmanager.bookmanager.book;

import com.digivox.seletiva.bookmanager.bookmanager.customer.Customer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "sq_book_history", initialValue = 1)
//@AttributeOverride(name = "id", column = @Column(name = "book_id"))
public class BookHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private Book book;

    @OneToOne
    private Customer customer;

    private Boolean isRented;

    private Boolean isReserved;

    private LocalDate scheduledReturnDate;

    private LocalDate scheduledReserveDate;

    private Long rentedQuantity;

}
