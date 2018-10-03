package org.superbiz.moviefun.moviesapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class MoviesClient {


    private Logger logger = LoggerFactory.getLogger(MoviesClient.class);
    private static ParameterizedTypeReference<List<MovieInfo>> movieListType = new ParameterizedTypeReference<List<MovieInfo>>() {};
    private RestOperations restOperations;
    private String moviesUrl;

    public MoviesClient(String moviesUrl, RestOperations restOperations) {
        this.restOperations = restOperations;
        this.moviesUrl = moviesUrl;
    }


    public void addMovie(MovieInfo movie) {
        restOperations.postForEntity(moviesUrl, movie, MovieInfo.class);
    }

    public void deleteMovieId(long id) {
        restOperations.delete(moviesUrl + "/" + id);
    }

    public List<MovieInfo> getMovies() {
        return restOperations.exchange(moviesUrl, HttpMethod.GET, null, movieListType).getBody();
    }

    public List<MovieInfo> findAll(int firstResult, int maxResults) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl)
        .queryParam("start", firstResult)
        .queryParam("maxResults", maxResults);

        return restOperations.exchange(builder.toUriString(), HttpMethod.GET, null, movieListType).getBody();
    }

    public int countAll() {
        logger.info(moviesUrl + "/count");
        return restOperations.getForObject(moviesUrl + "/count", Integer.class);
    }

    public int count(String field, String key) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl + "/count")
                .queryParam("field", field)
                .queryParam("key", key);

        return restOperations.getForObject(builder.toUriString(), Integer.class);
    }

    public List<MovieInfo> findRange(String field, String searchTerm, int firstResult, int maxResults) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl)
                .queryParam("field", field)
                .queryParam("searchTerm", searchTerm)
                .queryParam("start", firstResult)
                .queryParam("maxResults", maxResults);

        return restOperations.exchange(builder.toUriString(), HttpMethod.GET, null, movieListType).getBody();

    }
}
