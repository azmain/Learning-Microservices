package io.azmain.moviecatalogservice.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import io.azmain.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.azmain.moviecatalogservice.models.CatalogItem;
import io.azmain.moviecatalogservice.models.Movie;
import io.azmain.moviecatalogservice.models.Rating;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	/*
	 * It tells inject me RestTemplate from where it's been declared Autowire is a
	 * consumer, Bean is a producer
	 */
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		/**
		 * calling REST APIs programmatically Using a REST client library Spring Boot
		 * comes with a client already in our class path called Rest Template
		 * 
		 * Rest Template is now deprecated & web client(Reactive programming) is new.
		 * synchronous and asynchronous
		 */
		
		
		/*
		 * If we write like this below it will create an instance every time the api gets
		 * called, rather we can auto wired this
		 */
		
		//RestTemplate restTemplate = new RestTemplate(); // it is for calling web api programmatically
		
		UserRating ratings = restTemplate.getForObject("http://movie-data-service/ratingsdata/users/"+userId,UserRating.class);

		
		return ratings.getUserRating().stream().map(rating -> {
			/*
			 * It calls the api end point & get an string response which then converts to an
			 * object with the given class
			 * This call is synchronous but there's way to make these calls asynchronous
			 */
			Movie movie = restTemplate.getForObject("http://movie-info-service/movie/"+userId, Movie.class);

			/*Web Client Builder Way
			Movie movie = webClientBuilder.build()
					.get()
					.uri("http://localhost:8082/movie/"+rating.getMovieId())
					.retrieve()
					.bodyToMono(Movie.class)
					.block();*/

			return new CatalogItem(movie.getMovieName(), "description", rating.getRating());
		})
		.collect(Collectors.toList());
		
		
		/*
		 * return Collections.singletonList( new CatalogItem("3 Idiots", "Educational",
		 * 9));
		 */
	}
}
