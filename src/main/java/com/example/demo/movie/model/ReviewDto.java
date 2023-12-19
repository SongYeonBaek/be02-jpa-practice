package com.example.demo.movie.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
public class ReviewDto {
    Integer id;
    Integer star;
    String text;
    Integer movieId;

    MovieDto movieDto;
}
