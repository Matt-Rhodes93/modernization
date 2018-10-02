package org.superbiz.moviefun.movies;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private static final String FIELD_PARAM = "field";
    private static final String SEARCH_TERM_PARAM = "searchTerm";
    private static final String START_PARAM = "start";
    private static final String MAX_RESULT_PARAM = "maxResults";
    private static final String KEY_PARAM = "key";

    private MoviesRepository moviesRepository;

    public MoviesController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @PostMapping
    public void addMovie(@RequestBody Movie inputMovie) {
        moviesRepository.addMovie(inputMovie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieById(@PathVariable Long id) {
        moviesRepository.deleteMovieId(id);
    }


    @GetMapping
    public List<Movie> getMovies(@RequestParam(name = FIELD_PARAM, required = false) String searchField, @RequestParam(name = SEARCH_TERM_PARAM, required = false) String searchTerm, @RequestParam(name = START_PARAM, required = false) Integer start, @RequestParam(name = MAX_RESULT_PARAM, required = false) Integer maxResults) {
        if (searchField != null && searchTerm != null) {
            return moviesRepository.findRange(searchField, searchTerm, start, maxResults);
        } else if (start != null && maxResults != null) {
            return moviesRepository.findAll(start, maxResults);
        } else {
            return moviesRepository.getMovies();
        }
    }

    @GetMapping("/count")
    public int count(@RequestParam(value = FIELD_PARAM, required = false) String field, @RequestParam(value = KEY_PARAM, required = false) String key) {
        if (field != null && key != null) {
            return moviesRepository.count(field, key);
        } else {
            return moviesRepository.countAll();
        }
    }




}
