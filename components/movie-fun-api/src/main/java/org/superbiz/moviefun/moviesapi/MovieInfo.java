package org.superbiz.moviefun.moviesapi;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

public class MovieInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String director;
    private String title;
    private int year;
    private String genre;
    private int rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieInfo)) return false;
        MovieInfo movieInfo = (MovieInfo) o;
        return getId() == movieInfo.getId() &&
                getYear() == movieInfo.getYear() &&
                getRating() == movieInfo.getRating() &&
                Objects.equals(getDirector(), movieInfo.getDirector()) &&
                Objects.equals(getTitle(), movieInfo.getTitle()) &&
                Objects.equals(getGenre(), movieInfo.getGenre());
    }


    public MovieInfo() {
    }

    public MovieInfo(String title, String director, String genre, int rating, int year) {
        this.director = director;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }

    public MovieInfo(String director, String title, int year) {
        this.director = director;
        this.title = title;
        this.year = year;
    }



    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDirector(), getTitle(), getYear(), getGenre(), getRating());
    }

    public long getId() {
        return id;
    }

    public String getDirector() {
        return director;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public int getRating() {
        return rating;
    }
}
