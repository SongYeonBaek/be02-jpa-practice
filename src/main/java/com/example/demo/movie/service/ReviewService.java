package com.example.demo.movie.service;

import com.example.demo.movie.model.Movie;
import com.example.demo.movie.model.MovieDto;
import com.example.demo.movie.model.Review;
import com.example.demo.movie.model.ReviewDto;
import com.example.demo.movie.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void create(Integer movieId, ReviewDto reviewDto){
        reviewRepository.save(Review.builder()
                        .star(reviewDto.getStar())
                        .text(reviewDto.getText())
                        .movie(Movie.builder().id(movieId).build())
                        .build());
    }

    public List<ReviewDto> list() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDto> reviewDtos = new ArrayList<>();

        for(Review review : reviews) {
            Movie movie = review.getMovie();
            MovieDto movieDto = MovieDto.builder()
                    .id(movie.getId())
                    .name(movie.getName())
                    .price(movie.getPrice())
                    .build();

            ReviewDto reviewDto = ReviewDto.builder()
                    .id(review.getId())
                    .star(review.getStar())
                    .text(review.getText())
                    .movieDto(movieDto)
                    .build();
            reviewDtos.add(reviewDto);
        }
        return reviewDtos;
    }

    public ReviewDto read(Integer id){
        Optional<Review> review = reviewRepository.findById(id);

        if(review.isPresent()){
            return ReviewDto.builder()
                    .id(review.get().getId())
                    .star(review.get().getStar())
                    .text(review.get().getText())
                    .movieId(review.get().getMovie().getId())
                    .build();
        }
       else{
           return null;
        }

    }

    public void update(ReviewDto reviewDto){
        reviewRepository.save(Review.builder()
                        .id(reviewDto.getId())
                        .star(reviewDto.getStar())
                        .text(reviewDto.getText())
                        .build());
    }

    public void delete(Integer id){
        reviewRepository.delete(Review.builder().id(id).build());
    }

}
