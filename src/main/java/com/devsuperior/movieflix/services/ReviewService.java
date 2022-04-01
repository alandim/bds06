package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;
	
	@Transactional
    public ReviewDTO insertReview(String text, Long movieId){
        Review entity = new Review();
        User user = authService.authenticated();
        User userEntity = userRepository.findByEmail(user.getUsername());
        Movie movie = movieRepository.getOne(movieId);
        entity.setId(entity.getId());
        entity.setUser(userEntity);
        entity.setMovie(movie);
        entity.setText(text);
        repository.save(entity);
        return new ReviewDTO(entity);
    }
	
}
