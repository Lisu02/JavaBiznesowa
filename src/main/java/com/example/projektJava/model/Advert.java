package com.example.projektJava.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int id;

    @Column(name="information")
    private String information;


}
