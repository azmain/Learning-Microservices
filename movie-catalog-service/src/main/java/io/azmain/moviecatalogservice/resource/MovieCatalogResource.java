package io.azmain.moviecatalogservice.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.azmain.moviecatalogservice.models.CatalogItem;
import io.azmain.moviecatalogservice.models.Movie;
import io.azmain.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	/*
	 * It tells inject me RestTemplate from where it's been declared Autowire is a
	 * consumer, Bean is a producer
	 */
	@Autowired
	private RestTemplate restTemplate;

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
		
		List<Rating> ratings = Arrays.asList(
					new Rating("1234",8),
					new Rating("5678",7)
				);
		
		return ratings.stream().map(rating -> {
			/*
			 * It calls the api end point & get an string response which then converts to an
			 * object with the given class
			 * This call is synchronous but there's way to make these calls asynchronous
			 */
			Movie movie = restTemplate.getForObject("http://localhost:8082/movie/nishan", Movie.class);
			return new CatalogItem(movie.getMovieName(), "description", rating.getRating());
		})
		.collect(Collectors.toList());
		
		
		/*
		 * return Collections.singletonList( new CatalogItem("3 Idiots", "Educational",
		 * 9));
		 */
	}
}
