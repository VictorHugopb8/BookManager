package com.digivox.seletiva.bookmanager.bookmanager.book;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "sq_book", initialValue = 1)
//@AttributeOverride(name = "id", column = @Column(name = "book_id"))
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String version;
    private Double rentPerDay;

}
