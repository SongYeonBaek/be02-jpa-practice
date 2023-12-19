package com.example.demo.movie.service;

import com.example.demo.movie.model.Movie;
import com.example.demo.movie.model.MovieDto;
import com.example.demo.movie.model.Review;
import com.example.demo.movie.model.ReviewDto;
import com.example.demo.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void create(MovieDto movieDto) {

        movieRepository.save(Movie.builder()
                .name(movieDto.getName())
                .price(movieDto.getPrice())
                .build());
    }

    public List<MovieDto> list() {
        List<Movie> movies = movieRepository.findAll();

          //Movie Entity를 MovieDto로 바꿔주서 반환
        List<MovieDto> movieDtos = new ArrayList<>();
        for (Movie movie:movies) {
            //해당 movie의 리뷰들을 ReviewDto 형태로 변환하여 저장
             List<Review> reviews= movie.getReviews();
             List<ReviewDto> reviewDtos = new ArrayList<>();

            for (Review review :reviews) {
                ReviewDto reviewDto = ReviewDto.builder()
                        .id(review.getId())
                        .text(review.getText())
                        .star(review.getStar())
                        .build();
                reviewDtos.add(reviewDto);
            }

            //MovieDto의 reviews에 위 리스트를 넣어준다
            MovieDto movieDto = MovieDto.builder()
                    .id(movie.getId())
                    .name(movie.getName())
                    .price(movie.getPrice())
                    .reviews(reviewDtos)
                    .build();
            movieDtos.add(movieDto);
        }
        return movieDtos;
    }

    public MovieDto read(Integer id) {

        Optional<Movie> result = movieRepository.findById(id);
        if (result.isPresent()) {
            Movie movie = result.get();

            //movie의 reviews를 받아와서 Dto로 변환하여 리스트를 만들어준다
            List<ReviewDto> reviewDtos = new ArrayList<>();
            for(Review review : movie.getReviews()){
                ReviewDto reviewDto = ReviewDto.builder()
                        .id(review.getId())
                        .star(review.getStar())
                        .text(review.getText())
                        .build();
                reviewDtos.add(reviewDto);
            }

            return MovieDto.builder()
                    .id(movie.getId())
                    .price(movie.getPrice())
                    .name(movie.getName())
                    .reviews(reviewDtos)
                    .build();
        } else {
            return null;
        }
    }

    public void update(MovieDto movieDto) {
        movieRepository.save(Movie.builder()
                .id(movieDto.getId())
                .name(movieDto.getName())
                .price(movieDto.getPrice())
                .build());
    }

    public void delete(Integer id) {
        movieRepository.delete(Movie.builder().id(id).build());
    }
}
