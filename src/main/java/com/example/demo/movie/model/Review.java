package com.example.demo.movie.model;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer star;
    String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Movie_id")
    Movie movie;
}
