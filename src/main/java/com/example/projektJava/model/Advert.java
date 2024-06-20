package com.example.projektJava.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name ="advert")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="information", length = 500)
    private String information;

    @Column(name="creation_date")
    private LocalDate creationDate;

    @Column(name="expiration_date")
    private LocalDate expirationDate;
    @Column(name="accepted")
    private Boolean accepted;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}
